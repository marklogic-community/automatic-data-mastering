xquery version "1.0-ml";

module namespace plugin = "http://marklogic.com/data-hub/plugins/default";

declare option xdmp:mapping "false";

(:~
 : Writer Plugin
 :
 : @param $id       - the identifier returned by the collector
 : @param $envelope - the final envelope
 : @param $options  - a map containing options. Options are sent from Java
 :
 : @return - zero or more header nodes
 :)
declare function plugin:write(
  $id as xs:string,
  $content as node(),
  $options as map:map) as empty-sequence()
{
  xdmp:document-insert($id, $content)
};