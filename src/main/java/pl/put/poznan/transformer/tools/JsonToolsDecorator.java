package pl.put.poznan.transformer.tools;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Set;


/**
 * Decorator Interface which decorate JsonTools.
 * This interface is responsible for providing modify operation.
 *
 * @author Damian
 * @version 1.0
 */
public interface JsonToolsDecorator extends JsonTools {

    /**
     * This function modifies actual Object JSON file.It is used to implement specific decorator operation.
     *
     * @param jsonNode    JSON main node. JSON file in Object format.
     * @param modified Set of String data. Set of node's names that will be used in processing.
     * @return JSON main node.
     */
    JsonNode modify(JsonNode jsonNode, Set<String> modified);
}
