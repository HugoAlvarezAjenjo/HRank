package es.hugoalvarezajenjo.hrank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final SimpMessagingTemplate template;
    public void notifyDisplaysToUpdate() {
        this.template.convertAndSend("/topic/display-updates", "update");
    }
}
