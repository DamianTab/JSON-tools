package pl.put.poznan.transformer.tools;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * Specific Implementation of JsonTools (Component).
 * This class is responsible for parsing JSON file and storing Object Mapper.
 *
 * @author Krzysztof
 * @version 1.0
 */

@Slf4j
@Service
@Qualifier("JsonTools")
public class JsonToolsImpl implements JsonTools {

    /**
     * Field that storage ObjectMapper. It is responsible for serialization and deserialization JSON.
     */
    private ObjectMapper mapper;

    /**
     * Class constructor, initializes ObjectMapper implementation and configures mapper's flags.
     *
     * @param mapper ObjectMapper implementation which is injected by Spring
     */
    @Autowired
    public JsonToolsImpl(ObjectMapper mapper) {
        this.mapper = mapper;
        mapper.enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES,
                DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY,
                DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
    }

    /**
     * Overrides abstract method of JsonTools interface.
     * It is responsible for parsing JSON file from String to Java Object.
     *
     * @param json JSON file in String format
     * @return JsonNode JSON main node. JSON file in Object format
     * @throws IllegalStateException Throws exception when JSON format is invalid.
     */
    @Override
    public JsonNode parseJson(String json) {
        try {
            return mapper.readTree(json);
        } catch (IOException e) {
            log.error("Error processing json: {}", json);
            throw new IllegalStateException("Invalid JSON format");
        }
    }
}
