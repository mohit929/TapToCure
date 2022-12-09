package com.stackrout.chatservice.service;

import com.stackrout.chatservice.model.Message;

public interface MessageService {
    Message saveMessage(Message message);
    Message updateMessage(Message message, long messageId);
    Message getMessageByMessageId(long messageId);
    void deleteMessageByMessageId(long messageId);
    Message replyMessage(Message message, long messageId);
    Message getMessageByProductId(long productId);

}
