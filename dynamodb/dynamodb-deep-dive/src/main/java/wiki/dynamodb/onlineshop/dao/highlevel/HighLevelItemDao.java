package wiki.dynamodb.onlineshop.dao.highlevel;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import wiki.dynamodb.onlineshop.domain.ShopItem;

import java.util.Collection;

public class HighLevelItemDao {
    private final DynamoDBMapper dynamoDBMapper;

    public HighLevelItemDao(AmazonDynamoDB client) {
        this.dynamoDBMapper = new DynamoDBMapper(client);
    }

    public ShopItem put(ShopItem shopItem) {
        dynamoDBMapper.save(shopItem);
        return shopItem;
    }

    public ShopItem get(String id) {
        return dynamoDBMapper.load(ShopItem.class, id);
    }

    public void delete(String id) {
        ShopItem shopItem = new ShopItem();
        shopItem.setId(id);

        dynamoDBMapper.delete(shopItem);
    }

    public Collection<ShopItem> getAll() {
        return dynamoDBMapper.scan(ShopItem.class, new DynamoDBScanExpression());
    }

}
