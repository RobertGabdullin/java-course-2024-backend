package edu.java.scrapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.java.dto.db.ChatDTO;
import edu.java.dto.db.ChatLinkDTO;
import edu.java.dto.db.LinkDTO;
import edu.java.domain.ChatLinkRepository;
import edu.java.domain.ChatRepository;
import edu.java.domain.LinkRepository;
import io.swagger.v3.oas.models.links.Link;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class ChatLinkTest extends IntegrationTest {

    @Autowired
    private ChatLinkRepository chatLinkRepository;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private LinkRepository linkRepository;

    static final LinkDTO SAMPLE_LINK = new LinkDTO(3L, "https://example.com", null, null);
    static final LinkDTO SAMPLE_LINK2 = new LinkDTO(4L, "https://example2.com", null, null);


    @Test
    @Transactional
    @Rollback
    public void testAddChatLink() {
        chatRepository.add(1);
        long id = linkRepository.add(SAMPLE_LINK);
        chatLinkRepository.add(1, id);
        assertEquals(1, chatLinkRepository.findAll().size());
    }

    @Test
    @Transactional
    @Rollback
    public void testRemoveChatLink() {
        chatRepository.add(1);
        long id = linkRepository.add(SAMPLE_LINK);
        chatLinkRepository.add(1, id);
        assertTrue(chatLinkRepository.findAll().size() == 1);
        chatLinkRepository.remove(1, id);
        assertTrue(chatLinkRepository.findAll().isEmpty());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindAllChatLinks() {
        chatRepository.add(1);
        long id = linkRepository.add(SAMPLE_LINK);
        chatLinkRepository.add(1, id);
        List<ChatLinkDTO> chatLinks = chatLinkRepository.findAll();
        assertEquals(1, chatLinks.size());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindById(){
        chatRepository.add(1);
        long id = linkRepository.add(SAMPLE_LINK);
        long id2 = linkRepository.add(SAMPLE_LINK2);
        chatLinkRepository.add(1, id);
        linkRepository.add(SAMPLE_LINK2);
        chatLinkRepository.add(1, id2);
        List <LinkDTO> res = chatLinkRepository.findAllLinksByChatId(1);
        assertEquals(2, res.size());
    }

}
