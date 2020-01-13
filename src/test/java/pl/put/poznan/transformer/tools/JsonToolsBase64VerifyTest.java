package pl.put.poznan.transformer.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class JsonToolsBase64VerifyTest {

    JsonTools jsonTools = spy(new JsonToolsImpl(new ObjectMapper()));

    JsonToolsBase64 toolsBase64 = new JsonToolsBase64(jsonTools);

    @Test()
    public void shouldCallJsonToolsOnce() {
        toolsBase64.getPrettyJson("{}");

        verify(jsonTools, times(1)).getPrettyJson(anyString());
    }

    @Test()
    public void shouldCallJsonToolsOnceWhenParsingJson() {
        toolsBase64.parseJson("{}");

        verify(jsonTools, times(1)).parseJson(anyString());
    }

}