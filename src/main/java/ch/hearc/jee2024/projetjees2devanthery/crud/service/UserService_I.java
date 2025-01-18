package ch.hearc.jee2024.projetjees2devanthery.crud.service;

import ch.hearc.jee2024.projetjees2devanthery.crud.model.User;

import java.util.Optional;

public interface UserService_I {
    public User create(User user);
    public Iterable<User> getAll();
    public Optional<User> getById(Long id);
    public User update(Long id, User user);
    public void delete(Long id);
}
