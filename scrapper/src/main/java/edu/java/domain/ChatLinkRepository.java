package edu.java.domain;

import edu.java.dto.db.ChatDTO;
import edu.java.dto.db.ChatLinkDTO;
import edu.java.dto.db.LinkDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.swing.text.StyledEditorKit;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;

@Repository
public class ChatLinkRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    LinkRepository linkRepository;

    @Autowired
    public ChatLinkRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public Boolean add(long chatId, long linkId) {
        String sql = "SELECT COUNT(*) FROM Chat_Link WHERE chat_id = ? AND link_id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, chatId, linkId);
        if(count > 0)
            return false;
        jdbcTemplate.update(
            "INSERT INTO Chat_Link (chat_id, link_id) VALUES (?, ?)",
            chatId, linkId);
        return true;
    }

    @Transactional
    public void remove(long chatId, long linkId) {
        jdbcTemplate.update(
            "DELETE FROM Chat_Link WHERE chat_id = ? AND link_id = ?",
            chatId, linkId);
    }

    @Transactional
    public LinkDTO remove(long chatId, URI uri){
        Long id = linkRepository.findId(uri.toString());
        if (id == null)
            return null;
        remove(chatId, id);
        return new LinkDTO(id, uri.toString(), null, null);
    }

    @Transactional
    public List<LinkDTO> findAllLinksByChatId(long chatId) {
        String sql = "SELECT l.link_id, l.url, l.updated_at, l.checked_at " +
            "FROM Chat_Link cl " +
            "JOIN Link l ON cl.link_id = l.link_id " +
            "WHERE cl.chat_id = ?";
        return jdbcTemplate.query(sql, new Object[]{chatId}, (rs, rowNum) ->
            new LinkDTO(rs.getLong("link_id"),
                rs.getString("url"),
                rs.getObject("updated_at", OffsetDateTime.class),
                rs.getObject("checked_at", OffsetDateTime.class))
        );
    }

    @Transactional
    public List<ChatDTO> findByLinkId(long linkId){
        String sql = "SELECT chat_id FROM Chat_Link WHERE link_id = ?";
        return jdbcTemplate.query(sql, new Object[]{linkId}, (rs, rowNum) ->
            new ChatDTO(rs.getLong("chat_id"))
        );
    }

    @Transactional
    public List<ChatLinkDTO> findAll(){
        return jdbcTemplate.query(
            "SELECT chat_id, link_id FROM Chat_Link",
            (rs, rowNum) -> new ChatLinkDTO(
                rs.getLong("chat_id"),
                rs.getLong("link_id")
            )
        );
    }
}

