import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Player {
     float x,y,dx,dy,speed,attack,defense,health,maxHealth,experience,maxExperience;
     int i,j,w,h,level;
     boolean up,down,left,right;
     Point[] contactPoints;
     Item[] inventory;
     Animation current,rightIdle,leftIdle;

     public Player(){
         health = 10;
         maxHealth = 40;
         maxExperience = 20;
         attack = 0;
         defense = 0;
         speed = 5;
         level = 0;
         experience = 1;
         up = false;
         down = false;
         left = false;
         right = false;
         dx = 0;
         dy = 0;
         x = 800;
         y = 400;
         i = 0;
         j = 0;
         w = 49;
         h = 84;
         inventory = new Item[20];
         float footY = 20;
         //determine contact points
         contactPoints = new Point[]{
                 //left side
                 new Point(x,y+h-footY),new Point(x,y + h - (footY/2)),new Point(x,y + h - (footY/4)),new Point(x,y + h),new Point(x,y + h - 3*(footY/4)),
                //right side
                 new Point(x+w,y+h-footY),new Point(x+w,y + h - (footY/2)),new Point(x+w,y + h - (footY/4)),new Point(x+w,y + h),new Point(x+w,y + h - 3*(footY/4)),
                 //up side
                 new Point(x+w,y+h-footY),new Point(x+w/4,y+h-footY),new Point(x+w/2,y+h-footY),new Point(x+3*(w/4),y+h-footY),
                 // down side
                 new Point(x+w,y+h),new Point(x+w/4,y+h),new Point(x+w/2,y+h),new Point(x+3*(w/4),y+h), new Point(x + w,y+h)
         };

         rightIdle = new Animation("ImageAssets/MC1_",2,w,h);
         leftIdle = new Animation("ImageAssets/MC1_right",2,w,h);
         current = leftIdle;

     }

     public void display(){
         checkCoordinates();
         Main.processing.image(current.display(),x,y);
     }

     public void resize(double displayWK, double displayHK){
         x *= displayWK;
         y *= displayHK;
         w *= displayWK;
         h *= displayHK;
         for(Point temp:contactPoints){
             temp.x *= displayWK;
             temp.y *= displayHK;
         }
         current.resize(displayWK, displayHK);
     }

     public void checkCoordinates(){
         if(left){
             dx -=speed;
             if(current != rightIdle){
                 current = rightIdle;
             }
         }
         if(up){dy -=speed;}
         if(right){
             dx +=speed;
             if(current != leftIdle){
                 current = leftIdle;
             }
         }
         if(down){dy +=speed;}
         for(Point p:contactPoints){
             p.x += dx;
             p.y += dy;
         }
         x += dx;
         y += dy;
         if(!Main.engine.currentMap.checkBoarder(contactPoints)){
             for(Point p:contactPoints){
                 p.x -= dx;
                 p.y -= dy;
             }
             x -= dx;
             y -= dy;
         }
         dx = 0;
         dy = 0;

     }






}
