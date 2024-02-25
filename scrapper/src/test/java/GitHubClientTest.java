import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import edu.java.service.GitHubActivity;
import edu.java.service.GitHubClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.OffsetDateTime;
import java.util.List;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GitHubClientTest {

    private WireMockServer wireMockServer;
    private GitHubClient client;
    @BeforeEach
    public void SetUp(){
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        WireMock.configureFor("localhost", 8080);
        client = new GitHubClient(WebClient.builder(), wireMockServer.baseUrl());
        stubFor(get(urlEqualTo("/repos/octocat/Hello-World/activity"))
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBodyFile("github_response.json")));
    }

    @AfterEach
    public void ShutDown(){
        wireMockServer.stop();
    }

    @Test
    public void testResponse(){
        List<GitHubActivity> response = client.getListUpdates("octocat", "Hello-World");
        assertNotNull(response);
        assertEquals(2, response.size());
        verify(getRequestedFor(urlEqualTo("/repos/octocat/Hello-World/activity")));
    }

    @Test
    public void testContent() {
        List<GitHubActivity> response = client.getListUpdates("octocat", "Hello-World");
        assertEquals(response.getFirst().getTimestamp(), OffsetDateTime.parse("2011-01-26T19:14:43Z"));
        assertEquals(response.get(1).getTimestamp(), OffsetDateTime.parse("2012-02-24T19:15:43Z"));
    }
}
