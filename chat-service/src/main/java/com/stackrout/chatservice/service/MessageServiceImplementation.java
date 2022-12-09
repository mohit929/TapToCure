package com.stackrout.chatservice.service;

import com.stackrout.chatservice.exception.MessageNotFoundException;
import com.stackrout.chatservice.model.Message;
import com.stackrout.chatservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MessageServiceImplementation implements MessageService{
    @Autowired
    private MessageRepository messageRepository;


    @Autowired
    private MessageService messageService;

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message updateMessage(Message message, long messageId) {
        //we need to check whether message in database is present or not

        Message existingMessage = messageRepository.findById(messageId).orElseThrow(
                () -> new MessageNotFoundException("Message with Id" + messageId + "not Found"));
        existingMessage.setReply(message.getReply());

        //save existing message
        messageRepository.save(existingMessage);
        return existingMessage;

    }

    @Override
    public Message getMessageByMessageId(long messageId) {
        Optional<Message> message = messageRepository.findById(messageId);
        if(message.isPresent()){
            return message.get();
        }else{
            throw new MessageNotFoundException("No reply found by Question with Id " + messageId);
        }

    }

    @Override
    public void deleteMessageByMessageId(long messageId) {
        //check whether a chat exist in db
        messageRepository.findById(messageId).orElseThrow(()
                -> new MessageNotFoundException("There is no chat found by Id" + messageId));
        messageRepository.deleteById(messageId);
    }

    @Override
    public Message replyMessage(Message message, long messageId) {
        Message existingMessage = messageRepository.findById(messageId).orElseThrow(
                () -> new MessageNotFoundException("Chat does not Exist with Id" +messageId));

        existingMessage.setReply(message.getReply());
        //save
        messageRepository.save(existingMessage);
        return existingMessage;
    }

    @Override
    public Message getMessageByProductId(long productId) {
        Optional <Message> message = messageRepository.findById(productId);

        return messageRepository.findById(productId).orElseThrow(()
                -> new MessageNotFoundException("Chat is not present with Product Id" + productId));
    }



}
