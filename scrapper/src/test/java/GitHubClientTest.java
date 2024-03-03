import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import edu.java.data_transfer.GitHubActivity;
import edu.java.service.GitHubClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.OffsetDateTime;
import java.util.List;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@WireMockTest(httpPort = 8080)
public class GitHubClientTest {

    private GitHubClient client;
    private final String owner = "octocat";
    private final String repo = "Hello-World";
    @BeforeEach
    public void setUp(WireMockRuntimeInfo wmRuntimeInfo){
        client = new GitHubClient(WebClient.builder(), wmRuntimeInfo.getHttpBaseUrl());
        stubFor(get(urlEqualTo("/repos/octocat/Hello-World/activity"))
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBodyFile("github_response.json")));
    }

    @Test
    public void testResponse(){
        List<GitHubActivity> response = client.getListUpdates(owner, repo);
        assertNotNull(response);
        assertEquals(2, response.size());
        verify(getRequestedFor(urlEqualTo("/repos/octocat/Hello-World/activity")));
    }

    @Test
    public void testContent() {
        List<GitHubActivity> response = client.getListUpdates(owner, repo);
        assertEquals(response.getFirst().timestamp(), OffsetDateTime.parse("2011-01-26T19:14:43Z"));
        assertEquals(response.get(1).timestamp(), OffsetDateTime.parse("2012-02-24T19:15:43Z"));
    }
}
