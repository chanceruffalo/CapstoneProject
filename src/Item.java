import processing.core.PImage;

public class Item {
    float x,y,w,h;
    String description,name;
    Animation animation;
    int[] statChanges;

    public Item(float x,float y,float w,float h, String name,String description, int[] statchanges, String address){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
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
