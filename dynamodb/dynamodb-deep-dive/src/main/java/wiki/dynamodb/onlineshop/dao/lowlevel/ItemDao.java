package wiki.dynamodb.onlineshop.dao.lowlevel;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.ItemCollectionSizeLimitExceededException;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughputExceededException;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.TableNotFoundException;
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

        try {
            this.dynamoDBClient.putItem(putItemRequest);
        } catch (TableNotFoundException ex) {
            // ignore
        } catch (ProvisionedThroughputExceededException ex) {
            // ignore
        } catch (ItemCollectionSizeLimitExceededException ex) {
            // ignore
        }
        // There are many other exceptions
    }

    public ShopItem getShopItem(final String id) {
        Map<String, AttributeValue> key = new HashMap<>();

        key.put("id", new AttributeValue().withS(id));

        GetItemRequest request = new GetItemRequest()
                .withTableName("ShopItem")
                .withKey(key)
                .withConsistentRead(true);

        final GetItemResult getItemResult = this.dynamoDBClient.getItem(request);
        final Map<String, AttributeValue> item = getItemResult.getItem();

        final ShopItem shopItem = new ShopItem();

        shopItem.setId(item.get("id").getS());
        shopItem.setName(item.get("name").getS());
        shopItem.setDescription(item.get("description").getS());
        shopItem.setAmount(Integer.valueOf(item.get("amount").getS()));

        return shopItem;
    }
}
