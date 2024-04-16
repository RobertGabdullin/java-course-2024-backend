package edu.java.domain;

import edu.java.dto.db.ChatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ChatRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ChatRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcTemplate.update("DELETE FROM Link WHERE link_id = 1");
        System.out.println("Hello, I am created");
    }

    @Transactional
    public boolean add(long chatId) {
        String sql = "SELECT COUNT(*) FROM Chat WHERE chat_id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, chatId);
        if(count > 0)
            return false;
        jdbcTemplate.update("INSERT INTO Chat (chat_id) VALUES (?)", chatId);
        return true;
    }

    @Transactional
    public void remove(long chatId) {
        jdbcTemplate.update("DELETE FROM Chat WHERE chat_id = ?", chatId);
    }

    @Transactional
    public List<ChatDTO> findAll() {
        return jdbcTemplate.query(
            "SELECT chat_id FROM Chat",
            (rs, rowNum) -> new ChatDTO(
                rs.getLong("chat_id")
            )
        );
    }
}

