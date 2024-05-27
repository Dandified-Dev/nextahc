package dev.dandified.backend.services;

import dev.dandified.backend.models.Chat;
import dev.dandified.backend.models.Message;
import dev.dandified.backend.models.User;
import dev.dandified.backend.repositories.MessageRepository;
import dev.dandified.backend.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Message> allMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> single(ObjectId id) {
        return messageRepository.findById(id);
    }

    public Message createMessage(String messageContent, String userId, String chatId) {
        Optional<User> sendByOpt = userRepository.findById(userId);
        User sendBy = sendByOpt.orElse(null);  // Handle case where user is not found

        Message message = messageRepository.insert(new Message(messageContent, LocalDateTime.now(), sendBy));

        mongoTemplate.update(Chat.class)
                .matching(Criteria.where("id").is(chatId))
                .apply(new Update().push("messageIds").value(message))
                .first();

        return message;
    }
}
