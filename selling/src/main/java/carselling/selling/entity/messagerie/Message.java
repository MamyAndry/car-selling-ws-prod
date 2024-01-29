package carselling.selling.entity.messagerie;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "message")
public class Message {
    @Id
    String id;
    String sender;
    String idSender;
    String receiver;
    String idReceiver;
    String content;
    Date dateTimeSend;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public String getIdSender() {
        return idSender;
    }
    public void setIdSender(String idSender) {
        this.idSender = idSender;
    }
    public String getReceiver() {
        return receiver;
    }
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    public String getIdReceiver() {
        return idReceiver;
    }
    public void setIdReceiver(String idReceiver) {
        this.idReceiver = idReceiver;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getDateTimeSend() {
        return dateTimeSend;
    }
    public void setDateTimeSend(Date dateTimeSend) {
        this.dateTimeSend = dateTimeSend;
    }
    
    public Message() {
    }

    public Message(String _id, String sender, String id_sender, String receiver, String id_receiver, String content, Date date_time_send) {
        this.setId(_id);
        this.setSender(sender);
        this.setIdSender(id_sender);
        this.setReceiver(receiver);
        this.setIdReceiver(id_receiver);
        this.setContent(content);
        this.setDateTimeSend(date_time_send);
    }

}