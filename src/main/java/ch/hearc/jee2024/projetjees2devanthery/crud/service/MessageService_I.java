package ch.hearc.jee2024.projetjees2devanthery.crud.service;

import ch.hearc.jee2024.projetjees2devanthery.crud.model.Message;

import java.util.Optional;

public interface MessageService_I {
    public Message create(Message message, Long idDiscussion);
    public Iterable<Message> getAll();
    public Optional<Message> getById(Long id);
    public Message update(String messageContent, Long id);
    public void delete(Long id);

    public Iterable<Message> getAllByDiscussion(Long id);
}
