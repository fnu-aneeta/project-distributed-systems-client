package com.distributedsystems.client.listener;

import com.distributedsystems.client.resources.MessageResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@Slf4j
@RequiredArgsConstructor
@Component
public class FriendMessageListener implements MessageListener {

    @Override
    @JmsListener(destination = "${app.configs.client.email}")
    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            MessageResource messageResource = (MessageResource) objectMessage.getObject();
            log.info("From: {}, Received Message: {}",
                    messageResource.getFromEmail(),
                    messageResource.getMessage());
        } catch (Exception e) {
            log.error("Received Exception : " + e);
            e.printStackTrace();
        }
    }
}
