package edu.java.domain;

import edu.java.dto.db.LinkDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public class LinkRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LinkRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public Long add(LinkDTO link) {
        String sql = "SELECT COUNT(*) FROM Link WHERE url = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, link.url());
        if(count > 0)
            return jdbcTemplate.queryForObject("SELECT link_id FROM Link WHERE url = ? LIMIT 1", Long.class, link.url());
        return jdbcTemplate.queryForObject(
            "INSERT INTO Link (url, updated_at, checked_at) VALUES (?, ?, ?) RETURNING link_id",
            Long.class, link.url(), link.updatedAt(), link.checkedAt());
    }

    @Transactional
    public void remove(String url) {
        jdbcTemplate.update("DELETE FROM Link WHERE url = ?", url);
    }

    @Transactional
    public Long findId(String url){
        String sql = "SELECT COUNT(*) FROM Link WHERE url = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, url);
        if(count == 0)
            return null;
        return jdbcTemplate.queryForObject("SELECT link_id FROM Link WHERE url = ? LIMIT 1", Long.class, url);
    }

    @Transactional
    public List<LinkDTO> findAll() {
        return jdbcTemplate.query(
            "SELECT link_id, url, updated_at, checked_at FROM Link",
            (rs, rowNum) -> new LinkDTO(
                rs.getLong("link_id"),
                rs.getString("url"),
                rs.getObject("updated_at", OffsetDateTime.class),
                rs.getObject("checked_at", OffsetDateTime.class)
            )
        );
    }

    @Transactional
    public List<LinkDTO> findOutdated(){
        String sql = "SELECT link_id, url, updated_at, checked_at FROM Link WHERE checked_at <= NOW() - INTERVAL '10 minutes'";
        return jdbcTemplate.query(sql,
            (rs, rowNum) -> new LinkDTO(
                rs.getLong("link_id"),
                rs.getString("url"),
                rs.getObject("updated_at", OffsetDateTime.class),
                rs.getObject("checked_at", OffsetDateTime.class)
            )
        );
    }

}

