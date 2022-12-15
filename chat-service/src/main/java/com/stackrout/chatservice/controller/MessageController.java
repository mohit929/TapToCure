package com.stackrout.chatservice.controller;


import com.stackrout.chatservice.model.Message;
import com.stackrout.chatservice.model.ReplyMessage;
import com.stackrout.chatservice.repository.MessageRepository;
import com.stackrout.chatservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("api/v1")
public class MessageController {
    @Autowired
    private MessageService messageService;



//    public MessageController(MessageService messageService) {
//        super();
//        this.messageService = messageService;
//    }

    //Generate Message
    @PostMapping("/message")
    public String saveMessage(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }
   // 1.Get-message
    @GetMapping("/get-Message")
    public List<Message> getMessage()

    {
      return messageService.getAllMessage();

    }

    // 1.Reply-message
    @GetMapping("/reply-message/{messageId}/{reply}")
    public ReplyMessage replyMessage(@PathVariable long messageId,@PathVariable String reply)

    {
        ReplyMessage replyMessage= new ReplyMessage();
        Message message= messageService.replyMessage(messageId);
        replyMessage.setMessage(message);
        replyMessage.setReply(reply);
        return replyMessage;

    }
    // 1.Delete-message
    @DeleteMapping("/delete-message/{messageId}")
    public String deleteMessage(@PathVariable long messageId)

    {
        return messageService.deleteMessageByMessageId(messageId);


    }

    //Update-message
    @PutMapping("/update-message")
    public String updateMessage(@RequestBody Message message) {
        return messageService.updateMessage(message);
    }

    // 1.Get-message-ById
    @GetMapping("/get-message-byId/{messageId}")
    public Message getMessage(@PathVariable long messageId)

    {
        return messageService.getMessageByMessageId(messageId);


    }





}




//   //update reply to the question
//    @PutMapping("/reply/{messageId}")
//    public ResponseEntity<Message> updateMessage(@PathVariable("messageId") long messageId, @RequestBody Message message){
//         return new ResponseEntity<Message>(messageService.updateMessage(message, messageId), HttpStatus.OK);
//    }
//
//    //get reply by QuestionId
//   @GetMapping("/reply/{messageId}")
//   public ResponseEntity<Message> getChatByQuestionId(@PathVariable("messageId") long messageId){
//       return new ResponseEntity<Message>(messageService.getMessageByMessageId(messageId), HttpStatus.OK);
//   }
//
//   //delete chat by QuestionId
//    @DeleteMapping("{questionId}")
//    public ResponseEntity<String> deleteMessage(@PathVariable("messageId") long messageId){
//        messageService.deleteMessageByMessageId(messageId);
//        return new ResponseEntity<String>("Message deleted successfully.", HttpStatus.OK);
//    }
//
//   // reply to previous answer
//    @PutMapping("/answer/{messageId}")
//    public ResponseEntity<Message> replyMessage(@PathVariable("MessageId") long messageId
//         ,@RequestBody Message message){
//        return new ResponseEntity<>(messageService.replyMessage(message, messageId), HttpStatus.OK);
//
//    }
//
//    // get chat by productId
//    @GetMapping("{productId}")
//    public ResponseEntity<Message> getMessageByProductId(@PathVariable("productId") long productId){
//        return new ResponseEntity<Message>(messageService.getMessageByProductId(productId), HttpStatus.OK);
//    }


