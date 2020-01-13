package pl.put.poznan.transformer.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class JsonToolsImplTest {

    JsonTools uut = new JsonToolsImpl(new ObjectMapper());

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionOnInvalidJsonParse() {
        // given
        String invalidJson = "invalid";
        // when
        uut.parseJson(invalidJson);
        // then should throw error
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionOnNullPrimitives() {
        // given
        String invalidJson = "{\"asd\":";
        // when
        uut.parseJson(invalidJson);
        // then should throw error
    }

    @Test()
    public void shouldParseDuplicateKey() {
        // given
        String duplicateKeysJson = "{\"asd\":1, \"asd:\":1}";
        // when
        uut.parseJson(duplicateKeysJson);
        // then should not throw exception
    }

    @Test()
    public void shouldParseValidJson() {
        // given
        String validJson = "{\"asd\":1}";
        // when
        uut.parseJson(validJson);
        // then should throw error
    }

}