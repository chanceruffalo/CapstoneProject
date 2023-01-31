public class Building {
    float x,y,maxX,maxY,minX,minY;
    int h,w;
    Animation animation;

    //constructors with diffrent parameters to make life easier
    public Building(float x,float y,int w,int  h,String ImgAddress,int framerate, float boundaryX, float boundaryY){
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
        //for player boundaries if player can walk behind building
        maxX = x+w-boundaryX;
        maxY = y+h;
        minX = x+boundaryX;
        minY = y+boundaryY;

        animation = new Animation(ImgAddress,framerate,w,h);
    }

    public Building(float x,float y,int w,int  h,String ImgAddress,int framerate, float boundaryLX,float boundaryRX, float boundaryUY,float boundaryDY){
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
        //for player boundaries if player can walk behind building
        maxX = x+w-boundaryRX;
        maxY = y+h-boundaryDY;
        minX = x+boundaryLX;
        minY = y+boundaryUY;


        animation = new Animation(ImgAddress,framerate,w,h);
    }
    public void resize(double displayWk, double displayHk){
        maxX *=displayWk;
        maxY *= displayHk;
        minX *=displayWk;
        minY *=displayHk;
        x *= displayWk;
        y *= displayHk;
        h *=displayHk;
        w *= displayWk;
        animation.resize(displayWk,displayHk);
    }

    public void display(){
        Main.processing.image(animation.display(),x,y);
    }

}
