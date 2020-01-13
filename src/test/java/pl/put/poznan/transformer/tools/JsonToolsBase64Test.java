package pl.put.poznan.transformer.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class JsonToolsBase64Test {

    private static final String ENCODED_JSON = "eyJrZXkiOiJ2YWx1ZSJ9";

    private static final String SAMPLE_JSON = "{\"key\":\"value\"}";

    JsonTools jsonTools = new JsonToolsImpl(new ObjectMapper());

    JsonToolsBase64 uut = new JsonToolsBase64(jsonTools);

    @Test
    public void shouldConvertValidJsonToBase64() {
        // given
        JsonNode json = getValidJsonNode();
        // when
        String result = uut.toBase64(json);
        // then
        assertEquals(ENCODED_JSON, result);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowErrorConvertingEmptyJsonToBase64() {
        // given
        JsonNode json = getEmptyJsonNode();
        // when
        String base64json = uut.toBase64(json);
        // then should throw error
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowErrorOnNullJsonConversionToBase64() {
        // given
        JsonNode json = null;
        // when
        uut.toBase64(json);
        // then should throw error
    }

    @Test
    public void shouldConvertValidBase64ToJson() {
        // given
        JsonNode validDecodedJson = getValidJsonNode();
        // when
        JsonNode result = uut.toJson(ENCODED_JSON);
        // then
        assertEquals(validDecodedJson, result);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowErrorOnNullBase64ConversionToJson() {
        // given
        String base64 = null;
        // when
        uut.toJson(base64);
        // then should throw error
    }

    @Test()
    public void shouldConvertEmptyBase64ToJson() {
        // given
        String base64 = "";
        // when
        JsonNode result = uut.toJson(base64);
        // then
        assertEquals(getEmptyJsonNode(), result);
    }

    private JsonNode getValidJsonNode() {
        return jsonTools.parseJson(SAMPLE_JSON);
    }

    private JsonNode getEmptyJsonNode() {
        return jsonTools.parseJson("");
    }
}