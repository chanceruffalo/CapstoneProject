import processing.core.PImage;

public class MapTile {
    float x,y;
    int i,j,w,h;
    Animation animation;


    public MapTile(float x, float y, int i,int j, int w, int h,String Iaddress, int frameRate){
        this.x = x;
        this.y = y;
        this.i = i;
        this.j = j;
        this.w = w;
        this.h = h;
        animation = new Animation(Iaddress,frameRate,w,h);
    }

    public void display() {
            Main.processing.image(animation.display(), x, y);
    }
    public void resize(double displayWk,double displayHk){
        x *= displayWk;
        y *= displayHk;
        w *= displayWk;
        h *= displayHk;
        animation.resize(displayWk,displayHk);
    }

}
