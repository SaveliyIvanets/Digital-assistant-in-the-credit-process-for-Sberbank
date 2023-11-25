package ru.sberbank.underwriterassistent.dto;

public class messagesDTO {
    String role;
    String content;
    messagesDTO(String role, String content){
        this.role = role;
        this.content = content;
    }

}
