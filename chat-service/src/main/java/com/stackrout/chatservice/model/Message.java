package com.stackrout.chatservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
@Document(collection="messageDetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {



   @Id
    private long messageId;

    private String message;
   // private String reply;
    private String senderEmail;
   // private String adminEmail;
}
