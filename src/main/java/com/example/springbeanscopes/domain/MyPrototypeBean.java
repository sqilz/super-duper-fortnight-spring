package com.example.springbeanscopes.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;

@Data
@Builder
@Slf4j
@AllArgsConstructor
public class MyPrototypeBean {
    private String dateTimeString = LocalDateTime.now().toString();

    public MyPrototypeBean() {
        log.info("MyPrototypeBean instance created");
    }


    public String getDateTime() {
        return dateTimeString;
    }
}
