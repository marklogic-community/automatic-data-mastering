xquery version "1.0-ml";

module namespace plugin = "http://marklogic.com/data-hub/plugins";

declare namespace envelope = "http://marklogic.com/data-hub/envelope";
declare namespace agile-mastering = "http://marklogic.com/agile-mastering";

declare option xdmp:mapping "false";

(:~
 : Create Headers Plugin
 :
 : @param $id      - the identifier returned by the collector
 : @param $content - the output of your content plugin
 : @param $options - a map containing options. Options are sent from Java
 :
 : @return - zero or more header nodes
 :)
declare function plugin:create-headers(
  $id as xs:string,
  $content as node()?,
  $options as map:map) as node()*
{
  element agile-mastering:id {sem:uuid-string()},
  element agile-mastering:sources {
    element agile-mastering:source {
      element agile-mastering:name {map:get($options, "mdm-source")},
      element agile-mastering:import-id {map:get($options, "import-id")},
      element agile-mastering:user {xdmp:get-current-user()},
      element agile-mastering:dateTime {fn:current-dateTime()}
    }
  }
};