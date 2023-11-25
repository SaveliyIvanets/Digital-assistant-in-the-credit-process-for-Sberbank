package ru.sberbank.underwriterassistent.dto;

import java.util.List;

public class GptResponseDto {
    public List<ChoiceDto> choice;
    public Integer created;
    public String model;
    public UsageDto usage;
    public String object;
}
