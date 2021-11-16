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

    private String title;

    private String newsText;

    private String imgSrc;

    private String externReff;

    private Date publicDate;

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
