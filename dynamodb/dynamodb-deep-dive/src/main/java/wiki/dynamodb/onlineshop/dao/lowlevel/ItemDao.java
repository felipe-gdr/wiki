package wiki.dynamodb.onlineshop.dao.lowlevel;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import wiki.dynamodb.onlineshop.domain.ShopItem;

import java.util.HashMap;
import java.util.Map;

public class ItemDao {
    private final AmazonDynamoDB dynamoDBClient;

    public ItemDao(AmazonDynamoDB dynamoDBClient) {
        this.dynamoDBClient = dynamoDBClient;
    }

    public void putShopItem(ShopItem shopItem) {
        Map<String, AttributeValue> itemMap = new HashMap<>();

        itemMap.put("id", new AttributeValue().withS(shopItem.getId()));
        itemMap.put("description", new AttributeValue().withS(shopItem.getDescription()));
        itemMap.put("name", new AttributeValue().withS(shopItem.getName()));
        itemMap.put("amount", new AttributeValue().withN(shopItem.getAmount().toString()));

        PutItemRequest putItemRequest = new PutItemRequest("ShopItem", itemMap);

        this.dynamoDBClient.putItem(putItemRequest);
    }
}
