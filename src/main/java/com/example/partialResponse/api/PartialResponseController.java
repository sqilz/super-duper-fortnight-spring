package com.example.partialResponse.api;

import com.example.partialResponse.annotations.PartialResponse;
import com.example.partialResponse.api.dto.MyResponse;
import com.example.partialResponse.api.dto.SomeOtherResponse;
import com.example.partialResponse.domain.PartialResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
class PartialResponseController {
    private final PartialResponseService partialResponseService;

    @PartialResponse(
            @PartialResponse.FilterField(type = MyResponse.class, fields = "name")
    )
    @GetMapping
    public List<MyResponse> getFiltered() {
        return this.partialResponseService.getSomeData();
    }

    @GetMapping("/b")
    public List<MyResponse> getUnfiltered() {
        return this.partialResponseService.getSomeData();
    }

    @PartialResponse(
            @PartialResponse.FilterField(type = SomeOtherResponse.class, fields = "age")
    )
    @GetMapping("/a")
    public List<SomeOtherResponse> getFilteredDifferentObj() {
        return this.partialResponseService.getSomeOtherData();
    }


}
