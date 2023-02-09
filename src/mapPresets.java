import java.util.Hashtable;

public class mapPresets {
    int[][] current,home;
    String[] assets;
    Building[] buildings;
    Item[] items;
    int w,h;
    public mapPresets(){
        w = 960/20;
        h = 540/11;
        items = new Item[5];
        //  [0] , [1] , [2] , [3] , [4] , [5]
        //   hp , att , def , spd , maxH, experience
        items[0] = new Item(675,300,50,50,"Red Mushroom","Demo this item is for testing",new int[]{ 5,0,0,2,0,5 },"ImageAssets/mushroom",5,5,35,10,1);
        items[1] = new Item(690,200,50,50,"Red Mushroom","Demo this item is for testing",new int[]{ 5,0,0,2,0,5 },"ImageAssets/mushroom",5,5,35,10,1);
        items[2] = new Item(400,400,50,50,"Red Mushroom","Demo this item is for testing",new int[]{ 5,0,0,2,0,5 },"ImageAssets/mushroom",5,5,35,10,1);
        items[3] = new Item(450,375,50,50,"Red Mushroom","Demo this item is for testing",new int[]{ 5,0,0,2,0,5  },"ImageAssets/mushroom",5,5,35,10,1);
        items[4] = new Item(500,375,25,15,"Blue waterCan","Demo this item is for testing",new int[]{ 10,0,0,2,0,1000 },"ImageAssets/watercan",5,5,35,10,true);

        buildings = new Building[5];
        buildings[1] = new Building(200,-5,225,225,"ImageAssets/greenhouse",10,10,20,100,10);
        buildings[0] = new Building(-10,-30,300,250,"ImageAssets/tree",10,30,160,230,10);
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
        // Image addresses
        assets = new String[10];
        assets[0] = "ImageAssets/newGrass";
        assets[1] = "ImageAssets/newGrassUpperWall";
        assets[2] = "ImageAssets/greenGrassboarderUpperLeftCorner_";
        assets[3] = "ImageAssets/greenGrassboarderUpperRightCorner_";
        assets[4] = "ImageAssets/greenGrassboarderLowerRightCorner_";
        assets[5] = "ImageAssets/greenGrassboarderLowerLeftCorner_";
        assets[6] = "ImageAssets/greenGrassboarderRight_";
        assets[7] = "ImageAssets/greenGrassboarderDown_";
        assets[8] = "ImageAssets/greenGrassboarderLeft_";
        // map to load
        current = home;
    }
    //presets:
    // x=0: home map
    public void changeCurrent(int x){
        switch(x){
            case 0:current = home;break;
        }
    }




    }

