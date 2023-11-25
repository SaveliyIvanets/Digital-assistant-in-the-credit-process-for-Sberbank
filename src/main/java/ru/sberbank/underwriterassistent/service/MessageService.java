package ru.sberbank.underwriterassistent.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.underwriterassistent.dto.ChoiceDto;
import ru.sberbank.underwriterassistent.dto.GptRequestDto;
import ru.sberbank.underwriterassistent.dto.MessageDto;
import ru.sberbank.underwriterassistent.repo.MessageRepo;
import ru.sberbank.underwriterassistent.service.gpt.GptClient;

@Service
public class MessageService {
    @Autowired
    MessageRepo messageRepo;

    @Autowired
    GptClient gptClient;

    public List<MessageDto> getMessages(int applicationId) {
        return messageRepo.getMessages(applicationId);
    }

    public void saveMessage(int applicationId, MessageDto messageDto) {
        messageRepo.saveMessage(applicationId, messageDto);
        GptRequestDto request = new GptRequestDto(
                "model_name",
                messageDto
        );
        try {
            var response = gptClient.sendMessage(request);
            var choices = response.choice;
            for (ChoiceDto choice : choices) {
                if (choice.messages.size() > 0) {
                    var message = choice.messages.getLast();
                    messageRepo.saveMessage(applicationId, message);
                }
            }
        } catch (IOException e) {
            System.out.println("катастрофа");
        }
    }
}
