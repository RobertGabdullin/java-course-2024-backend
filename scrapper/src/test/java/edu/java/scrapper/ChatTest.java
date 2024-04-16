package edu.java.scrapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.java.dto.db.ChatDTO;
import edu.java.domain.ChatRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@SpringBootTest
public class ChatTest extends IntegrationTest {

    @Autowired
    private ChatRepository chatRepository;

    @Test
    @Transactional
    @Rollback
    public void addChatTest() {
        boolean success = chatRepository.add(1L);
        assertEquals(1, chatRepository.findAll().size());
        assertTrue(success);
        success = chatRepository.add(1L);
        assertEquals(1, chatRepository.findAll().size());
        assertFalse(success);
        success = chatRepository.add(2L);
        assertEquals(2, chatRepository.findAll().size());
        assertTrue(success);
    }

    @Test
    @Transactional
    @Rollback
    public void removeChatTest() {
        chatRepository.add(1);
        assertTrue(chatRepository.findAll().size() == 1);
        chatRepository.remove(1);
        assertTrue(chatRepository.findAll().isEmpty());
    }

    @Test
    @Transactional
    @Rollback
    public void findAllChatsTest() {
        chatRepository.add(17);
        chatRepository.add(13);

        List<ChatDTO> chats = chatRepository.findAll();

        assertEquals(2, chats.size());
        assertTrue(chats.stream().anyMatch(chat -> chat.chatId().equals(17L)));
        assertTrue(chats.stream().anyMatch(chat -> chat.chatId().equals(13L)));
    }



}
