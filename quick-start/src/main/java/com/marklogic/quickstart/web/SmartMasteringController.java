package com.marklogic.quickstart.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marklogic.quickstart.EnvironmentAware;
import com.marklogic.quickstart.model.SearchQuery;
import com.marklogic.quickstart.service.SearchService;
import com.marklogic.quickstart.service.SmartMasteringSearchService;
import com.marklogic.quickstart.service.SmartMasteringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class SmartMasteringController extends EnvironmentAware {

    @Autowired
    SmartMasteringService smartMasteringService;

    @Autowired
    private SmartMasteringSearchService smartMasteringSearchService;

    @Bean
    @Scope(proxyMode= ScopedProxyMode.TARGET_CLASS, value="session")
    SmartMasteringService smartMasteringService() { return new SmartMasteringService(envConfig().getFinalClient()); }

    @Bean
    @Scope(proxyMode= ScopedProxyMode.TARGET_CLASS, value="session")
    SmartMasteringSearchService smartMasteringSearchService() {
        return new SmartMasteringSearchService(envConfig().getMlSettings());
    }

    @RequestMapping(value = "/mastering/search", method = RequestMethod.POST)
    @ResponseBody
    public String search(@RequestBody SearchQuery searchQuery) {
        return smartMasteringSearchService.search(searchQuery).get();
    }

    @GetMapping("/mastering/stats")
    @ResponseBody
    String getStats() {
        return smartMasteringService.getStats();
    }

    @RequestMapping(value = "/mastering/doc", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getDoc(@RequestParam String docUri) {
        HttpHeaders headers = new HttpHeaders();
        String body = smartMasteringService.getDoc(docUri);
        if (body.startsWith("<")) {
            headers.setContentType(MediaType.APPLICATION_XML);
        } else {
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        }
        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/mastering/merge", method = RequestMethod.POST)
    public ResponseEntity<String> merge(@RequestParam String doc1, @RequestParam String doc2, @RequestParam String options) {
        smartMasteringService.mergeDocs(doc1, doc2, options);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
