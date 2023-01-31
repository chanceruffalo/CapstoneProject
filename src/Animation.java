import processing.core.PImage;

public class Animation {

    PImage[] imgs;
    String address;
    int maxFrames, current, frameRate, counter, w,h;


    public Animation(String address,int frameRate, int w, int h){
        current = 0;
        counter = 0;
        maxFrames = 1;
        this.address = address;
        this.w = w;
        this.h =h;
        this.frameRate = frameRate;
        imgs = new PImage[30];
        //populate images into array
        PImage temp = Main.processing.loadImage((address+maxFrames+".png"));
        temp.resize(w,h);
        imgs[maxFrames-1] = temp;
        boolean finding = true;
        while(finding){
            try {
                maxFrames ++;
                temp = Main.processing.loadImage((address + maxFrames + ".png"));
                temp.resize(w,h);
                imgs[maxFrames-1] = temp;
            }catch(Exception E){
                maxFrames --;
                finding = false;
            }
        }


    }

    public PImage display(){
        counter ++;
        if(counter > frameRate){
            current ++;
            counter = 0;
        }
        if(current > maxFrames-1){
            current = 0;
        }
        return imgs[current];

  }

  public void resize(double displayWK, double displayHK){
      w *=displayWK;
      h *= displayHK;
      maxFrames = 1;
      imgs = new PImage[30];
      PImage temp = Main.processing.loadImage((address+maxFrames+".png"));
      temp.resize(w,h);
      imgs[maxFrames-1] = temp;
      boolean finding = true;
      while(finding){
          try {
              maxFrames ++;
              temp = Main.processing.loadImage((address + maxFrames + ".png"));
              temp.resize(w,h);
              imgs[maxFrames-1] = temp;
          }catch(Exception E){
              maxFrames --;
              finding = false;
          }
      }
  }
  public void setSize(int w, int h){
      this.w = w;
      this.h = h;
        for (PImage temp: imgs) {
          temp.resize(w,h);
      }
  }


}
