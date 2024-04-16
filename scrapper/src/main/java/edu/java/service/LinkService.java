package edu.java.service;

import edu.java.dto.db.ChatDTO;
import edu.java.dto.db.LinkDTO;
import java.net.URI;
import java.util.Collection;
import java.util.List;

public interface LinkService {
    void add(long tgChatId, URI url);
    LinkDTO remove(long tgChatId, URI url);
    List<LinkDTO> listAll(long tgChatId);
    List<LinkDTO> findOutdated();
    List<ChatDTO> listByLinkId(long lindId);
}
