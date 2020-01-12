package pl.put.poznan.transformer.tools;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Base64;


/**
 * Decorator Implementation which decorate Component JsonTools.
 * This class is responsible for filtering and ignoring nodes in JSON file
 * by given input data set.
 *
 * @author Damian, Krzysztof
 * @version 1.0
 */

@Slf4j
@Service
public class JsonToolsBase64 implements JsonToolsBase64Decorator {

    /**
     * Field which contains specific implementation of Component.
     */
    private JsonTools jsonTools;

    /**
     * Class constructor, initializes jsonTools implementation.
     *
     * @param jsonTools specific implementation of Component.
     */
    public JsonToolsBase64(@Qualifier("JsonTools") JsonTools jsonTools) {
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
     * Overrides modify function of JsonToolsBase64Decorator.
     * This function encodes a JSON string in Base64 encoding.
     *
     * @param json    JSON main node. JSON file in Object format.
     * @return String Base64 encoded string.
     */
    @Override
    public String toBase64(JsonNode json) {
        return Base64.getEncoder().encodeToString(json.toString().getBytes());
    }
}
