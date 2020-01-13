package pl.put.poznan.transformer.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class JsonToolsSelectorVerifyTest {
    JsonTools jsonTools = spy(new JsonToolsImpl(new ObjectMapper()));

    JsonToolsSelector jsonToolsSelector = new JsonToolsSelector(jsonTools);

    @Test()
    public void shouldCallJsonToolsOnce() {
        jsonTools.getPrettyJson("{}");

        verify(jsonTools, times(1)).getPrettyJson(anyString());
    }

    @Test()
    public void shouldCallJsonToolsOnceWhenParsingJson() {
        jsonTools.parseJson("{}");

        verify(jsonTools, times(1)).parseJson(anyString());
    }
}