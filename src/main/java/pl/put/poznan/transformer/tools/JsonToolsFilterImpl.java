package pl.put.poznan.transformer.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class JsonToolsFilterImpl implements JsonToolsFilter {

    private JsonTools jsonTools;

    public JsonToolsFilterImpl(@Qualifier("JsonTools") JsonTools jsonTools) {
        this.jsonTools = jsonTools;
    }

    @Override
    public JsonNode parseJson(String json) {
        return jsonTools.parseJson(json);
    }

    public JsonNode ignore(JsonNode json, Set<String> ignored) {
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

    public JsonNode select(JsonNode json, Set<String> selected) {
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
