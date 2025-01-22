package ch.hearc.jee2024.projetjees2devanthery;

import ch.hearc.jee2024.projetjees2devanthery.crud.DiscussionController;
import ch.hearc.jee2024.projetjees2devanthery.crud.model.Discussion;
import ch.hearc.jee2024.projetjees2devanthery.crud.repository.DiscussionRepository_I;
import ch.hearc.jee2024.projetjees2devanthery.crud.service.DiscussionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DiscussionControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void createDiscussion_ShouldReturnCreated() throws Exception {
        String newDiscussion = """
                {
                    "name": "Test Discussion",
                    "subject": "Testing discussions"
                }
                """;

        this.mvc.perform(post("/discussions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newDiscussion))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Discussion"))
                .andExpect(jsonPath("$.subject").value("Testing discussions"));
    }

    @Test
    public void updateDiscussion_ShouldReturnUpdatedDiscussion() throws Exception {
        String updatedDiscussion = """
                {
                    "name": "Updated Name",
                    "subject": "Updated Subject"
                }
                """;

        this.mvc.perform(put("/discussions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedDiscussion))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Name"));
    }

    @Test
    public void deleteDiscussion_ShouldReturnNoContent() throws Exception {
        this.mvc.perform(delete("/discussions/1"))
                .andExpect(status().isNoContent());  //Vérifie que le statut est 204
    }

    @Test
    public void createMessage_ShouldReturnCreated() throws Exception {
        //Discussion du message
        String newDiscussion = """
                {
                    "name": "Test Discussion",
                    "subject": "Testing messages"
                }
                """;

        this.mvc.perform(post("/discussions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newDiscussion))
                .andExpect(status().isCreated());

        //Message
        String newMessage = """
                {
                    "date": "2025-01-18T12:00:00",
                    "content": "Test Message"
                }
                """;

        this.mvc.perform(post("/discussions/1/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newMessage))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.content").value("Test Message"));
    }

    @Test
    public void updateMessage_ShouldReturnUpdatedMessage() throws Exception {
        //Discussion du message
        String newDiscussion = """
                {
                    "name": "Test Discussion",
                    "subject": "Testing message update"
                }
                """;

        this.mvc.perform(post("/discussions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newDiscussion))
                .andExpect(status().isCreated());

        //Message
        String newMessage = """
                {
                    "date": "2025-01-18T12:00:00",
                    "content": "Original Message"
                }
                """;

        this.mvc.perform(post("/discussions/1/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newMessage))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.content").value("Original Message"));

        //Mise a jour du message
        String updatedMessage = "Updated Message";

        this.mvc.perform(put("/discussions/1/messages/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedMessage))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Updated Message"));
    }

    @Test
    public void deleteMessage_ShouldReturnNoContent() throws Exception {
        this.mvc.perform(delete("/discussions/1/messages/1"))
                .andExpect(status().isNoContent());
    }
}
