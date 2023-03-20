import java.util.ArrayList;
import java.util.List;

public class Weapon extends Graphic {
    float x,y,w,h,projW,projH;
    String description,name,address;
    Animation animation,rightIdle,leftIdle;
    Projectile projectile;

    // statchanges
    //  [0] , [1] , [2] , [3] , [4] , [5] , [6] , [7]
    //   hp , att , def , spd , maxH, exp , proW, Projectile Height
    int[] statChanges;

    public Weapon(Item i){
        this.x = i.x;
        this.y = i.y;
        this.w = i.w*Main.engine.player.playerScale;
        this.h = i.h*Main.engine.player.playerScale;
        this.name = i.name;
        this.description = i.description;
        this.address = i.address;
        this.statChanges = i.statChanges;
        this.rightIdle = new Animation(address,10,(int)w,(int)h);
        this.leftIdle = new Animation(address+"leftIdle",10,(int)w,(int)h);
        animation = rightIdle;
        System.out.println("proj. attack: "+ statChanges[1]);

        projectile = new Projectile(x,y,(float)statChanges[6],(float)statChanges[7],statChanges[1],statChanges[3],address+"Projectile",true);
    }

    public Weapon(float x,float y,float w,float h, String name,String description, int[] statchanges, String address){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.name = name;
        this.description = description;
        this.statChanges = statchanges;
        this.address = address;
        animation = new Animation(address,10,(int)w,(int)h);
    }

    public void display(){
        animation.current = Main.engine.player.current.current;
        if(Main.engine.player.current == Main.engine.player.rightIdle){
            animation = rightIdle;
        }
        else{
            animation = leftIdle;
        }
        Main.processing.image(animation.display(),Main.engine.player.x+14,Main.engine.player.y+41);
    }

    public void use(float mouseX,float mouseY){
        Projectile temp = new Projectile(projectile);
        float px = GEngine.player.x;
        float py = GEngine.player.y;
        double distance = Math.sqrt((mouseX - px)*(mouseX-px) + ( mouseY -py)*(mouseY - py));
        temp.dx = (mouseX - px)/(float)distance;
        temp.dy = (mouseY - py)/(float)distance;
        temp.x = GEngine.player.x;
        temp.y = GEngine.player.y;
        Main.engine.currentMap.addBullet(temp);
    }





}
