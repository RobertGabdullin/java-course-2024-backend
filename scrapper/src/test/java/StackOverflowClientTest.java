import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import edu.java.dto.response.stackoverflow.StackOverflowResponse;
import edu.java.client.StackOverflowClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@WireMockTest(httpPort = 8080)
public class StackOverflowClientTest {

    private StackOverflowClient client;

    @BeforeEach
    public void setUp(WireMockRuntimeInfo wmRuntimeInfo){
        client = new StackOverflowClient(WebClient.builder(), wmRuntimeInfo.getHttpBaseUrl());
        stubFor(get(anyUrl())
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBodyFile("stackoverflow_response.json")));
    }

    @Test
    public void testResponse(){
        StackOverflowResponse res = client.lastUpdate(123);
        verify(getRequestedFor(urlEqualTo("/questions/123?site=stackoverflow")));
        assertNotNull(res);
    }

    @Test
    public void testContent(){
        StackOverflowResponse res = client.lastUpdate(123);
        Instant instant = Instant.ofEpochSecond(1681305155);
        assertEquals(res.getLastUpdate(), OffsetDateTime.ofInstant(instant, ZoneOffset.UTC));
    }

}
