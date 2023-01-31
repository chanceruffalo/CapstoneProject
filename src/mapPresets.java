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
        //items[0] = new Item(300,300,50,50,"item","Demo this item is for testing",new int[]{ 1,2,3,4,5 },"item");
        buildings = new Building[5];
        buildings[0] = new Building(200,-5,225,225,"ImageAssets/greenhouse",10,10,20,100,40);
        buildings[1] = new Building(-10,-30,300,250,"ImageAssets/tree",10,10,10,100,40);
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

