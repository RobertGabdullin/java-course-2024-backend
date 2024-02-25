import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import edu.java.service.StackOverflowResponse;
import edu.java.service.StackOverflowClient;
import org.junit.jupiter.api.AfterEach;
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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StackOverflowClientTest {

    private WireMockServer wireMockServer;
    private StackOverflowClient client;

    @BeforeEach
    public void SetUp(){
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        WireMock.configureFor("localhost", 8080);
        client = new StackOverflowClient(WebClient.builder(), wireMockServer.baseUrl());
        stubFor(get(anyUrl())
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBodyFile("stackoverflow_response.json")));
    }

    @AfterEach
    public void ShutDown(){
        wireMockServer.stop();
    }

    @Test
    public void testResponse(){
        StackOverflowResponse res = client.lastUpdate(123);
        wireMockServer.verify(getRequestedFor(urlEqualTo("/questions/123?site=stackoverflow")));
        assertNotNull(res);
    }

    @Test
    public void testContent(){
        StackOverflowResponse res = client.lastUpdate(123);
        Instant instant = Instant.ofEpochSecond(1681305155);
        assertEquals(res.getLastUpdate(), OffsetDateTime.ofInstant(instant, ZoneOffset.UTC));
    }

}
