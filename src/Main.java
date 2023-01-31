import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Main extends PApplet{
    public static PApplet processing;
    public static GEngine engine;
    public static boolean loads;
    public static Double time;
    public static double originalW,originalH;
    public static double displayWK,displayHK;

    public static void main(String[] args) {
        time = 0.0;
        loads = false;
       PApplet.main("Main",args);
    }
    public void settings(){
        //play window:h540, player hub: h200
        originalW = 960;
        originalH = 540;
        displayWK = 1.0;
        displayHK = 1.0;
        size(960,540);
    }
    public void loadAssets(){
        processing = this;
        engine = new GEngine();
        loads = true;
    }

    public void setup(){
        surface.setTitle("Greener Fields");
        surface.setResizable(true);
        surface.setLocation(10, 10);
        thread("loadAssets");
    }



    public void draw(){
        time += .01;
        background(0,0,0);
        if(loads) {
            //if (processing.width - originalW > 10 || processing.height - originalH>10) {
              //  resize();
            //}
            engine.start();
        }

    }

    public void resize(){
        displayWK = processing.width / originalW;
        displayHK = processing.height / originalH;
        engine.resize(displayWK,displayHK);
    }

    public void keyPressed(){
        switch(keyCode){
            case 'A' : engine.player.left = true;break;
            case 'W' : engine.player.up = true;break;
            case 'S' : engine.player.down = true;break;
            case 'D' : engine.player.right = true;break;
        }
    }

    public void keyReleased(){
        switch(keyCode){
            case 'A' : engine.player.left = false;break;
            case 'W' : engine.player.up = false;break;
            case 'S' : engine.player.down = false;break;
            case 'D' : engine.player.right = false;break;
        }
    }
}