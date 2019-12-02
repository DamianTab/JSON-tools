package pl.put.poznan.transformer.tools;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Set;

public interface JsonToolsFilter extends JsonTools {
    JsonNode ignore(JsonNode jsonNode, Set<String> ignored);
    JsonNode select(JsonNode jsonNode, Set<String> selected);
}
