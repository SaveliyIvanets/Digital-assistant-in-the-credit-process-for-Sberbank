package ru.sberbank.underwriterassistent.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import ru.sberbank.underwriterassistent.dto.MessageDto;

@Repository
public class MessageRepo {
    HashMap<Integer, List<MessageDto>> messages = new HashMap<>();

    public List<MessageDto> getMessages(int applicationId) {
        if (messages.containsKey(applicationId)) {
            return messages.get(applicationId);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public void saveMessage(int applicationId, MessageDto dto) {
        if (!messages.containsKey(applicationId)) {
            messages.put(applicationId, new ArrayList<>());
        }
        messages.get(applicationId).add(dto);
    }
}
