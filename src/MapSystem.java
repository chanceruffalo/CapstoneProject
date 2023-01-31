

public class MapSystem {

    MapTile[][] map;
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
        forwardBuildings = new Building[buildings.length];
        for(int j = 0; j < y; j ++){
            for(int i = 0; i < x; i ++){
                MapTile temp = new MapTile(i*w, j * h, i, j, w, h,preset.assets[preset.home[j][i]],20);
                map[i][j] = temp;
            }
        }
    }

    public void displayBackground() {
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                map[i][j].display();
            }
        }
        //display buildigs behind character
        for(Building b:buildings){
            if (b != null) {
                b.display();
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
    }
    public boolean checkBoarder(Point[] points){
        i = 0;

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
        for(Point p: points){
            //check if player runs into each building
            for (Building b : buildings) {
                if(b != null){
                    if(p.x >b.minX  && p.y > b.minY && p.x  < b.maxX && p.y  < b.maxY){
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

    public int contains(Building[] buildings, Building b){
        int i = 0;
        for (Building temp: buildings) {
            if(temp == b){
                return i;
            }
            i ++;
        }
        return -1;
    }

}
