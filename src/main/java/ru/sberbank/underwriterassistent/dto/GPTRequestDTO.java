package ru.sberbank.underwriterassistent.dto;

import java.util.ArrayList;
import java.util.List;

public class GPTRequestDTO {
    String model;

    List<messagesDTO> messages;


    public GPTRequestDTO(String model, messagesDTO writer){
        this.model = model;
        this.messages = new ArrayList<messagesDTO>(List.of(new messagesDTO[]{writer}));
    }

}
