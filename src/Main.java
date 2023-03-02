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
       // surface.setResizable(true);
        surface.setLocation(100, 100);

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

    public void mousePressed(){
        engine.mousepressed(mouseX,mouseY);
    }

    public void mouseReleased(){
        engine.mousereleased(mouseX,mouseY);
    }

    public void keyPressed(){
        switch(keyCode){
            case 'A' : engine.player.left = true;break;
            case 'W' : engine.player.up = true;break;
            case 'S' : engine.player.down = true;break;
            case 'D' : engine.player.right = true;break;
            case 'F' : engine.player.checkinteractions();break;
            case '1' : engine.player.useAbility(0);break;
            case '2' : engine.player.useAbility(1);break;
            case '3' : engine.player.useAbility(2);break;
            case '4' : engine.player.useAbility(3);break;
            case '5' : engine.player.useAbility(4);break;
            case '6' : engine.player.useAbility(5);break;
            case '7' : engine.player.useAbility(6);break;
            case '8' : engine.player.useAbility(7);break;
            case '9' : engine.player.useAbility(8);break;
            case '0' : engine.player.useAbility(9);break;
            case ' ' : engine.player.useWeapon(mouseX, mouseY);
        }
    }

    public void keyReleased(){
        switch(keyCode){
            case 'A' : engine.player.left = false;break;
            case 'W' : engine.player.up = false;break;
            case 'S' : engine.player.down = false;break;
            case 'D' : engine.player.right = false;break;
            case 'F' : engine.player.interact = false;break;
        }
    }
}