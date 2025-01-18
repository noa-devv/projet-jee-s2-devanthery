package ch.hearc.jee2024.projetjees2devanthery.crud;

import ch.hearc.jee2024.projetjees2devanthery.crud.model.Message;
import ch.hearc.jee2024.projetjees2devanthery.crud.service.DiscussionService;
import ch.hearc.jee2024.projetjees2devanthery.crud.model.Discussion;
import ch.hearc.jee2024.projetjees2devanthery.crud.service.MessageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;
import java.util.logging.Logger;

@RestController
@RequestMapping("/discussions")
public class DiscussionController {
    private static final Logger LOGGER = Logger.getLogger(DiscussionController.class.getName());

    private final DiscussionService discussionService;
    private final MessageService messageService;

    public DiscussionController(@Qualifier("discussionService") DiscussionService discussionService, MessageService messageService) {
        this.discussionService = discussionService;
        this.messageService = messageService;
    }

    //GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<Discussion> getDiscussionById(@PathVariable Long id) {
        LOGGER.info("Fetching discussion with ID: " + id);
        return discussionService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //GET ALL
    @GetMapping
    public ResponseEntity<Iterable<Discussion>> getAllDiscussions() {
        LOGGER.info("Fetching all discussions");
        return ResponseEntity.ok(discussionService.getAll());
    }

    //POST
    @PostMapping
    public ResponseEntity<Discussion> createDiscussion(@RequestBody Discussion discussion) {
        LOGGER.info("Creating a new discussion: " + discussion);
        try {
            return ResponseEntity.status(201).body(discussionService.create(discussion));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<Discussion> updateDiscussion(@PathVariable Long id, @RequestBody Discussion discussion) {
        LOGGER.info("Updating discussion with ID: " + id);
        try {
            return ResponseEntity.ok(discussionService.update(discussion, id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscussion(@PathVariable Long id) {
        LOGGER.info("Deleting discussion with ID: " + id);
        try {
            discussionService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //GET MESSAGES
    @GetMapping("/{id}/messages")
    public ResponseEntity<Iterable<Message>> getAllMessages(@PathVariable Long id) {
        LOGGER.info("Fetching all messages for discussion ID: " + id);
        try {
            return ResponseEntity.ok(messageService.getAllByDiscussion(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //POST MESSAGES
    @PostMapping("/{id}/messages")
    public ResponseEntity<Message> postMessages(@PathVariable Long id, @RequestBody Message message) {
        LOGGER.info("Posting a message for discussion ID: " + id);
        try {
            return ResponseEntity.status(201).body(messageService.create(message, id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //PUT MESSAGES
    @PutMapping("/{discussionId}/messages/{messageId}")
    public ResponseEntity<Message> putMessages(@PathVariable Long discussionId, @PathVariable Long messageId, @RequestBody String messageContent) {
        LOGGER.info("Editing message ID: " + messageId + " for discussion ID: " + discussionId);
        try {
            return ResponseEntity.ok(messageService.update(messageContent, messageId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //DELETE MESSAGES
    @DeleteMapping("/{discussionId}/messages/{messageId}")
    public ResponseEntity<Void> deleteMessages(@PathVariable Long discussionId, @PathVariable Long messageId) {
        LOGGER.info("Deleting message ID: " + messageId + " for discussion ID: " + discussionId);
        try {
            messageService.delete(messageId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
