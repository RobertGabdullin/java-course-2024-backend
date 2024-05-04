package edu.java.scrapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.java.dto.db.LinkDTO;
import edu.java.domain.LinkRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class LinkTest extends IntegrationTest {

    @Autowired
    private LinkRepository linkRepository;

    static final LinkDTO SAMPLE_LINK1 = new LinkDTO(null, "https://example.com/1", null, null);
    static final LinkDTO SAMPLE_LINK2 = new LinkDTO(null, "https://example.com/2", null, null);

    @Test
    @Transactional
    @Rollback
    public void addLinkTest() {
        long idx = linkRepository.add(SAMPLE_LINK1);
        assertTrue(idx > 0);
        idx = linkRepository.add(SAMPLE_LINK1);
        assertEquals(4, idx);
        assertEquals(1, linkRepository.findAll().size());
    }

    @Test
    @Transactional
    @Rollback
    public void removeLinkTest() {
        linkRepository.add(SAMPLE_LINK1);
        assertTrue(linkRepository.findAll().size() == 1);

        linkRepository.remove("https://example.com/1");
        assertTrue(linkRepository.findAll().isEmpty());
    }

    @Test
    @Transactional
    @Rollback
    public void findAllLinksTest() {
        linkRepository.add(SAMPLE_LINK1);
        linkRepository.add(SAMPLE_LINK2);

        List<LinkDTO> links = linkRepository.findAll();
        assertEquals(2, links.size());
        assertTrue(links.stream().anyMatch(link -> link.url().equals("https://example.com/1")));
        assertTrue(links.stream().anyMatch(link -> link.url().equals("https://example.com/2")));
    }
}
