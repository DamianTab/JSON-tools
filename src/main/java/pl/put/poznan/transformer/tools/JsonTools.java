package pl.put.poznan.transformer.tools;

import com.fasterxml.jackson.databind.JsonNode;

public interface JsonTools {
    JsonNode parseJson(String json);
    String getPrettyJson(String json);
}
