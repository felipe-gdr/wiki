package wiki;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder
                .standard()
                .withRegion(Regions.AP_SOUTHEAST_2)
                .build();

        Map<String, AttributeValue> userItem = new HashMap<>();

        userItem.put("Id", new AttributeValue().withS("4"));
        userItem.put("Name", new AttributeValue().withS("Bob"));
        userItem.put("Email", new AttributeValue().withS("bob@example.com"));

        PutItemRequest request = new PutItemRequest(
                "Users",
                userItem
        );

        client.putItem(request);
    }
}
