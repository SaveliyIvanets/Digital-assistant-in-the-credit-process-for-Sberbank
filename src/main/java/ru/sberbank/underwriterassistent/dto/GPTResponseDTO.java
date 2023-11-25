package ru.sberbank.underwriterassistent.dto;

import ru.sberbank.underwriterassistent.dto.ChoiceDTO;

import java.util.List;

public class GPTResponseDTO {
    List<ChoiceDTO> choice;
    Integer created;
    String model;
    UsageDTO usage;
    String object;





}
