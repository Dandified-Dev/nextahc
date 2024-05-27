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

import java.time.LocalDateTime;

@Data
@Document(collection = "messages")
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    @JsonDeserialize(using = ObjectIdDeserializer.class)
    private ObjectId id;
    private String message;
    private LocalDateTime timestamp;
    @DocumentReference
    private User sendBy;

    public Message(String message, LocalDateTime timestamp, User sendBy) {
        this.message = message;
        this.timestamp = timestamp;
        this.sendBy = sendBy;
    }
}
