package com.benzforum.model.newsitem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "news")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class NewsItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "news_text")
    private String newsText;

    @Column(name = "img_src")
    private String imgSrc;

    @Column(name = "extern_reff")
    private String externReff;

    @Column(name = "public_date")
    private Date publicDate;

    @Column(name = "author_id")
    private Long authorId;

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNewsText(String newsText) {
        this.newsText = newsText;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public void setExternReff(String externReff) {
        this.externReff = externReff;
    }

    public void setPublicDate(Date publicDate) {
        this.publicDate = publicDate;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNewsText() {
        return newsText;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public String getExternReff() {
        return externReff;
    }

    public Date getPublicDate() {
        return publicDate;
    }
}
