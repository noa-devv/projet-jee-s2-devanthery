package ch.hearc.jee2024.projetjees2devanthery;

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
public class MessageControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getMessagesByDiscussionId_ShouldReturnMessages() throws Exception {
        this.mvc.perform(get("/discussions/1/messages"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].content").exists());
    }

    @Test
    public void createMessage_ShouldReturnCreated() throws Exception {
        String newMessage = """
                {
                    "date": "2025-01-18T12:00:00"
                    "content": "Test Message",
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
