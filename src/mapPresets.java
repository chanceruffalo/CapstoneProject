import java.util.Hashtable;
import java.util.Random;

public class mapPresets {
    int[][] current,home;
    String[] assets;
    Building[] buildings;
    Item[] items;
    Enemy[] baddies;
    int w,h;
    float minX, minY,maxX,maxY;
    Random random;
    Animation mapBoarder;

    public mapPresets(){
        mapBoarder = null;
        //default to base map
        random = new Random();
        w = 960/20;
        h = 540/11;
        minX = 0;
        minY=150;
        maxX = 960;
        maxY = 540;
        // statchanges
        //  [0] , [1] , [2] , [3] , [4] , [5] , [6] , [7]
        //   hp , att , def , spd , maxH, exp , proW, Projectile Height
        items = new Item[10];
        items[0] = Main.library.getItem("Blue watercan",500,375);
        items[1] = new Item(870,200,50,50,"Sign Post","Interact to move to next area.",new int[]{},"ImageAssets/signPost",5,5,20,5,"teleport: level1");
        //all buildings
        buildings = new Building[5];
        buildings[0] = new Building(200,85,310,204,"ImageAssets/greenhouse",10,10,80,100,10);
        buildings[1] = new Building(-10,10,300,250,"ImageAssets/tree",10,30,160,230,10);
        //all enemies
        baddies = new Enemy[5];

        // Tile presets
        home = new int[][]
                {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1}};

        for(int i = 0; i < 12; i ++){
            for(int j = 0; j < 21; j ++){
                if(i == 1){
                    home[i][j] = 5;
                }
                else if(i == 2){
                    home[i][j] = 1;
                }
                else{
                    int taddress = random.nextInt(10)+1;
                    while(taddress == 1 ||taddress == 5){
                        taddress = random.nextInt(4)+1;
                    }
                    if(taddress > 4){
                        taddress = 6;
                    }
                    home[i][j] = taddress;
                }
            }
        }
        // Image addresses
        assets = new String[10];
        assets[0] = "ImageAssets/newGrass";
        assets[1] = "ImageAssets/newGrassUpperWall";
        assets[2] = "ImageAssets/newGrass2";
        assets[3] = "ImageAssets/newGrass3";
        assets[4] = "ImageAssets/newGrass4";
        assets[5] = "ImageAssets/newGrassUpperWall2";
        assets[6] = "ImageAssets/newGrass5";
        assets[7] = "ImageAssets/greenGrassboarderDown_";
        assets[8] = "ImageAssets/greenGrassboarderLeft_";
        // map to load
        current = home;
    }
    //presets:
    // x=0: home map
    public void changeCurrent(int x){
        switch(x){
            case 0:base();
            case 1:level1();
        }
    }
    //diffrent levels and maps to be displayed
    public void base(){

        //all buildings
        buildings = new Building[5];
        buildings[0] = new Building(200,85,310,204,"ImageAssets/greenhouse",10,10,80,100,10);
        buildings[1] = new Building(-10,10,300,250,"ImageAssets/tree",10,30,160,230,10);
        //all enemies
        baddies = new Enemy[0];
        mapBoarder = null;
        assets = new String[10];
        assets[0] = "ImageAssets/newGrass";
        assets[1] = "ImageAssets/newGrassUpperWall";
        assets[2] = "ImageAssets/newGrass2";
        assets[3] = "ImageAssets/newGrass3";
        assets[4] = "ImageAssets/newGrass4";
        assets[5] = "ImageAssets/newGrassUpperWall2";
        assets[6] = "ImageAssets/newGrass5";
        assets[7] = "ImageAssets/greenGrassboarderDown_";
        assets[8] = "ImageAssets/greenGrassboarderLeft_";
    }

    public void level1(){
        minX = 0;
        minY =0;
        maxX = 960;
        //maxy less than window height to account for Inventory UI
        maxY = 500;
        mapBoarder = new Animation("ImageAssets/level1mapBoarder",10,960,540);

        Main.engine.player.moveLocation(50,250);
        // maptile population
        for(int i = 0; i < 12; i ++){
            for(int j = 0; j < 21; j ++){
                    int taddress = random.nextInt(10)+1;
                    while(taddress == 1 ||taddress == 5){
                        taddress = random.nextInt(4)+1;
                    }
                    if(taddress > 4){
                        taddress = 6;
                    }
                    home[i][j] = taddress;
                }
            }

        //empty lists and randomly fill from library
        // --> Item list
        int max = 10;
        int min = 1;
        int numberOfItems = (int)Math.floor(Math.random() * (max - min + 1) + min);
        items = new Item[numberOfItems];
        // -->Building list
        max = 10;
        numberOfItems = (int)Math.floor(Math.random() * (max - min + 1) + min);
        buildings = new Building[numberOfItems];
        // --> enemy list
        max = 6;
        numberOfItems = (int)Math.floor(Math.random() * (max - min + 1) + min);
        baddies = new Enemy[numberOfItems];

        // mandatory items
        items[0] = new Item(70,200,50,50,"Sign Post","Interact to move to next area.",new int[]{},"ImageAssets/signPost",5,5,20,5,"teleport: base");
        baddies[0] = new Enemy("Reaper",600,200,59,66,3,5,0,10,10,5,null,3,"ImageAssets/reaperEnemy");

        //generate building list
        for(int i = 0; i < buildings.length; i ++){
            if(buildings[i] == null) {
                //"getBuildings" will randomly produce a building from a subset of buildings for maps
                Building copy = Main.engine.library.getSpawnableBuilding();
                float randomX = (float) Math.floor(Math.random() * (Main.originalW - 0 + 1) + 0);
                float randomY = (float) Math.floor(Math.random() * (Main.originalH - 0 + 1) + 0);
                copy.changeCoordinates(randomX, randomY);
                int changeThreshold = 0;
                //if the building were to collide with another item or building change x and y coordinates
                while (collides(copy) == true && changeThreshold < 200) {
                    changeThreshold ++;
                    randomX = (float) Math.floor(Math.random() * (Main.originalW - 0 + 1) + 0);
                    randomY = (float) Math.floor(Math.random() * (Main.originalH - 0 + 1) + 0);
                    copy.changeCoordinates(randomX, randomY);
                }
                buildings[i] = new Building(copy);
            }
        }

        //generate items list
        for(int i = 0; i < items.length; i ++){
            if(items[i] == null) {
                //"getSpawnable" will randomly produce an item from a subset of items for maps
                Item copy = Main.engine.library.getSpawnableItem();
                //randomize location
                float randomX = (float) Math.floor(Math.random() * (Main.originalW - 0 + 1) + 0);
                float randomY = (float) Math.floor(Math.random() * (Main.originalH - 0 + 1) + 0);
                copy.changeCoordinates(randomX, randomY);
                //this variable will stop the changing x and y if tried to many times
                int changeThreshold = 0;
                //if the item were to collide with another item or building change x and y coordinates
                while (collides(copy) == true && changeThreshold < 20) {
                    randomX = (float) Math.floor(Math.random() * (Main.originalW - 0 + 1) + 0);
                    randomY = (float) Math.floor(Math.random() * (Main.originalH - 0 + 1) + 0);
                    copy.changeCoordinates(randomX, randomY);
                }
                items[i] = new Item(copy);
            }
        }

        //generate baddies
        for(int i = 0; i < baddies.length; i ++){
            if(baddies[i] == null) {
                //"getSpawnable" will randomly produce an enemy from a subset of baddies for maps
                Enemy copy = Main.engine.library.getSpawnableEnemy();
                //randomize location
                float randomX = (float) Math.floor(Math.random() * (Main.originalW - 0 + 1) + 0);
                float randomY = (float) Math.floor(Math.random() * (Main.originalH - 0 + 1) + 0);
                copy.changeCoordinates(randomX, randomY);
                //this variable will stop the changing x and y if tried to many times
                int changeThreshold = 0;
                //if the item were to collide with another item or building change x and y coordinates
                while (collides(copy) == true && changeThreshold < 20) {
                    randomX = (float) Math.floor(Math.random() * (Main.originalW - 0 + 1) + 0);
                    randomY = (float) Math.floor(Math.random() * (Main.originalH - 0 + 1) + 0);
                    copy.changeCoordinates(randomX, randomY);
                }
                baddies[i] = new Enemy(copy);
            }
        }

        assets[0] = "ImageAssets/grasslvl";
        assets[1] = "ImageAssets/grasslvl";
        assets[2] = "ImageAssets/grasslvl1_2";
        assets[3] = "ImageAssets/grasslvl1_2";
        assets[4] = "ImageAssets/grasslvl1_3";
        assets[5] = "ImageAssets/grasslvl";
        assets[6] = "ImageAssets/grasslvl1_3";
        assets[7] = "ImageAssets/grasslvl";
        assets[8] = "ImageAssets/grasslvl";
    }

    // method to check if an item/building colides with and item,building or player in list
    public boolean collides(Graphic x){
        //check buildings
        for(Building b: buildings){
            if(b != null){
                //check which graphic is bigger and then check if smaller item is inside bigger graphic
                if(b.w * b.h > x.w * x.h){
                    for (Point p : x.contactPoints) {
                        if (p.x >= b.x && p.x <= b.x + b.w && p.y >= b.y && p.y <= b.y + b.h) {
                            return true;
                        }
                    }
                }
                else {
                    for (Point p : b.contactPoints) {
                        if (p.x >= x.x && p.x <= x.x + x.w && p.y >= x.y && p.y <= x.y + x.h) {
                            return true;
                        }
                    }
                }
            }
        }
        //check items
        for(Item item: items){
            if(item != null) {
                //check which graphic is bigger and then check if smaller item is inside bigger graphic
                if(item.w * item.h > x.w * x.h){
                    for (Point p : x.contactPoints) {
                        if (p.x >= item.x && p.x <= item.x + item.w && p.y >= item.y && p.y <= item.y + item.h) {
                            return true;
                        }
                    }
                }
                else {
                    for (Point p : item.contactPoints) {
                        if (p.x >= x.x && p.x <= x.x + x.w && p.y >= x.y && p.y <= x.y + x.h) {
                            return true;
                        }
                    }
                }
            }
        }
        //check enemies in map
        for(Enemy item: baddies){
            if(item != null) {
                //check which graphic is bigger and then check if smaller item is inside bigger graphic
                if(item.w * item.h > x.w * x.h){
                    for (Point p : x.contactPoints) {
                        if (p.x >= item.x && p.x <= item.x + item.w && p.y >= item.y && p.y <= item.y + item.h) {
                            return true;
                        }
                    }
                }
                else {
                    for (Point p : item.contactPoints) {
                        if (p.x >= x.x && p.x <= x.x + x.w && p.y >= x.y && p.y <= x.y + x.h) {
                            return true;
                        }
                    }
                }
            }
        }
//check collision of player
        if(Main.engine.player.w * Main.engine.player.h > x.w * x.h){
            for (Point p : x.contactPoints) {
                if (p.x >= GEngine.player.x && p.x <= GEngine.player.x + GEngine.player.w && p.y >= GEngine.player.y && p.y <= GEngine.player.y + GEngine.player.h) {
                    return true;
                }
            }
        }
        else {
            //check if collides with player
            for (Point p : Main.engine.player.contactPoints) {
                if (p.x >= x.x && p.x <= x.x + x.w && p.y >= x.y && p.y <= x.y + x.h) {
                    return true;
                }
            }
        }

        //check if in boundaries of map
        for(Point p : x.contactPoints){
            if(p.x <= minX || p.x >= maxX || p.y <= minY || p.y >= maxY){
                return true;
            }
        }
        // no collision occured
        return false;
    }




    }

