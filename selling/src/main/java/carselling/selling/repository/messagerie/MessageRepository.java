package carselling.selling.repository.messagerie;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import carselling.selling.entity.messagerie.Message;

public interface MessageRepository extends MongoRepository<Message, String>{
    
    @Query(value = "{ $or: [ { idSender: id }, { idReceiver: id } ] }", fields = "{ _id: 0, idSender: 1, idReceiver: 1, sender: 1, receiver: 1 }", sort = "{ idSender: 1, idReceiver: 1 }")
    List<Message> findDistinctMessagesByIdSenderOrIdReceiver(@Param("id") String id);


    @Query(value = "{ $or: [ { idSender: id }, { idReceiver: id }, { idSender: id2 }, { idReceiver: id2 } ] }")
    List<Message> findMessagesByIdSenderOrIdReceiverOrderByDateTimeDesc(@Param("id")String id, @Param("id2")String id2);
}
