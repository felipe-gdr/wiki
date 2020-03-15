package wiki.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class MongodbTest {
    private static MongoCollection<Document> users;
    private static MongoCollection<Document> todos;

    @BeforeClass
    public static void beforeClass() {
        MongoClient mongoClient = MongoClients.create();

        MongoDatabase database = mongoClient.getDatabase("todo-app");

        users = database.getCollection("users");
        todos = database.getCollection("todos");
    }

    @Test
    public void addUser() {
        String identifier = UUID.randomUUID().toString();

        Document document = new Document("username", "user-" + identifier)
                .append("name", "User " + identifier)
                .append("email", "user-" + identifier + "@company.com");

        users.insertOne(document);
    }

    @Test
    public void addTodo() {
        String identifier = UUID.randomUUID().toString();

        Document document = new Document("title", "Todo " + identifier)
                .append("description", "This is todo " + identifier);

        todos.insertOne(document);
    }

    @Test
    public void updateUser() {
        users.updateOne(
                eq("username", "user-46554ca1-85bb-4efe-91a3-5d24b568c776"),
                new Document("$set", new Document("updated", new Date()))
        );
    }

    @Test
    public void updateInexistentUserDoesntThrowException() {
        users.updateOne(
                eq("username", "user-blah-blah-blah"),
                new Document("$set", new Document("updated", new Date()))
        );
    }

    @Test
    public void deleteInexistentUserDoesntThrowException() {
        users.deleteOne(eq("username", "user-blah-blah-blah"));
    }
}
