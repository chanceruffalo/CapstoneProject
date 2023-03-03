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

    public mapPresets(){
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
        items[0] = new Item(675,300,50,50,"Red Mushroom","Demo this item is for testing",new int[]{ 5,0,0,2,0,5 },"ImageAssets/mushroom",5,5,35,10,1);
        items[1] = new Item(690,200,50,50,"Red Mushroom","Demo this item is for testing",new int[]{ 5,0,0,2,0,5 },"ImageAssets/mushroom",5,5,35,10,1);
        items[2] = new Item(400,400,50,50,"Red Mushroom","Demo this item is for testing",new int[]{ 5,0,0,2,0,5 },"ImageAssets/mushroom",5,5,35,10,1);
        items[3] = new Item(450,375,50,50,"Red Mushroom","Demo this item is for testing",new int[]{ 5,0,0,2,0,5  },"ImageAssets/mushroom",5,5,35,10,1);
        items[4] = new Item(500,375,25,15,"Blue waterCan","Demo this item is for testing",new int[]{ 10,0,0,2,0,0,20,20 },"ImageAssets/watercan",5,5,35,10,true,"shoot");
        items[5] = new Item(870,200,50,50,"Sign Post","Interact to move to next area.",new int[]{},"ImageAssets/signPost",5,5,20,5,"teleport: level1");
        //all buildings
        buildings = new Building[5];
        buildings[0 ] = new Building(200,85,310,204,"ImageAssets/greenhouse",10,10,80,100,10);
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
        buildings[0 ] = new Building(200,85,310,204,"ImageAssets/greenhouse",10,10,80,100,10);
        buildings[1] = new Building(-10,10,300,250,"ImageAssets/tree",10,30,160,230,10);
        //all enemies
        baddies = new Enemy[5];

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
        items = new Item[10];
        items[5] = new Item(70,200,50,50,"Sign Post","Interact to move to next area.",new int[]{},"ImageAssets/signPost",5,5,20,5,"teleport: base");

        buildings = new Building[5];
        baddies = new Enemy[5];
        baddies[0] = new Enemy(600,200,59,66,3,5,0,10,10,5,null,3,"ImageAssets/reaperEnemy");
        minX = 0;
        minY =65;
        maxX = 960;
        maxY = 540;
        assets[0] = "ImageAssets/grasslvl";
        assets[1] = "ImageAssets/grasslvl";
        assets[2] = "ImageAssets/grasslvl";
        assets[3] = "ImageAssets/grasslvl";
        assets[4] = "ImageAssets/grasslvl";
        assets[5] = "ImageAssets/grasslvl";
        assets[6] = "ImageAssets/grasslvl";
        assets[7] = "ImageAssets/grasslvl";
        assets[8] = "ImageAssets/grasslvl";
    }




    }

