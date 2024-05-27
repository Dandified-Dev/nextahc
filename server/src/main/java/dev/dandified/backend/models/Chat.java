package dev.dandified.backend.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.dandified.backend.ObjectIdDeserializer;
import dev.dandified.backend.ObjectIdSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "chats")
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    @JsonDeserialize(using = ObjectIdDeserializer.class)
    private ObjectId id;
    private String name;
    private LocalDate creationDate;

    @DocumentReference
    private List<User> userIds;

    @DocumentReference
    private List<Message> messages;
    private String picture;
    private String string;

    public Chat(String name, LocalDate creationDate, String picture) {
        this.name = name;
        this.creationDate = creationDate;
        this.picture = picture;
    }
}
