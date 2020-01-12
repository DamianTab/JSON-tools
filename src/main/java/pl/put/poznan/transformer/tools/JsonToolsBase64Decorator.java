package pl.put.poznan.transformer.tools;

import com.fasterxml.jackson.databind.JsonNode;

public interface JsonToolsBase64Decorator extends JsonTools {
    String toBase64(JsonNode jsonNode);
}
