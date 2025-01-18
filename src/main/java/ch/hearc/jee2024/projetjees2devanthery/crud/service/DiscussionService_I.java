package ch.hearc.jee2024.projetjees2devanthery.crud.service;

import ch.hearc.jee2024.projetjees2devanthery.crud.model.Discussion;

import java.util.Optional;

public interface DiscussionService_I {
    public Discussion create(Discussion discussion);
    public Iterable<Discussion> getAll();
    public Optional<Discussion> getById(Long id);
    public Discussion update(Discussion discussion, Long id);
    public void delete(Long id);
}
