import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import edu.java.service.GitHubActivity;
import edu.java.service.GitHubClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@WireMockTest(httpPort = 8443)
public class GitHubClientTest {
   // private WireMockServer wireMockServer;


    @Test
    public void testGetLastRepositoryUpdate() {
        // Define the expected request and response
        stubFor(get(anyUrl())
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBodyFile("github_response.json")));

        // Make the call to the GitHub client method under test
        WebClient.Builder webClientBuilder = WebClient.builder();
        //webClientBuilder.baseUrl("http://localhost:8080");

        GitHubClient gitHubClient = new GitHubClient(webClientBuilder, "http://localhost:8443");
        List<GitHubActivity> response = gitHubClient.getListUpdates("octocat", "Hello-World");
        verify(getRequestedFor(anyUrl()));
        GitHubActivity lastActivity = response.getFirst();
        // Perform assertions on the response
        assertNotNull(response);
        assertEquals("2011-01-26T19:14:43Z", lastActivity.toString());
    }
}
