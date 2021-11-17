package com.benzforum.model.discuss;

import com.benzforum.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "discussions")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Discuss {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Date public_date;

    @Enumerated(EnumType.STRING)
    private DiscussStatus status;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublic_date(Date public_date) {
        this.public_date = public_date;
    }

    public void setStatus(DiscussStatus status) {
        this.status = status;
    }

    public User getAuthor() {
        return author;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getPublic_date() {
        return public_date;
    }

    public DiscussStatus getStatus() {
        return status;
    }

}
