package com.stackrout.chatservice.service;

import com.stackrout.chatservice.model.Message;

import java.util.List;

public interface MessageService {
    String saveMessage(Message message);
    String updateMessage(Message message);
    Message getMessageByMessageId(long messageId);
    String deleteMessageByMessageId(long messageId);
    Message replyMessage( long messageId);
    public List<Message> getAllMessage();


}
