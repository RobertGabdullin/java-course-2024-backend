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


    @BeforeEach
    public void setUp(){
        List <LinkDTO> asd = linkRepository.findAll();
        chatRepository.add(1);
        linkRepository.add(SAMPLE_LINK);
    }

    @Test
    @Transactional
    @Rollback
    public void testAddChatLink() {
        chatLinkRepository.add(1, 2);
        assertEquals(1, chatLinkRepository.findAll().size());
    }

    @Test
    @Transactional
    @Rollback
    public void testRemoveChatLink() {
        chatLinkRepository.add(1, 1);
        assertTrue(chatLinkRepository.findAll().size() == 1);
        chatLinkRepository.remove(1, 1);
        assertTrue(chatLinkRepository.findAll().isEmpty());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindAllChatLinks() {
        chatLinkRepository.add(1, 5);
        List<ChatLinkDTO> chatLinks = chatLinkRepository.findAll();
        assertEquals(1, chatLinks.size());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindById(){
        chatLinkRepository.add(1, 3);
        linkRepository.add(SAMPLE_LINK2);
        chatLinkRepository.add(1, 4);
        List <LinkDTO> res = chatLinkRepository.findAllLinksByChatId(1);
        assertTrue(res.contains(SAMPLE_LINK));
        assertTrue(res.contains(SAMPLE_LINK2));
    }

}
