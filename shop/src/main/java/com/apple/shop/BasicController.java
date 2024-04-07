package com.apple.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller

public class BasicController {
    @GetMapping("/")
    String hello() {
        return "index.html";
    }

    @GetMapping("/about")
    @ResponseBody
    String about() {
        return "정보";
    }

    @GetMapping("/date")
    @ResponseBody
    String date() {
        Date now = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String format = format1.format(now);
        return format;
    }


}
