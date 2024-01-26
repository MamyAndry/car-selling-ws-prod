package carselling.selling.service.messagerie;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import carselling.selling.entity.messagerie.Conversation;
import carselling.selling.entity.messagerie.Message;
import carselling.selling.repository.messagerie.MessageRepository;

@Service
public class MessageService {
    @Autowired
    MessageRepository repository;
    
    public List<Conversation> getConversations(String id){
        ArrayList<Conversation> res = new ArrayList<>();
        List<Message> messages = repository.findDistinctMessagesByIdSenderOrIdReceiver(id);
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
}
