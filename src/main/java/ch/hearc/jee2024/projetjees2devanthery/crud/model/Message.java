package ch.hearc.jee2024.projetjees2devanthery.crud.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;
    private String content;

    @ManyToOne
    @JoinColumn(name="discussion_id", nullable=false)
    @JsonBackReference
    private Discussion discussion;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=true)
    private User user;

    public Message() {}

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public LocalDateTime getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDiscussion(Discussion discussion) {
        this.discussion = discussion;
    }

    public Discussion getDiscussion() {
        return discussion;
    }
}
