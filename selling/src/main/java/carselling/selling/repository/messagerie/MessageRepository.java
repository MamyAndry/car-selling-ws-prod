package carselling.selling.repository.messagerie;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import carselling.selling.entity.messagerie.Message;

public interface MessageRepository extends MongoRepository<Message, String>{
    
    List<Message> findByIdSenderOrIdReceiverOrderByDateTimeSendDesc(String idSender, String idReceiver);
}
