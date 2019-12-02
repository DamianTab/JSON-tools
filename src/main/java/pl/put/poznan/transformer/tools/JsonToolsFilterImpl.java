package pl.put.poznan.transformer.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        try{
            ignored.forEach(property -> objectNode.findParents(property).forEach(node->{
                ((ObjectNode)node).without(property);
                    } ));
        }catch (NullPointerException e){
            throw new IllegalStateException("The set of parameters to ignore is incorrect !");
        }
        return objectNode;
    }

    public JsonNode select(JsonNode json, Set<String> selected) {
        ObjectNode objectNode = (ObjectNode) json;
        try{
            selected.forEach(property -> objectNode.findParent(property).retain(property));
        }catch (NullPointerException e){
            throw new IllegalStateException("The set of parameters to select is incorrect !");
        }
        return objectNode;
    }
}
