----NO SQL
use messagerie;

db.createCollection("messages", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["sender", "id_sender", "receiver", "id_receiver", "content", "date_time_send"],
      properties: {
        sender: {
          bsonType: "string",
          description: "Sender's name",
        },
        id_sender: {
          bsonType: "string",
          description: "Sender's ID",
        },
        receiver: {
          bsonType: "string",
          description: "Receiver's name",
        },
        id_receiver: {
          bsonType: "string",
              : "Receiver's ID",
        },
        content: {
          bsonType: "string",
          description: "Message content",
        },
        date_time_send: {
          bsonType: "date",
          description: "Timestamp of when the message was sent",
        },
      },
    },
  },
  validationLevel: "strict",
  validationAction: "error",
});

// Insert a document without an auto-incrementing field
db.messages.insertOne({
  sender: "John",
  id_sender: "123",
  receiver: "Alice",
  id_receiver: "456",
  content: "Hello, how are you?",
  date_time_send: new Date(),
});

db.createUser(
  {
    user: "admin",
    pwd:  "admin",   // or cleartext password
    roles: [ { role: "readWrite", db: "messagerie" },
             { role: "read", db: "reporting" } ]
  }
)

db.message.find(
    {
        $or: [
            { idSender: "USR0002" },
            { idReceiver: "USR0002" }
        ]
    },
    {
        _id: 0,
        idSender: 1,
        idReceiver: 1,
        sender: 1,
        receiver: 1
    }
).sort({ idSender: 1, idReceiver: 1 });

db.message.find(
    {
        $or: [
            { idSender: "USR0002" },
            { idReceiver: "USR0002" },
            { idSender: "USR0003" },
            { idReceiver: "USR0003" }
        ]
    });

