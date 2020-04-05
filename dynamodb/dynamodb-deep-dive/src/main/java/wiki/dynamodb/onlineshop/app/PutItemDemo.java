package wiki.dynamodb.onlineshop.app;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import wiki.dynamodb.onlineshop.dao.lowlevel.ItemDao;
import wiki.dynamodb.onlineshop.domain.ShopItem;

public class PutItemDemo {
    public static void main(String[] args) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder
                .standard()
                .withRegion(Regions.AP_SOUTHEAST_2)
                .build();

        ItemDao itemDao = new ItemDao(client);

//        putItem(itemDao);

        final ShopItem item = getItem(itemDao);
        System.out.println(item);
    }

    private static ShopItem getItem(ItemDao itemDao) {
        return itemDao.getShopItem("1");
    }

    private static void putItem(ItemDao itemDao) {
        ShopItem shopItem = new ShopItem(
                "1",
                "Spoon",
                "Musical spoon",
                1001
        );

        itemDao.putShopItem(shopItem);
    }
}
