import java.util.List;

public class MapSystem {

    MapTile[][] map;
    //forward lists are for render
    Item[] items,forwardItems;
    float minX,minY,maxX,maxY;
    int x,y,w,h,i,j;
    Building[] forwardBuildings,buildings;
    public MapSystem(mapPresets preset){
        //x , y are tiles counts for map
        x = 20;
        y = 11;
        // width and height of each tile
        w = 960/20;
        h = 540/11;
        //boundaries
        minX = 0;
        minY = 0;
        maxX = x*w ;
        maxY = y*h ;
        // i-> index of forward buildings array
        // j-> index of background buildings array "buildings"
        i = 0;
        j = 1;
        map = new MapTile[x][y];
        buildings = preset.buildings;
        items = preset.items;
        forwardBuildings = new Building[buildings.length];
        forwardItems = new Item[items.length];
        for(int j = 0; j < y; j ++){
            for(int i = 0; i < x; i ++){
                MapTile temp = new MapTile(i*w, j * h, i, j, w, h,preset.assets[preset.home[j][i]],20);
                map[i][j] = temp;
            }
        }
    }

    public void displayBackground() {
        //display floor
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                map[i][j].display();
            }
        }
        //display buildings behind character
        for(Building b:buildings){
            if (b != null) {
                b.display();
            }
        }
        //display items behind character
        for(Item i: items){
            if(i != null){
                i.display();
            }
        }

    }

    public void displayForground() {
        //display buildings in front of player
        for (Building b : forwardBuildings) {
            if (b != null) {
                b.display();
            }
        }
        for (Item i : forwardItems) {
            if (i != null) {
                i.display();
            }
        }
    }
    public boolean checkBoarder(Point[] points){
        //counter for buildings => i
        //counter for items => j
        i = 0;
        j = 0;

        //display buildings either infront of player or behind player
        for (Building b : buildings) {
            if (b != null) {
                if (points[3].y <= b.maxY && contains(forwardBuildings,b) == -1){
                    forwardBuildings[i] = b;
                }
                else if(points[3].y >= b.maxY){
                    forwardBuildings[i] = null;
                }
            }
            i ++;
        }

        //display items either infront of player or behind player
        for (Item it : items) {
            if (it != null) {
                if (points[3].y <= it.maxY && contains(forwardItems,it) == -1){
                    forwardItems[j] = it;
                }
                else if(points[3].y >= it.maxY){
                    forwardItems[j] = null;
                }
            }
            i ++;
        }
        for(Point p: points){
            //check if player runs into each building
            for (Building b : buildings) {
                if(b != null){
                    if(p.x >b.minX  && p.y > b.minY && p.x  < b.maxX && p.y  < b.maxY){
                        return false;
                    }
                }
            }

            for(Item i : items){
                if(i != null){
                    if(p.x >i.minX  && p.y > i.minY && p.x  < i.maxX && p.y  < i.maxY){
                        return false;
                    }
                }
            }

            //boarder check
            if(p.x < minX  || p.y < minY || p.x  > maxX || p.y  > maxY){
                return false;
            }
        }
        return true;

    }

    public void resize(double displayWk,double displayHk){
        w *= displayWk;
        h *= displayHk;
        for(Building temp:buildings) {
            if (temp != null) {
                temp.resize(displayWk, displayHk);
            }
        }
        for(int j = 0; j < y; j ++){
            for(int i = 0; i < x; i ++){
                map[i][j].resize(displayWk,displayHk);
            }
        }
    }

    public int contains(Object[] items, Object b){
        int i = 0;
        for (Object temp: items) {
            if(temp == b){
                return i;
            }
            i ++;
        }
        return -1;
    }

}
