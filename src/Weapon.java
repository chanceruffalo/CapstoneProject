public class Weapon extends Graphic {
    float x,y,w,h;
    String description,name,address;
    Animation animation;
    // statchanges
    //  [0] , [1] , [2] , [3] , [4] , [5]
    //   hp , att , def , spd , maxH, experience
    int[] statChanges;

    public Weapon(Item i){
        this.x = i.x;
        this.y = i.y;
        this.w = i.w;
        this.h = i.h;
        this.name = i.name;
        this.description = i.description;
        this.statChanges = i.statChanges;
        this.address = i.address;
        this.animation = new Animation(address,10,(int)w,(int)h);
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
        Main.processing.image(animation.display(),Main.engine.player.x+14,Main.engine.player.y+41);
    }

    public void use(){
        System.out.println("pew-pew");
    }





}
