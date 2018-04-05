xquery version "1.0-ml";

module namespace plugin = "http://marklogic.com/data-hub/plugins";

import module namespace flow = "http://marklogic.com/data-hub/flow-lib"
  at "/MarkLogic/data-hub-framework/impl/flow-lib.xqy";

declare namespace es = "http://marklogic.com/entity-services";
declare namespace am = "http://marklogic.com/agile-mastering";

declare option xdmp:mapping "false";

(:~
 : Create Content Plugin
 :
 : @param $id          - the identifier returned by the collector
 : @param $options     - a map containing options. Options are sent from Java
 :
 : @return - your transformed content
 :)
declare function plugin:create-content(
  $id as xs:string,
  $options as map:map) as item()?
{
  element uri {$id}
};