import java.util.Random;

public class Library {
    Item[] items, spawnableItems;
    Building[] buildings, spawnableBuildings;
    Enemy[] baddies, spawnableEnemy;
    //this list will hold items that can spawn randomly in a map
    int spawnableMax, spawnableMin;
    Random random;

    public Library() {
        // statchanges
        //  [0] , [1] , [2] , [3] , [4] , [5] , [6] , [7]
        //   hp , att , def , spd , maxH, exp , proW, Projectile H
        items = new Item[10];
        items[0] = new Item(-100, -100, 50, 50, "Red Mushroom", "Magical healing mushroom.\nCan be produced.", new int[]{5, 0, 0, 0, 0, 0}, "ImageAssets/mushroom", 5, 5, 35, 10, 1);
        items[1] = new Item(-100, -100, 25, 15, "Blue waterCan", "Tool for watering plants.", new int[]{0, 1, 0, 2, 0, 0, 10, 10}, "ImageAssets/watercan", 5, 5, 35, 10, true, "shoot");
        items[2] = new Item(-100, -100, 50, 50, "Sign Post", "Interact to move to next area.", new int[]{}, "ImageAssets/signPost", 5, 5, 20, 5, "teleport: level1");
        //subset list for items to spawn in maps
        spawnableItems = new Item[10];
        spawnableItems[0] = items[0];

        buildings = new Building[5];
        buildings[0] = new Building(-100, -100, 310, 204, "ImageAssets/greenhouse", 10, 10, 80, 100, 10);
        buildings[1] = new Building(-100, -100, 300, 250, "ImageAssets/tree", 10, 30, 160, 230, 10);
        buildings[2] = new Building(-100,-100,50,50,"ImageAssets/level1bush",10,3,3,30,3);
        //subset of buildings that can spawn in maps
        spawnableBuildings = new Building[5];
        spawnableBuildings[0] = new Building(-100, -100, 300, 250, "ImageAssets/treeLevel", 10, 30, 160, 230, 10);
        spawnableBuildings[1]  =  new Building(-100,-100,50,50,"ImageAssets/level1bush",10,3,3,30,3);

        baddies = new Enemy[5];
        baddies[0] = new Enemy("Reaper", 600, 200, 59, 66, 3, 5, 0, 10, 10, 5, null, 3, "ImageAssets/reaperEnemy");
        //subset of enemies that can spawn
        spawnableEnemy = new Enemy[5];
        spawnableEnemy[0] = new Enemy("Reaper", 600, 200, 59, 66, 3, 5, 0, 10, 10, 5, null, 3, "ImageAssets/reaperEnemy");
    }

    // methods to return copys of objects
    public Item getItem(String name, float x, float y) {
        for (Item i : items) {
            if (i.name.equals(name)) {
                Item copy = new Item(i);
                copy.x = x;
                copy.y = y;
                return copy;
            }
        }
        return null;
    }

    public Item getItem(int index, float x, float y) {
        if (items[index] != null) {
            Item copy = new Item(items[index]);
            copy.x = x;
            copy.y = y;
            return copy;
        }
        return null;
    }

    // generate a random builing that can be spawned in map --> might need to be revisted for map themes
    public Building getSpawnableBuilding() {
        int rand = (int) Math.floor(Math.random() * (spawnableBuildings.length));
        while (spawnableBuildings[rand] == null) {
            rand = (int) Math.floor(Math.random() * (spawnableBuildings.length));
        }
        return (new Building(spawnableBuildings[rand]));
    }

    //genrate a random item that can be spawned on map
    public Item getSpawnableItem() {
        spawnableMax = spawnableItems.length - 1;
        spawnableMin = 0;
        int rand = (int) Math.floor(Math.random() * (spawnableMax - spawnableMin + 1));
        while (spawnableItems[rand] == null) {
            rand = (int) Math.floor(Math.random() * (spawnableMax - spawnableMin + 1));
        }
        return (new Item(spawnableItems[rand]));
    }


    //generate a random baddies that can spawn in map --> might need to be revisted later
    public Enemy getSpawnableEnemy() {
        int rand = (int) Math.floor(Math.random() * (spawnableEnemy.length));
        while (spawnableEnemy[rand] == null) {
            rand = (int) Math.floor(Math.random() * (spawnableEnemy.length));
        }
        return (new Enemy(spawnableEnemy[rand]));
    }
}
