package pl.put.poznan.transformer.tools;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@Qualifier("JsonTools")
public class JsonToolsImpl implements JsonTools {

    private ObjectMapper mapper;

    @Autowired
    public JsonToolsImpl(ObjectMapper mapper) {
        this.mapper = mapper;
        mapper.enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES,
                      DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY,
                      DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
    }

    @Override
    public JsonNode parseJson(String json) {
        try {
            return mapper.readTree(json);
        } catch (IOException e) {
            log.error("Error processing json: {}", json);
            throw new IllegalStateException("Invalid json");
        }
    }
}
