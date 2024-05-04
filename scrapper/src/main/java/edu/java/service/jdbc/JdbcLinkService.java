package edu.java.service.jdbc;

import edu.java.domain.ChatLinkRepository;
import edu.java.domain.LinkRepository;
import edu.java.dto.db.ChatDTO;
import edu.java.dto.db.LinkDTO;
import edu.java.service.LinkService;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JdbcLinkService implements LinkService {

    @Autowired
    private LinkRepository linkRepository;
    @Autowired
    private ChatLinkRepository chatLinkRepository;

    public void add(long tgChatId, URI url) {
        LinkDTO linkDTO = new LinkDTO(null, url.toString(), OffsetDateTime.now(), OffsetDateTime.now());
        long idx = linkRepository.add(linkDTO);
        chatLinkRepository.add(tgChatId, idx);
    }

    public LinkDTO remove(long tgChatId, URI url) {
        return chatLinkRepository.remove(tgChatId, url);
    }

    public List<LinkDTO> listAll(long tgChatId) {
        return chatLinkRepository.findAllLinksByChatId(tgChatId);
    }

    public List<LinkDTO> findOutdated() {
        return linkRepository.findOutdated();
    }

    public List<ChatDTO> listByLinkId(long linkId) {
        return chatLinkRepository.findByLinkId(linkId);
    }

}
