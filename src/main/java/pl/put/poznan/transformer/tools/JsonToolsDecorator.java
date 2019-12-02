package pl.put.poznan.transformer.tools;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Set;

public interface JsonToolsDecorator extends JsonTools {
    JsonNode modify(JsonNode jsonNode, Set<String> modified);
}
