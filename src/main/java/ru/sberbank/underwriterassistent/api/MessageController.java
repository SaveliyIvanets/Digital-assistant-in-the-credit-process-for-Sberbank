package ru.sberbank.underwriterassistent.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.underwriterassistent.dto.MessageDto;
import ru.sberbank.underwriterassistent.service.MessageService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping("/{id}")
    public ResponseEntity<List<MessageDto>> getMessage(
            @PathVariable("id") int applicationId
    ) {
        return new ResponseEntity<>(
                messageService.getMessages(applicationId),
                HttpStatus.OK
        );
    }

    @PostMapping("/{id}")
    public ResponseEntity<List<MessageDto>> saveMessage(
            @PathVariable("id") int applicationId,
            @RequestBody MessageDto body
    ) {
        messageService.saveMessage(applicationId, body);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
