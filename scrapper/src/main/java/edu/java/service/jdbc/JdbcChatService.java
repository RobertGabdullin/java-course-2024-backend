package edu.java.service.jdbc;

import edu.java.domain.ChatRepository;
import edu.java.dto.db.ChatDTO;
import edu.java.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JdbcChatService implements ChatService {
    @Autowired
    private ChatRepository chatRepository;

    public void register(long tgChatId){
        chatRepository.add(tgChatId);
    }

    public void unregister(long tgChatId){
        chatRepository.remove(tgChatId);
    }

}
