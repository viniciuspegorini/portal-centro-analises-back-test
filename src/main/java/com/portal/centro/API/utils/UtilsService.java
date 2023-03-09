package com.portal.centro.API.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class UtilsService {


    public Date getDate(){
        return new Date();
    }




    public static String parseDateToString(LocalDate dt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dt.format(formatter);
    }



}