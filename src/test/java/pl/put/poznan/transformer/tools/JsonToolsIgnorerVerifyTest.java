package pl.put.poznan.transformer.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class JsonToolsIgnorerVerifyTest {
    JsonTools jsonTools = spy(new JsonToolsImpl(new ObjectMapper()));

    JsonToolsIgnorer jsonToolsIgnorer = new JsonToolsIgnorer(jsonTools);

    @Test()
    public void shouldCallJsonToolsOnce() {
        jsonToolsIgnorer.getPrettyJson("{}");

        verify(jsonTools, times(1)).getPrettyJson(anyString());
    }

    @Test()
    public void shouldCallJsonToolsOnceWhenParsingJson() {
        jsonToolsIgnorer.parseJson("{}");

        verify(jsonTools, times(1)).parseJson(anyString());
    }
}