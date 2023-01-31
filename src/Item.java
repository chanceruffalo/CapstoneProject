import processing.core.PImage;

public class Item {
    float x,y,w,h,minX,maxX,minY,maxY;
    String description,name;
    Animation animation;
    int[] statChanges;

    public Item(float x,float y,float w,float h, String name,String description, int[] statchanges, String address){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        minX = x;
        maxX = x + w;
        minY = y;
        maxY = y + h;
        this.name = name;
        this.description = description;
        this.statChanges = statchanges;
        animation = new Animation(address,10,(int)w,(int)h);
    }

    public Item(float x,float y,float w,float h, String name,String description, int[] statchanges, String address, float boundaryLX,float boundaryRX, float boundaryUY,float boundaryDY) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        maxX = x+w-boundaryRX;
        maxY = y+h-boundaryDY;
        minX = x+boundaryLX;
        minY = y+boundaryUY;
        this.name = name;
        this.description = description;
        this.statChanges = statchanges;
        animation = new Animation(address,10,(int)w,(int)h);
    }

        public void display(){
        Main.processing.image(animation.display(),x,y);
    }

    public void display(float x,float y,int w, int h){
        PImage temp = animation.display();
        temp.resize(w,h);
        Main.processing.image(animation.display(),x,y);
    }



}
