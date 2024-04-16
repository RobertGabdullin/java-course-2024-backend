package edu.java.scheduler;

import edu.java.client.BotClient;
import edu.java.client.GitHubClient;
import edu.java.client.StackOverflowClient;
import edu.java.client.TypeLink;
import edu.java.dto.db.ChatDTO;
import edu.java.dto.db.LinkDTO;
import edu.java.dto.request.UpdateRequest;
import edu.java.service.LinkService;
import edu.java.service.jdbc.JdbcLinkService;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class LinkUpdaterScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkUpdaterScheduler.class);

    LinkService linkService;

    @Autowired
    GitHubClient gitHubClient;
    @Autowired
    StackOverflowClient stackOverflowClient;
    @Autowired
    BotClient botClient;


    @Autowired
    LinkUpdaterScheduler(JdbcLinkService jdbcLinkService) {
        linkService = jdbcLinkService;
    }

    @Scheduled(fixedDelayString = "#{@scheduler.interval.toMillis()}")
    public void update() {
        LOGGER.info("Update method is used...");
        List<LinkDTO> list = linkService.findOutdated();
        for (LinkDTO i : list) {
            TypeLink temp = new TypeLink(i.url());
            OffsetDateTime cur = i.updatedAt();
            OffsetDateTime actual;
            List<ChatDTO> chatList = linkService.listByLinkId(i.linkId());
            List<Long> listId = new ArrayList<>();
            for (ChatDTO chatDTO : chatList) {
                listId.add(chatDTO.chatId());
            }
            if (temp.getId() == 1) {
                actual = gitHubClient.getListUpdates(temp.getOwner(), temp.getRep()).getLast().timestamp();
            } else {
                actual = stackOverflowClient.lastUpdate(temp.getId()).getLastUpdate();
            }
            if (cur.isBefore(actual)) {
                botClient.sendUpdate(new UpdateRequest(i.linkId(), URI.create(i.url()), "", listId));
            }
        }
    }

}
