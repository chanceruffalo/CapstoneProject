import processing.core.PImage;

public class Player {
     float x,y,dx,dy,speed,attack,defense,health,maxHealth,experience,maxExperience;
     int i,j,w,h,level;
     boolean up,down,left,right;
     Point[] contactPoints;
     Item[] inventory;
     Animation current;

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
         contactPoints = new Point[]{new Point(x,y),new Point(x+w,y),new Point(x,y+h),new Point(x+w,y+h)};
         current = new Animation("ImageAssets/MC1_",2,w,h);

     }

     public void display(){
         checkCoordinates();
         Main.processing.image(current.display(),contactPoints[0].x,contactPoints[0].y);
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
         if(left){dx -=speed;}
         if(up){dy -=speed;}
         if(right){dx +=speed;}
         if(down){dy +=speed;}
         for(Point p:contactPoints){
             p.x += dx;
             p.y += dy;
         }
         if(!Main.engine.currentMap.checkBoarder(contactPoints)){
             for(Point p:contactPoints){
                 p.x -= dx;
                 p.y -= dy;
             }
         }
         dx = 0;
         dy = 0;

     }






}
