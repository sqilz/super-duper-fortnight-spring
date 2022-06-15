package com.example;

import com.example.config.AppConfig;
import com.example.springbeanscopes.domain.MySingletonBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class SpringBeanScopesApplication {


    static AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBeanScopesApplication.class, args);
    }

    @GetMapping("/prototype")
    public static void extracted() {
        MySingletonBean bean = configApplicationContext.getBean(MySingletonBean.class);
        bean.showMessage();

        MySingletonBean bean2 = configApplicationContext.getBean(MySingletonBean.class);
        bean2.showMessage();
    }

}
