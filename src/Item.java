import processing.core.PImage;

public class Item extends Graphic {
    float x,y,w,h,minX,maxX,minY,maxY,boundaryRX,boundaryDY,boundaryLX,boundaryUY;
    String description,name,address;
    Animation animation,activate;
    Boolean interactable;
    // statchanges
    //  [0] , [1] , [2] , [3] , [4] , [5]
    //   hp , att , def , spd , maxH, experience
    int[] statChanges;
    int uses;

    public Item(float x,float y,float w,float h, String name,String description, int[] statchanges, String address, float boundaryLX,float boundaryRX, float boundaryUY,float boundaryDY,int uses) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.uses = uses;
        this.boundaryDY = boundaryDY;
        this.boundaryRX = boundaryRX;
        this.boundaryLX = boundaryLX;
        this.boundaryUY = boundaryUY;
        maxX = x+w-boundaryRX;
        maxY = y+h-boundaryDY;
        value = maxY;
        minX = x+boundaryLX;
        minY = y+boundaryUY;
        this.name = name;
        this.description = description;
        this.statChanges = statchanges;
        this.interactable = false;
        this.address = address;
        animation = new Animation(address,10,(int)w,(int)h);
        activate = new Animation("ImageAssets/activateBtn",10,15,15);
    }
    //copy constructor
    public Item(Item i){
        this.x = i.x;
        this.y = i.y;
        this.w = i.w;
        this.h = i.h;
        this.boundaryDY = i.boundaryDY;
        this.boundaryRX = i.boundaryRX;
        this.boundaryLX = i.boundaryLX;
        this.boundaryUY = i.boundaryUY;
        maxX = x+w-boundaryRX;
        maxY = y+h-boundaryDY;
        value = maxY;
        minX = x+boundaryLX;
        minY = y+boundaryUY;
        this.name = i.name;
        this.description = i.description;
        this.statChanges = i.statChanges;
        this.interactable = false;
        this.address = i.address;
        animation = new Animation(address,10,(int)w,(int)h);
        activate = new Animation("ImageAssets/activateBtn",10,15,15);
    }

        public void display(){
        Main.processing.image(animation.display(),x,y);
        if(interactable){
            Main.processing.image(activate.display(),x,y);
        }
    }

    public void use(){
        //  [0] , [1] , [2] , [3] , [4] , [5]
        //   hp , att , def , spd , maxH, experience
        Main.engine.player.health += statChanges[0];
        Main.engine.player.attack += statChanges[1];
        Main.engine.player.defense += statChanges[2];
        Main.engine.player.speed += statChanges[3];
        Main.engine.player.maxHealth += statChanges[4];
        Main.engine.player.experience += statChanges[5];
        uses --;
    }




}
