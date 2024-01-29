package carselling.selling.service.messagerie;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import carselling.selling.entity.messagerie.Conversation;
import carselling.selling.entity.messagerie.Message;
import carselling.selling.repository.messagerie.MessageRepository;

@Service
public class MessageService {
    @Autowired
    MessageRepository repository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Set<Conversation> getConversations(String id){
        Set<Conversation> res = new HashSet<>();
        List<Message> messages = repository.findByIdSenderOrIdReceiverOrderByDateTimeSendDesc(id, id);
        for (Message message : messages) {
            Conversation conversation = new Conversation();
            if (message.getIdReceiver().equals(id)) {
                conversation.setId(message.getIdSender());
                conversation.setNom(message.getSender());
                res.add(conversation);
            }else{
                conversation.setId(message.getIdReceiver());
                conversation.setNom(message.getReceiver());
                res.add(conversation);
            }
        }
        return res;
    }

    public List<Message> getMessagesBetweenUsers(String userId1, String userId2) {
        Query query = new Query(Criteria.where("idSender").in(Arrays.asList(userId1, userId2))
            .orOperator(Criteria.where("idReceiver").in(Arrays.asList(userId1, userId2))));
        query.with(Sort.by(Sort.Order.asc("dateTimeSend")));
        return mongoTemplate.find(query, Message.class);
    }
}
