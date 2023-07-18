package com.example.demo.controller;

import com.example.demo.dto.DensityDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.DensityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DensityController {
    private final DensityService densityService;
    @PostMapping("/density/updateDensity")
    public ResponseDto updateDensity(@RequestBody DensityDto densityDto) {
        System.out.println("densityDto.getStoreId() = " + densityDto.getStoreId());
        System.out.println("densityDto.getDensity() = " + densityDto.getDensity());
        System.out.println("densityDto.getWhen() = " + densityDto.getWhen());
        densityService.updateDensity(densityDto);
        return new ResponseDto("D001", "밀집도가 갱신되었습니다.");
    }
}
