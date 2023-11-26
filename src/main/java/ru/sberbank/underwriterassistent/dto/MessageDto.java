package ru.sberbank.underwriterassistent.dto;


public class MessageDto {
    public String role;
    public String content;
    MessageDto(String role, String content){
        this.role = role;
        this.content = content;
    }

    MessageDto() {}

}
