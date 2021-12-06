package com.benzforum.model.message;

import com.benzforum.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "msg_text")
    private String msgText;

    @Column(name = "public_date")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date publicDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "discuss_id")
    private Long discussId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setMsgText(String msg_text) {
        this.msgText = msg_text;
    }

    public void setPublicDate(Date public_date) {
        this.publicDate = public_date;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setDiscussId(Long discuss_id) {
        this.discussId = discuss_id;
    }

    public Long getId() {
        return id;
    }

    public String getMsgText() {
        return msgText;
    }

    public Date getPublicDate() {
        return publicDate;
    }

    public User getAuthor() {
        return author;
    }

    public Long getDiscussId() {
        return discussId;
    }
}
