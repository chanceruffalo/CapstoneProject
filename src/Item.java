import processing.core.PImage;

public class Item extends Graphic  {
    float minX,maxX,minY,maxY,boundaryRX,boundaryDY,boundaryLX,boundaryUY;
    String description,name,address,power;
    Animation animation,activate;
    Boolean interactable,isWeapon;
    //Point[] contactPoints;
    // statchanges
    //  [0] , [1] , [2] , [3] , [4] , [5]
    //   hp , att , def , spd , maxH, experience
    int[] statChanges;
    int uses;

    //constructor for a consumable item
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
        isWeapon = false;
        this.description = description;
        this.statChanges = statchanges;
        this.interactable = false;
        this.address = address;
        contactPoints = new Point[]{new Point(x,y),new Point(x+w,y),new Point(x,y+h),new Point(x+w,y+h) };
        power = "use";
        animation = new Animation(address,10,(int)w,(int)h);
        activate = new Animation("ImageAssets/activateBtn",10,15,15);
    }
    //constructor for a weapon
    public Item(float x,float y,float w,float h, String name,String description, int[] statchanges, String address, float boundaryLX,float boundaryRX, float boundaryUY,float boundaryDY,boolean isWeapon,String power) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.uses = -1;
        this.boundaryDY = boundaryDY;
        this.boundaryRX = boundaryRX;
        this.boundaryLX = boundaryLX;
        this.boundaryUY = boundaryUY;
        contactPoints = new Point[]{new Point(x,y),new Point(x+w,y),new Point(x,y+h),new Point(x+w,y+h) };
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
        // power will be either shoot or slash
        this.power = power;
        this.isWeapon = isWeapon;
        animation = new Animation(address,10,(int)w,(int)h);
        activate = new Animation("ImageAssets/activateBtn",10,15,15);
    }
    //special case items that have special uses
    public Item(float x,float y,float w,float h, String name,String description, int[] statchanges, String address, float boundaryLX,float boundaryRX, float boundaryUY,float boundaryDY,String power) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.uses = -1;
        this.boundaryDY = boundaryDY;
        this.boundaryRX = boundaryRX;
        this.boundaryLX = boundaryLX;
        this.boundaryUY = boundaryUY;
        contactPoints = new Point[]{new Point(x,y),new Point(x+w,y),new Point(x,y+h),new Point(x+w,y+h) };
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
        this.isWeapon = false;
        this.power = power;
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
        this.contactPoints = i.contactPoints;
        maxX = x+w-boundaryRX;
        maxY = y+h-boundaryDY;
        value = maxY;
        minX = x+boundaryLX;
        minY = y+boundaryUY;
        this.name = i.name;
        this.description = i.description;
        this.statChanges = i.statChanges;
        this.interactable = false;
        this.uses = i.uses;
        this.address = i.address;
        this.isWeapon = i.isWeapon;
        this.power = i.power;
        animation = new Animation(address,10,(int)w,(int)h);
        activate = new Animation("ImageAssets/activateBtn",10,15,15);
    }

        public void display(){
        if(isWeapon){
            animation.current = 0;
        }
        Main.processing.image(animation.display(),x,y);

        if(interactable){
            Main.processing.image(activate.display(),x,y);
        }

    }

    public void use(){
        if(power.equals("use")) {
            // statchanges
            //  [0] , [1] , [2] , [3] , [4] , [5] , [6] , [7]
            //   hp , att , def , spd , maxH, exp , proW, Projectile Height
            if(statChanges[0] != 0 &&Main.engine.player.health < Main.engine.player.maxHealth){
                Main.engine.player.health += statChanges[0];
                Main.engine.player.attack += statChanges[1];
                Main.engine.player.defense += statChanges[2];
                Main.engine.player.speed += statChanges[3];
                Main.engine.player.maxHealth += statChanges[4];
                Main.engine.player.experience += statChanges[5];
                uses --;
            }
            else if(statChanges[0] == 0) {
                Main.engine.player.attack += statChanges[1];
                Main.engine.player.defense += statChanges[2];
                Main.engine.player.speed += statChanges[3];
                Main.engine.player.maxHealth += statChanges[4];
                Main.engine.player.experience += statChanges[5];
                uses--;
            }
        }
    }

    //method to change x y coordinates including contactpoints
    public void changeCoordinates(float x, float y){
        this.x = x;
        this.y = y;
        maxX = x+w-boundaryRX;
        maxY = y+h-boundaryDY;
        value = maxY;
        minX = x+boundaryLX;
        minY = y+boundaryUY;
        this.contactPoints = new Point[]{new Point(x,y),new Point(x+w,y),new Point(x,y+h),new Point(x+w,y+h) };
    }




}
