import java.util.List;
import java.util.Random;

public class MapSystem {

    MapTile[][] map;
    //forward lists are for render
    Item[] items;
    float minX,minY,maxX,maxY,grabRange;
    int x,y,w,h,i,j;
    Building[] buildings;
    Enemy[] baddies;
    Projectile[] bullets;
    int bulletCount;
    //store the boarder of a map for UI
    Animation mapBoarder;
    public MapSystem(mapPresets preset){

        //x , y are tiles counts for map
        x = 20;
        y = 11;
        // width and height of each tile
        w = 960/20;
        h = 540/11;
        //boundaries
        minX = preset.minX;
        minY = preset.minY;
        maxX = preset.maxX;
        maxY = preset.maxY;
        //grab range for player for any item
        grabRange = 35;
        bulletCount =0;
        // i-> index of forward buildings array
        // j-> index of background buildings array "buildings"
        i = 0;
        j = 1;
        map = new MapTile[x][y];
        buildings = preset.buildings;
        items = preset.items;
        baddies = preset.baddies;
        bullets = new Projectile[60];
        bulletCount = 0;
        mapBoarder = preset.mapBoarder;
        Random random = new Random();
        for(int j = 0; j < y; j ++){
            for(int i = 0; i < x; i ++){
                MapTile temp = new MapTile(i*w, j * h, i, j, w, h,preset.assets[preset.current[j][i]],20);
                temp.animation.current = random.nextInt(temp.animation.maxFrames);
                map[i][j] = temp;
            }
        }

    }

    public void addBullet(Projectile bullet){
        int bulletC =0;
        for(Projectile proj: bullets){
            if(proj == null){
                break;
            }
            bulletC ++;
        }
        bullet.index = bulletC;
        bullets[bulletC] = bullet;
        bulletCount ++;
    }

    public void deleteProjectile(int index){
        bullets[index] = null;
        bulletCount --;
    }

    public void deleteEnemy(float x, float y){
        int index = 0;
        for(Enemy e: baddies){
            if(e != null){
                if(e.x ==x && e.y ==y){
                    baddies[index] = null;
                    break;
                }
                index ++;
            }
            else{
                index ++;
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

        for(Point p: points){
            //check if player runs into each building
            for (Building b : buildings) {
                if(b != null){
                    if(p.x >b.x +b.boundaryLX  && p.y > b.y + b.boundaryUY && p.x  < b.x+b.w - b.boundaryRX && p.y  < b.y + b.h -b.boundaryDY){
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

    //here will check bullet collision and perform operations if bullet does something if hit
    public boolean checkBulletCollision(Projectile p){
        //if goes off-screen or hits a building return null
        for (Building b : buildings) {
            if(b != null){
                if(p.x >b.minX  && p.y > b.minY && p.x  < b.maxX && p.y  < b.maxY){
                    return true;
                }
            }
        }
        //checks collision with enemy
        for(Enemy e:baddies){
            if(e != null){
                if(p.x >e.x  && p.y > e.y && p.x  < e.x + e.w && p.y  < e.y+e.h && p.isFriendly){
                        e.takeDamage(p.attack);
                        //e.health -= (p.attack - e.defense);
                    return true;
                }
            }
        }
        //check if bullet collides with player
        if(p.x >Main.engine.player.x  && p.y > Main.engine.player.y && p.x  < Main.engine.player.x + Main.engine.player.w && p.y  < Main.engine.player.y+Main.engine.player.h && !p.isFriendly){
           Main.engine.player.takeDamage(p.attack);
            return true;
        }

        return false;
    }
//checks if a player can interact with an item and perform interaction if permitted
    public Item playerInteract(int emptySpot){
        if(emptySpot == -1){
            return null;
        }
        int j = 0;
        for(Item i : items){
            if(i != null && i.interactable && i.power.length() < 6){
                    Item copy = new Item(i);
                    items[j] = null;
                    return copy;
            }//Here are special item powers code
            else if(i != null && i.interactable && i.power.substring(0,8).equals("teleport")){
                String port = i.power.substring(10,i.power.length());
                if(port.equals("level1")) {
                    Main.engine.changeMap(1);
                    break;
                }
                else if(port.equals("base")) {
                    Main.engine.changeMap(0);
                    break;
                }
            }
            j++;
        }
        return null;
    }

    //here will check the items in map and make themm interactable
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
