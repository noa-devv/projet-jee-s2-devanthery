package ch.hearc.jee2024.projetjees2devanthery.crud.repository;

import ch.hearc.jee2024.projetjees2devanthery.crud.model.Discussion;
import ch.hearc.jee2024.projetjees2devanthery.crud.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessagesRepository_I extends CrudRepository<Message,Long> {
    List<Message> findByDiscussion(Discussion discussion);
}
