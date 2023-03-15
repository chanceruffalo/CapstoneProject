public class Building extends Graphic {
    float maxX,maxY,minX,minY,boundaryLX,boundaryRX,boundaryUY,boundaryDY;
    int framerate;
    String ImgAddress;
    Animation animation;

    public Building(float x,float y,float w,float  h,String ImgAddress,int framerate, float boundaryLX,float boundaryRX, float boundaryUY,float boundaryDY){
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
        this.boundaryLX = boundaryLX;
        this.boundaryRX = boundaryRX;
        this.boundaryUY = boundaryUY;
        this.boundaryDY = boundaryDY;
        this.framerate = framerate;
        this.ImgAddress = ImgAddress;
        contactPoints = new Point[]{new Point(x,y),new Point(x+w,y),new Point(x,y+h),new Point(x+w,y+h) };

        value = y+h;


        animation = new Animation(ImgAddress,framerate,(int)w,(int)h);
    }
    //copy constructor
    public Building(Building b){
        this.x = b.x;
        this.y = b.y;
        this.h = b.h;
        this.w = b.w;
        this.framerate = b.framerate;
        this.ImgAddress = b.ImgAddress;
        contactPoints = b.contactPoints;
        value = y+h;
        //for player boundaries if player can walk behind building
        this.boundaryLX = b.boundaryLX;
        this.boundaryRX = b.boundaryRX;
        this.boundaryUY = b.boundaryUY;
        this.boundaryDY = b.boundaryDY;


        animation = new Animation(ImgAddress,framerate,(int)w,(int)h);
    }
    public void resize(double displayWk, double displayHk){
        x *= displayWk;
        y *= displayHk;
        h *=displayHk;
        w *= displayWk;
        contactPoints = new Point[]{new Point(x,y),new Point(x+w,y),new Point(x,y+h),new Point(x+w,y+h) };
        animation.resize(displayWk,displayHk);
    }

    public void changeCoordinates(float x, float y){
        this.x = x;
        this.y = y;
        value = y+h;

        contactPoints = new Point[]{new Point(x,y),new Point(x+w,y),new Point(x,y+h),new Point(x+w,y+h) };
    }

    public void display(){
        Main.processing.image(animation.display(),x,y);
    }

}
