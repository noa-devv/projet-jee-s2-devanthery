package ch.hearc.jee2024.projetjees2devanthery.crud.service;

import ch.hearc.jee2024.projetjees2devanthery.crud.model.Discussion;
import ch.hearc.jee2024.projetjees2devanthery.crud.model.Message;
import ch.hearc.jee2024.projetjees2devanthery.crud.repository.DiscussionRepository_I;
import ch.hearc.jee2024.projetjees2devanthery.crud.repository.MessagesRepository_I;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Qualifier("messagesService")
public class MessageService implements MessageService_I {

    private final MessagesRepository_I repository;
    private final DiscussionRepository_I discussionRepository;

    public MessageService(MessagesRepository_I repository, DiscussionRepository_I discussionRepository) {
        this.repository = repository;
        this.discussionRepository = discussionRepository;
    }

    @Override
    public Message create(Message message, Long discussionId) {
        Discussion discussion = discussionRepository.findById(discussionId).orElseThrow();
        message.setDiscussion(discussion);
        return repository.save(message);
    }

    @Override
    public Iterable<Message> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Message> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Message update(String messageContent, Long id) {
        Message existingMessage = repository.findById(id).orElseThrow(() -> new RuntimeException("Message not found with ID: " + id));

        existingMessage.setContent(messageContent);

        return repository.save(existingMessage);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<Message> getAllByDiscussion(Long id) {
        Discussion discussion = discussionRepository.findById(id).orElseThrow();
        return discussion.getMessages();
    }
}
