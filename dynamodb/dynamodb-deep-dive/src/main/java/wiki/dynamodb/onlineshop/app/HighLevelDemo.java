package wiki.dynamodb.onlineshop.app;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import wiki.dynamodb.onlineshop.dao.highlevel.HighLevelItemDao;
import wiki.dynamodb.onlineshop.domain.ShopItem;

import java.util.Collection;

public class HighLevelDemo {
    public static void main(String[] args) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder
                .standard()
                .withRegion(Regions.AP_SOUTHEAST_2)
                .build();

        HighLevelItemDao highLevelItemDao = new HighLevelItemDao(client);


        final ShopItem shopItem = putItem(highLevelItemDao);
        System.out.println("Item added. Id: " + shopItem.getId());

        final ShopItem item = getItem(highLevelItemDao, shopItem.getId());

        System.out.println(item);

        final Collection<ShopItem> allItems = highLevelItemDao.getAll();

        System.out.println("All items: " + allItems.size());

        if (!allItems.isEmpty()) {
            highLevelItemDao.delete(allItems.iterator().next().getId());
        }

        final Collection<ShopItem> allItemsAfterDeletion = highLevelItemDao.getAll();

        System.out.println("All items after deletion: " + allItemsAfterDeletion.size());
    }

    private static ShopItem getItem(HighLevelItemDao itemDao, String id) {
        return itemDao.get(id);
    }

    private static ShopItem putItem(HighLevelItemDao itemDao) {
        ShopItem shopItem = new ShopItem();

        shopItem.setName("Spoon");
        shopItem.setDescription("Musical Spoon");
        shopItem.setAmount(1001);

        return itemDao.put(shopItem);
    }
}
