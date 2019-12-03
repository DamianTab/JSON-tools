package pl.put.poznan.transformer.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;

/**
 * Decorator Implementation which decorate Component JsonTools.
 * This class is responsible for filtering and selecting nodes in JSON file
 * by given input data set.
 *
 * @author Damian
 * @version 1.0
 */
public class JsonToolsSelector implements JsonToolsDecorator {

    /**
     * Field which contains specific implementation of Component.
     */
    private JsonTools jsonTools;

    /**
     * Class constructor, initializes jsonTools implementation.
     *
     * @param jsonTools specific implementation of Component
     */
    public JsonToolsSelector(@Qualifier("JsonTools") JsonTools jsonTools) {
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

    @Override
    public String getPrettyJson(String json) {
        return jsonTools.getPrettyJson(json);
    }


    /**
     * Overrides modify function of JsonToolsDecorator.
     * This function modifies actual Object JSON file by filtering and selecting
     * with entered node's values.
     *
     * @param json     JSON main node. JSON file in Object format.
     * @param selected Set of String data. Set of node's names to be selected.
     * @return JSON main node. JSON file with selected values.
     * @throws IllegalStateException Throws exception when parameters set is invalid.
     */
    public JsonNode modify(JsonNode json, Set<String> selected) {
        ObjectNode objectNode = (ObjectNode) json;
        try {
            selected.forEach(property -> {
                List<JsonNode> jsonNodes = objectNode.findParents(property);
                List<String> deleteList = new ArrayList<>();

                for (JsonNode selectedNode : jsonNodes) {
                    Iterator<Map.Entry<String, JsonNode>> iterator = selectedNode.fields();
                    while (iterator.hasNext()) {
                        Map.Entry<String, JsonNode> entry = iterator.next();
                        if (!entry.getKey().equals(property) && entry.getValue().findParent(property) == null) {
                            deleteList.add(entry.getKey());
                        }
                    }
                }
                deleteList.forEach(toDelete -> objectNode.findParents(toDelete).forEach(node -> {
                    ((ObjectNode) node).without(toDelete);
                }));
            });
        } catch (NullPointerException e) {
            throw new IllegalStateException("The set of parameters to select is incorrect !");
        }
        return objectNode;
    }
}
