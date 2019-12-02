package pl.put.poznan.transformer.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 *
 * Decorator Implementation which decorate Component JsonTools.
 * This class is responsible for filtering and ignoring nodes in JSON file
 * by given input data set.
 *
 * @author Damian, Krzysztof
 * @version 1.0
 */

@Slf4j
@Service
public class JsonToolsIgnorer implements JsonToolsDecorator {

    /**
     * Field which contains specific implementation of Component.
     */
    private JsonTools jsonTools;

    /**
     * Class constructor, initialize jsonTools implementation.
     *
     * @param jsonTools specific implementation of Component.
     */
    public JsonToolsIgnorer(@Qualifier("JsonTools") JsonTools jsonTools) {
        this.jsonTools = jsonTools;
    }


    /**
     * Overrides implementation of interface. Assign specific Component implementation.
     *
     * @param json JSON file in String format
     * @return JsonNode JSON main node. JSON file in Object format
     */
    @Override
    public JsonNode parseJson(String json) {
        return jsonTools.parseJson(json);
    }


    /**
     * Overrides modify function of JsonToolsDecorator.
     * This function modifies actual Object JSON file by filtering and ignoring
     * with entered node's values.
     *
     * @param json JSON main node. JSON file in Object format.
     * @param ignored Set of String data. Set of node's names to be ignored.
     * @return JSON main node. JSON file with selected values.
     * @throws IllegalStateException Throws exception when parameters set is invalid.
     */
    @Override
    public JsonNode modify(JsonNode json, Set<String> ignored) {
        ObjectNode objectNode = (ObjectNode) json;
        try {
            ignored.forEach(property -> objectNode.findParents(property).forEach(node -> {
                ((ObjectNode) node).without(property);
            }));
        } catch (NullPointerException e) {
            throw new IllegalStateException("The set of parameters to ignore is incorrect !");
        }
        return objectNode;
    }
}
