import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Player extends Graphic{
     float x,y,w,h,dx,dy,speed,attack,defense,health,maxHealth,experience,maxExperience,grabRange,footY;
     int i,j,level,emptySpot;
     boolean up,down,left,right,interact;
     Point[] contactPoints,viewPoints;
     Item[] inventory;
     Weapon weapon;

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
         grabRange = 10;
         up = false;
         down = false;
         left = false;
         right = false;
         interact = false;
         dx = 0;
         dy = 0;
         x = 800;
         y = 400;
         value = y +h;
         i = 0;
         j = 0;
         w = 49;
         h = 84;
         inventory = new Item[20];
         emptySpot = 0;
         weapon = null;
         footY = 20;
         viewPoints = new Point[]{
                 new Point(x,y),new Point(x+w,y),new Point(x,y+h), new Point(x+w,y+h)
         };
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

         rightIdle = new Animation("ImageAssets/MC1_",1,(int)w,(int)h);
         leftIdle = new Animation("ImageAssets/MC1_right",1,(int)w,(int)h);
         current = leftIdle;

     }

     public void display(){
         checkCoordinates();
         Main.processing.image(current.display(),x,y);
         if(weapon != null){
             weapon.display();
         }
     }


     public void checkinteractions(){
         emptySpot = emptySpot();
         Item copy = Main.engine.currentMap.playerInteract(emptySpot);
         if(copy != null) {
             boolean haveItem = false;
             for (Item x : inventory) {
                 if (x != null && x.name == copy.name) {
                     haveItem = true;
                     x.uses += 1;
                     break;
                 }
             }
             if (!haveItem) {
                 inventory[emptySpot] = copy;
             }
         }
     }
     //move location
    public void moveLocation(float x, float y){
         this.x = x;
         this.y = y;
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
        viewPoints = new Point[]{
                new Point(x,y),new Point(x+w,y),new Point(x,y+h), new Point(x+w,y+h)
        };
    }

     public int emptySpot(){
         emptySpot = 0;
         for(Item x : inventory){
             if(x == null){
                 return emptySpot;
             }
             emptySpot ++;
         }
         return -1;
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
         for(Point p:viewPoints){
             p.x += dx;
             p.y += dy;
         }
         x += dx;
         y += dy;
         //check if an item can be interacted with
         Main.engine.currentMap.checkInteractable(contactPoints);
         if(!Main.engine.currentMap.checkBoarder(contactPoints)){
             for(Point p:contactPoints){
                 p.x -= dx;
                 p.y -= dy;
             }
             for(Point p:viewPoints){
                 p.x -= dx;
                 p.y -= dy;
             }
             x -= dx;
             y -= dy;
         }
         dx = 0;
         dy = 0;
         value = y+h;

     }

     public void useAbility(int ability){
         Item currentItem = inventory[ability];
                 if(currentItem != null){
                     Main.engine.ui.abilityHighlight = ability;
                     //check if item trying to be used in a weapon and equiping if so
                     if(currentItem.isWeapon == true){
                         weapon = new Weapon(inventory[ability]);
                     }
                     //if not item just use item
                     else {
                         inventory[ability].use();
                         //check if max health has been achieved
                         if(health > maxHealth){
                             health = maxHealth;
                         }
                         //check if item has been used up
                         if (inventory[ability].uses <= 0) {
                             inventory[ability] = null;
                         }
                     }
                 }
                 while(experience >=maxExperience){
                     level ++;
                     experience -= maxExperience;
                 }
             }

     public void useWeapon(float mouseX,float mouseY){
         if(weapon != null){
            weapon.use(mouseX,mouseY);
         }
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






}
