package dev.dandified.backend.repositories;

import dev.dandified.backend.models.Chat;
import dev.dandified.backend.models.Message;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<Chat, ObjectId> {

    Chat findById(String chatId);
}
