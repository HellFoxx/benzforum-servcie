package com.benzforum.dto.newsitem;

import com.benzforum.dto.user.UserDto;

import lombok.Data;

import java.util.Date;

@Data
public class NewsItemDto {
    private long id;
    private String title;
    private String text;
    private String imgSrc;
    private String externReff;
    //private UserDto author;
    private Date publicDate;

    public NewsItemDto() {

    }

    public NewsItemDto(long id, String title, String text, String imgSrc, String externReff, Date publicDate) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.imgSrc = imgSrc;
        this.externReff = externReff;
        this.publicDate = publicDate;
    }

}
