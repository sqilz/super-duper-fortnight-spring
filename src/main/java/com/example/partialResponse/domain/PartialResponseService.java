package com.example.partialResponse.domain;

import com.example.partialResponse.api.dto.MyResponse;
import com.example.partialResponse.api.dto.SomeOtherResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
class PartialResponseService {


    public List<MyResponse> getSomeData() {
        return List.of(
                MyResponse.builder().id("1").name("Joe").build(),
                MyResponse.builder().id("2").name("John").build()
        );
    }

    public List<SomeOtherResponse> getSomeOtherData() {
        return List.of(
                SomeOtherResponse.builder().surname("1").age(22).build(),
                SomeOtherResponse.builder().surname("2").age(11).build()
        );
    }

}
