package carselling.selling.repository.messagerie;

import org.springframework.data.mongodb.repository.MongoRepository;

import carselling.selling.entity.messagerie.Message;

public interface MessageRepository extends MongoRepository<Message, String>{
    
}
