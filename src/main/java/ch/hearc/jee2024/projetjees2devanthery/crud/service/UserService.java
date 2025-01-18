package ch.hearc.jee2024.projetjees2devanthery.crud.service;

import ch.hearc.jee2024.projetjees2devanthery.crud.model.User;
import ch.hearc.jee2024.projetjees2devanthery.crud.repository.UserRepository_I;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Qualifier("userService")
public class UserService implements UserService_I {

    private final UserRepository_I repository;

    public UserService(UserRepository_I repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public Iterable<User> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public User update(Long id, User user) {
        repository.deleteById(id);
        return repository.save(user);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
