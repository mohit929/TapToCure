package com.stackroute.chatservice.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private long messageId;
    private long productId;
    private String message;
    private List reply;
    private String userEmail;
}
