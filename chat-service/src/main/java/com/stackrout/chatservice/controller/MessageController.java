package com.stackrout.chatservice.controller;


import com.stackrout.chatservice.model.Message;
import com.stackrout.chatservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("api/v1")
public class MessageController {
     @Autowired
     private MessageService messageService;

    public MessageController(MessageService messageService) {
        super();
        this.messageService = messageService;
    }

   //build add question rest api
    @PostMapping("/message")
    public ResponseEntity<Message> saveMessage(@RequestBody Message message){
        return new ResponseEntity<Message>(messageService.saveMessage(message), HttpStatus.CREATED);
    }
   //update reply to the question
    @PutMapping("/reply/{messageId}")
    public ResponseEntity<Message> updateMessage(@PathVariable("messageId") long messageId, @RequestBody Message message){
         return new ResponseEntity<Message>(messageService.updateMessage(message, messageId), HttpStatus.OK);
    }

    //get reply by QuestionId
   @GetMapping("/reply/{messageId}")
   public ResponseEntity<Message> getChatByQuestionId(@PathVariable("messageId") long messageId){
       return new ResponseEntity<Message>(messageService.getMessageByMessageId(messageId), HttpStatus.OK);
   }

   //delete chat by QuestionId
    @DeleteMapping("{questionId}")
    public ResponseEntity<String> deleteMessage(@PathVariable("messageId") long messageId){
        messageService.deleteMessageByMessageId(messageId);
        return new ResponseEntity<String>("Message deleted successfully.", HttpStatus.OK);
    }

   // reply to previous answer
    @PutMapping("/answer/{messageId}")
    public ResponseEntity<Message> replyMessage(@PathVariable("MessageId") long messageId
         ,@RequestBody Message message){
        return new ResponseEntity<>(messageService.replyMessage(message, messageId), HttpStatus.OK);

    }

    // get chat by productId
    @GetMapping("{productId}")
    public ResponseEntity<Message> getMessageByProductId(@PathVariable("productId") long productId){
        return new ResponseEntity<Message>(messageService.getMessageByProductId(productId), HttpStatus.OK);
    }

}
