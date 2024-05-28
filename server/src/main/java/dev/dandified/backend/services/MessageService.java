package dev.dandified.backend.services;

import com.mongodb.client.result.UpdateResult;
import dev.dandified.backend.models.Chat;
import dev.dandified.backend.models.Message;
import dev.dandified.backend.models.User;
import dev.dandified.backend.repositories.MessageRepository;
import dev.dandified.backend.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
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

    public Optional<Message> single(String id) {
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


    public Message deleteMessage(String messageId) {
        // Retrieve the message by ID and ensure it exists
        return messageRepository.findById(messageId).map(message -> {
            // Delete the message from the Message collection
            mongoTemplate.remove(new Query(Criteria.where("id").is(new ObjectId(messageId))), Message.class);

            // Remove the message ID from the messageIds array in the Chat documents
            Query chatQuery = new Query(Criteria.where("messageIds").is(new ObjectId(messageId)));
            Update update = new Update().pull("messageIds", new ObjectId(messageId)); // Use ObjectId here
            UpdateResult result = mongoTemplate.updateMulti(chatQuery, update, Chat.class);

            // Return the deleted message
            return message;
        }).orElseThrow(() -> new NoSuchElementException("Message not found with id: " + messageId));
    }


}
