package com.example.demo.dto;


public class ErrorResponseDto extends ResponseDto {
    public ErrorResponseDto() {
        super("R004", "문제가 발생했습니다.");
    }
}
