package com.distributedsystems.client.listener;

import com.distributedsystems.client.requests.MessageDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.BytesMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

@Slf4j
@RequiredArgsConstructor
@Component
public class FriendMessageListener implements MessageListener {

    private final ObjectMapper objectMapper;

    @Override
    @JmsListener(destination = "${app.configs.client.email}")
    public void onMessage(Message message) {
        try {
            MessageDetails messageDetails;
            if (message instanceof ActiveMQTextMessage) {
                final ActiveMQTextMessage amqMessage = (ActiveMQTextMessage) message;
                final String jsonStringMessage = amqMessage.getText();
                messageDetails = objectMapper.readValue(jsonStringMessage, MessageDetails.class);
            } else {
                final BytesMessage bm = (BytesMessage) message;
                final byte[] data = new byte[(int) bm.getBodyLength()];
                bm.readBytes(data);
                final String jsonStringMessage = new String(data);
                messageDetails = objectMapper.readValue(jsonStringMessage, MessageDetails.class);
            }

            log.info("Message from: '{}', message: {}",
                messageDetails.getFromEmail(),
                messageDetails.getBody());
        } catch (Exception e) {
            log.error("Received Exception : " + e);
            e.printStackTrace();
        }
    }
}
