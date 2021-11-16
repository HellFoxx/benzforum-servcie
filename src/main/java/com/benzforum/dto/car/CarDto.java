package com.benzforum.dto.car;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
public class CarDto {
    private String mark;
    private String model;
    private String bodyNum;
    private int yearOfManuf;
    //private float engineDispl;
}
