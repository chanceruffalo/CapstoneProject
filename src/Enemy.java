public class Enemy extends Graphic{
    float x,y,w,h,dx,dy,speed,attack,defense,health,maxHealth,experience;
    int i,j,level,movementCounter;
    boolean up,down,left,right,playerSpotted;
    Weapon weapon;
    Animation current, rightIdle,leftIdle;
    Point[] contactPoints;
    public Enemy(float x, float y,float w, float h,float speed, float attack, float defense, float health, float maxHealth, float experience, Weapon weapon, int level,String address){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.speed = speed;
        this.attack = attack;
        this.defense = defense;
        this.health = health;
        this.maxHealth = maxHealth;
        this.experience = experience;
        this.weapon = weapon;
        this.level = level;
        float footY = 10;
        //for renderer comparison
        value = y + h;
        movementCounter = 0;
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
        dx = (float) ((Math.random()*speed) - speed);
        dy = (float) ((Math.random()*speed) - speed);
        playerSpotted = false;
        up = false;
        down = false;
        left = false;
        right = false;
        rightIdle = new Animation(address,10,(int)w,(int)h);
        leftIdle = new Animation(address+"_left",10,(int)w,(int) h);
        current = leftIdle;
    }

    public void display(){
        move();
        value = y + h;
        Main.processing.image(current.display(),x,y);
    }

    public void move(){
        if(!playerSpotted){
            getDxy();



        }
    }

    public void getDxy(){
        int movementConstant = 100;
        if(movementCounter == movementConstant){
            movementCounter = 0;
            dx = (float) ((Math.random()*speed) - speed/2);
            dy = (float) ((Math.random()*speed) - speed/2);
            if((x > -1 && x < 1) || (y > -1 && y < 1)){
                current.frameRate = 10;
            }
            else {
                current.frameRate = 4;
            }
        }
        //check building collision
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
            movementCounter = movementConstant -1;
        }
        if(dx > 0){
            current = rightIdle;
        }
        else{
            current = leftIdle;
        }

        movementCounter ++;
    }


}
