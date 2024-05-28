package dev.dandified.backend.services;

import com.mongodb.client.result.UpdateResult;
import dev.dandified.backend.models.Chat;
import dev.dandified.backend.models.User;
import dev.dandified.backend.repositories.ChatRepository;
import dev.dandified.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Chat> allChats() {
        return chatRepository.findAll();
    }

    public Chat createChat(String name, String picture) {
        return chatRepository.insert(new Chat(name, LocalDate.now(), picture));
    }

    public Chat addParticipants(String chatId, List<String> participantIds) {
        for (String participantId :
                participantIds) {
            Optional<User> participant = userRepository.findById(participantId);
            mongoTemplate.update(Chat.class)
                    .matching(Criteria.where("id").is(chatId))
                    .apply(new Update().push("participantIds").value(participant.get()))
                    .first();
        }
        return chatRepository.findById(chatId);
    }

    public Chat removeParticipant(String chatId, String participantId) {
        Optional<User> participant = userRepository.findById(participantId);
        mongoTemplate.update(Chat.class)
                .matching(Criteria.where("id").is(chatId))
                .apply(new Update().pull("participantIds", participant.get()))
                .first();
        return chatRepository.findById(chatId);
    }

}
