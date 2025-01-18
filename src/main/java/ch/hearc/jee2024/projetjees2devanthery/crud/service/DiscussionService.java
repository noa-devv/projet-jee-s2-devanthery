package ch.hearc.jee2024.projetjees2devanthery.crud.service;

import ch.hearc.jee2024.projetjees2devanthery.crud.model.Discussion;
import ch.hearc.jee2024.projetjees2devanthery.crud.repository.DiscussionRepository_I;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Qualifier("discussionService")
public class DiscussionService implements DiscussionService_I {

    private final DiscussionRepository_I repository;

    public DiscussionService(DiscussionRepository_I repository) {
        this.repository = repository;
    }

    @Override
    public Discussion create(Discussion discussion) {
        return repository.save(discussion);
    }

    @Override
    public Iterable<Discussion> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Discussion> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Discussion update(Discussion discussion, Long id) {
        Discussion existingDiscussion = repository.findById(id).orElseThrow(() -> new RuntimeException("Discussion not found with ID: " + id));

        existingDiscussion.setName(discussion.getName());
        existingDiscussion.setSubject(discussion.getSubject());

        return repository.save(existingDiscussion);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
