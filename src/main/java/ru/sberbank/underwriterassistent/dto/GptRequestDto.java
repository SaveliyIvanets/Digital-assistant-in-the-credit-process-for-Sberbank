package ru.sberbank.underwriterassistent.dto;

import java.util.ArrayList;
import java.util.List;

public class GptRequestDto {
    String model;

    List<MessageDto> messages;


    public GptRequestDto(String model, MessageDto writer){
        this.model = model;
        this.messages = new ArrayList<MessageDto>(List.of(new MessageDto[]{writer}));
    }

}
