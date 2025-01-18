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
                .andExpect(jsonPath("$.name").value("Test Discussion"));
    }

    @Test
    public void getDiscussionById_ShouldReturnDiscussion() throws Exception {
        this.mvc.perform(get("/discussions/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").exists());
    }

    @Test
    public void getDiscussionById_NotFound_ShouldReturn404() throws Exception {
        this.mvc.perform(get("/discussions/999"))
                .andExpect(status().isNotFound());
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
                .andExpect(status().isNoContent());  // Vérifie que le statut est 204
    }
}
