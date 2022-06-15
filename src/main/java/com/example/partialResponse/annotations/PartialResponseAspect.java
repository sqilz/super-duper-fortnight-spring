package com.example.partialResponse.annotations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@ControllerAdvice
@RequiredArgsConstructor
public class PartialResponseAspect {
    private final MappingJackson2HttpMessageConverter converter;
    private final ObjectMapper objectMapper;


    @Pointcut("@annotation(com.example.partialResponse.annotations.PartialResponse)")
    public void modifyResponse() {

    }

    @AfterReturning("modifyResponse()")
    public void modifyResponse(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        PartialResponse.FilterField filterField = method.getAnnotation(PartialResponse.class).value();
        ObjectMapper om = objectMapper.copy()
                .addMixIn(filterField.type(), FilterMixIn.class)
                .setFilterProvider(new SimpleFilterProvider()
                        .addFilter(
                                "soDynamic",
                                SimpleBeanPropertyFilter.serializeAllExcept(filterField.fields())
                        ));
        converter.setObjectMapper(om);
    }
}
