package com.example.springbeanscopes.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@Slf4j
@Controller
public class MySingletonBean {
    private ObjectProvider<MyPrototypeBean> beanObjectProvider;

    @Autowired
    private MyPrototypeBean bean;

    public MySingletonBean() {
        log.info("Singleton instance created");
    }

    public void showMessage() {
        MyPrototypeBean bean = beanObjectProvider.getIfAvailable(() -> new MyPrototypeBean("default bean"));
        //bean = new MyPrototypeBean("test");
        //log.info("{}, prototype instance id: {}", bean.getMessage(), System.identityHashCode(bean));
        log.info(bean.getDateTime());
    }

}
