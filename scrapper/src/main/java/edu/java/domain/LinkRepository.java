package edu.java.domain;

import edu.java.dto.db.LinkDTO;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LinkRepository {
    private final JdbcTemplate jdbcTemplate;

    static final String COUNT_QUERY = "SELECT COUNT(*) FROM Link WHERE url = ?";
    static final String ID_QUERY = "SELECT link_id FROM Link WHERE url = ? LIMIT 1";
    static final String UPDATE_AT = "updated_at";
    static final String URL = "url";
    static final String CHECKED_AT = "checked_at";
    static final String LINK_ID = "link_id";

    @Autowired
    public LinkRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public Long add(LinkDTO link) {
        int count = jdbcTemplate.queryForObject(COUNT_QUERY, Integer.class, link.url());
        if (count > 0) {
            return jdbcTemplate.queryForObject(
                ID_QUERY,
                Long.class,
                link.url()
            );
        }
        return jdbcTemplate.queryForObject(
            "INSERT INTO Link (url, updated_at, checked_at) VALUES (?, ?, ?) RETURNING link_id",
            Long.class, link.url(), link.updatedAt(), link.checkedAt());
    }

    @Transactional
    public void remove(String url) {
        jdbcTemplate.update("DELETE FROM Link WHERE url = ?", url);
    }

    @Transactional
    public Long findId(String url) {
        int count = jdbcTemplate.queryForObject(COUNT_QUERY, Integer.class, url);
        if (count == 0) {
            return null;
        }
        return jdbcTemplate.queryForObject(ID_QUERY, Long.class, url);
    }

    @Transactional
    public List<LinkDTO> findAll() {
        return jdbcTemplate.query(
            "SELECT link_id, url, updated_at, checked_at FROM Link",
            (rs, rowNum) -> new LinkDTO(
                rs.getLong(LINK_ID),
                rs.getString(URL),
                rs.getObject(UPDATE_AT, OffsetDateTime.class),
                rs.getObject(CHECKED_AT, OffsetDateTime.class)
            )
        );
    }

    @Transactional
    public List<LinkDTO> findOutdated() {
        String sql = "SELECT link_id, url, updated_at, checked_at "
            + "FROM Link WHERE checked_at <= NOW() - INTERVAL '10 minutes'";
        return jdbcTemplate.query(sql,
            (rs, rowNum) -> new LinkDTO(
                rs.getLong(LINK_ID),
                rs.getString(URL),
                rs.getObject(UPDATE_AT, OffsetDateTime.class),
                rs.getObject(CHECKED_AT, OffsetDateTime.class)
            )
        );
    }

}

