package com.benzforum.dto.message;

public class MessageDto {

    private String messageText;

    private Long authorId;

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getMessageText() {
        return messageText;
    }

    public Long getAuthorId() {
        return authorId;
    }
}
