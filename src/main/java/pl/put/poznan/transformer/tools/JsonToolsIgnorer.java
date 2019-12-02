package pl.put.poznan.transformer.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class JsonToolsIgnorer implements JsonToolsDecorator {

    private JsonTools jsonTools;

    public JsonToolsIgnorer(@Qualifier("JsonTools") JsonTools jsonTools) {
        this.jsonTools = jsonTools;
    }

    @Override
    public JsonNode parseJson(String json) {
        return jsonTools.parseJson(json);
    }

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
