import org.cleanCode.Translation.TranslatorApi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("TranslationApi Test")
public class TranslatorApiTest {

    private TranslatorApi api;
    private HttpResponse<String> mockedResponse;
    private HttpClient mockedClient;

    @BeforeEach
    void setUp() {
        api = new TranslatorApi();
    }

    @Test
    @DisplayName("GetLanguagesCall Test")
    void sendLanguagesCallTest() throws IOException, InterruptedException {
        mockedClient = mock(HttpClient.class);
        mockedResponse = mock(HttpResponse.class);

        when(mockedResponse.body()).thenReturn("Mocked Response");

        when(mockedClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockedResponse);

        api.setClient(mockedClient);

        String result = api.sendGetLanguagesCall();

        assertEquals("Mocked Response", result);
    }

    @Test
    @DisplayName("GetLanguagesCall Fail Test")
    void sendLanguagesCallFailTest() throws IOException, InterruptedException {
        mockedClient = mock(HttpClient.class);
        mockedResponse = mock(HttpResponse.class);

        when(mockedClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenThrow(IOException.class);

        api.setClient(mockedClient);

        assertThrows(IOException.class, () ->api.sendGetLanguagesCall());
    }

    @Test
    @DisplayName("SendTranslateCall Test")
    void sendTranslateCallTest() throws IOException, InterruptedException {
        mockedClient = mock(HttpClient.class);
        mockedResponse = mock(HttpResponse.class);

        when(mockedResponse.body()).thenReturn("Mocked Response");
        when(mockedClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockedResponse);

        api.setClient(mockedClient);

        String result = api.sendTranslateCall("dummy","dummy","dummy");
        assertEquals("Mocked Response", result);
    }


    @Test
    @DisplayName("SendTranslateCall Fail Test")
    void sendTranslateCallFailTest() throws IOException, InterruptedException {
        mockedClient = mock(HttpClient.class);
        mockedResponse = mock(HttpResponse.class);

        when(mockedClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenThrow(IOException.class);

        api.setClient(mockedClient);

        assertThrows(IOException.class, () ->api.sendTranslateCall("dummy","dummy","dummy"));
    }

    @AfterEach
    void tearDown() {
        mockedClient = null;
        mockedResponse = null;
        api = null;
    }
}
