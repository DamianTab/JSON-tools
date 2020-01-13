package pl.put.poznan.transformer.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class JsonToolsImplVerifyTest {

    ObjectMapper mapper = spy(new ObjectMapper());

    JsonTools uut = new JsonToolsImpl(mapper);

    @Test()
    public void shouldCallMapperOnce() throws IOException {
        // when
        uut.parseJson(anyString());
        // then
        verify(mapper, times(1)).readTree(anyString());
    }

    @Test()
    public void shouldCallMapperOnceGettingPrettyPrintEmptyJson() {
        // when
        uut.getPrettyJson("{}");
        // then
        verify(mapper, times(1)).writerWithDefaultPrettyPrinter();
    }

    @Test()
    public void shouldCallMapperOnceGettingPrettyPrintValidJson() {
        // when
        uut.getPrettyJson("{\"abc\":1}");
        // then
        verify(mapper, times(1)).writerWithDefaultPrettyPrinter();
    }

    @Test()
    public void shouldCallMapperOneTimeParsingEmptyJson() throws IOException {
        uut.parseJson("");
        // then
        verify(mapper, times(1)).readTree(anyString());
    }

}