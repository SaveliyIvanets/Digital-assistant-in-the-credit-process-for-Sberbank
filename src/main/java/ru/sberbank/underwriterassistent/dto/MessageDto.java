package ru.sberbank.underwriterassistent.dto;

public class MessageDto {
    String role;
    String content;
    MessageDto(String role, String content){
        this.role = role;
        this.content = content;
    }

}
