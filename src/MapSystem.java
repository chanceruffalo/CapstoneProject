import java.util.List;

public class MapSystem {

    MapTile[][] map;
    //forward lists are for render
    Item[] items;
    float minX,minY,maxX,maxY,grabRange;
    int x,y,w,h,i,j;
    Building[] buildings;
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
        //grab range for player for any item
        grabRange = 35;
        // i-> index of forward buildings array
        // j-> index of background buildings array "buildings"
        i = 0;
        j = 1;
        map = new MapTile[x][y];
        buildings = preset.buildings;
        items = preset.items;
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
    }
    public boolean checkBoarder(Point[] points){
        //counter for buildings => i
        //counter for items => j
        i = 0;
        j = 0;
        checkInteractable(points);

        for(Point p: points){
            //check if player runs into each building
            for (Building b : buildings) {
                if(b != null){
                    if(p.x >b.minX  && p.y > b.minY && p.x  < b.maxX && p.y  < b.maxY){
                        return false;
                    }
                }
            }
            //ITEM COLLISION
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

    public Item playerInteract(){
        int j = 0;
        for(Item i : items){
            if(i != null && i.interactable){
                Item copy = new Item(i);
                items[j] = null;
                return copy;
            }
            j++;
        }
        return null;
    }

    public void checkInteractable(Point[] points){
        for(Item i : items){
            for(Point p: points) {
                if (i != null) {
                    // check if item can interact
                    if (p.x > i.minX - grabRange && p.y > i.minY - grabRange && p.x < i.maxX + grabRange && p.y < i.maxY + grabRange && !i.interactable) {
                        i.interactable = true;
                        break;
                    } else {
                        i.interactable = false;
                    }
                }
            }
        }
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

}
