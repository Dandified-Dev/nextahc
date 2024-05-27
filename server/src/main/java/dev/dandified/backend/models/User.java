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

import java.time.LocalDate;
import java.util.Date;

@Data
@Document(collection = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    @JsonDeserialize(using = ObjectIdDeserializer.class)
    private ObjectId id;
    private String email;
    private String password;
    private LocalDate creationDate;
    private LocalDate lastLoginDate;
    private String role;
    private String profilePicture;

    public User(String email, String password, LocalDate creationDate, LocalDate lastLoginDate, String role, String profilePicture) {
        this.email = email;
        this.password = password;
        this.creationDate = creationDate;
        this.lastLoginDate = lastLoginDate;
        this.role = role;
        this.profilePicture = profilePicture;
    }
}
