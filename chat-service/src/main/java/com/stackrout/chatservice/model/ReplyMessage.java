package com.stackrout.chatservice.model;

public class ReplyMessage {
    private Message message;
    private String reply;
    private String replyBy="admin@taptocure.com";

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getReplyBy() {
        return replyBy;
    }


}
