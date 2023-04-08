public class Enemy extends Graphic{
    float x,y,w,h,dx,dy,speed,attack,defense,health,maxHealth,experience,footY,projectileSpeed;
    int i,j,level,movementCounter,recentDamageTimer,weaponCoolDown,weaponCoolDownRate;
    boolean up,down,left,right,playerSpotted;
    String name,address,recentDamage;
    //this array will show damage done as floating numbers above enemy
    Weapon weapon;
    Projectile projectile;

    Animation current, rightIdle,leftIdle,alert;
    public Enemy(String name,float x, float y,float w, float h,float speed,float projectileSpeed, float attack,int weaponCoolDownRate, float defense, float health, float maxHealth, float experience, Weapon weapon, int level,String address){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.speed = speed;
        this.attack = attack;
        this.weaponCoolDownRate = weaponCoolDownRate;
        this.projectileSpeed = projectileSpeed;
        this.defense = defense;
        this.health = health;
        this.maxHealth = maxHealth;
        this.experience = experience;
        this.weapon = weapon;
        this.level = level;
        this.footY = 10;
        this.name = name;
        this.address = address;
        weaponCoolDown = 0;
        projectile = new Projectile(x,y,25,25,attack,projectileSpeed,address+"Projectile",false);
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
        recentDamage = "";
        recentDamageTimer = 0;
    }
    //copy constructor
    public Enemy(Enemy e){
        this.x = e.x;
        this.y = e.y;
        this.w = e.w;
        this.h = e.h;
        this.speed = e.speed;
        this.attack = e.attack;
        this.weaponCoolDownRate = e.weaponCoolDownRate;
        this.weaponCoolDown = e.weaponCoolDown;
        this.defense = e.defense;
        this.health = e.health;
        this.maxHealth = e.maxHealth;
        this.experience = e.experience;
        this.weapon = e.weapon;
        this.projectile = e.projectile;
        this.level = e.level;
        this.footY = e.footY;
        this.name = e.name;
        this.address = e.address;
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

    public void takeDamage(float damage){
        if(defense < damage){
            recentDamage = "-" + (attack - defense);
            health -= damage- defense;
        }

        if(health <= 0){
            Main.engine.player.addExperience(experience);
            Main.engine.currentMap.deleteEnemy(x,y);
        }
    }


    public void display(){

        move(look());
        //render value for displaying properly
        value = y + h;
        Main.processing.image(current.display(),x,y);
        //display health bar
        float healthBarPaddingX = (float).05*w;
        float healthBarPaddingY = (float).05*h;
        if(health != maxHealth) {
            //health bar background
            Main.processing.fill(0, 0, 0);
            Main.processing.rect(x + healthBarPaddingX, y + healthBarPaddingY, w - healthBarPaddingX, 3);
            //health bar
            Main.processing.fill(250, 0, 0);
            float HealthK = health / maxHealth;
            Main.processing.rect(x + healthBarPaddingX, y + healthBarPaddingY, (w - healthBarPaddingX) * (HealthK), 3);

            //show recent damage
            if(recentDamage != "" && recentDamageTimer == 0){
                recentDamageTimer = 50;
            }
            if(recentDamageTimer != 0){
                recentDamageTimer --;
                Main.processing.fill(0,0,0);
                Main.processing.text(recentDamage,x+w,y+10+(float)(.5*recentDamageTimer));
                if(recentDamageTimer == 0){
                    recentDamage = "";
                }
            }

        }
    }
    // update view points based on direction
    public String look(){
        float xView = 400;
        float yView = 400;
        //if looking left
        if(current == leftIdle) {
            //code to see the field of vision
            //Main.processing.stroke(0);
            //Main.processing.noFill();
            //Main.processing.rect(x - xView+w/2,y - yView/2 + h/2,xView,yView);
            for (Point p : Main.engine.player.viewPoints) {
                if((p.x >  x - xView+w/2 && p.x < x + w/2)&&(p.y > y-yView/2 + h/2 && p.y < y+yView/2+h/2)){
                    //Main.processing.fill(0);
                    //Main.processing.rect(x - xView+w/2,y - yView/2 + h/2,xView,yView);
                    return "spotted";
                }

            }
        }
        //if looking right
        else{
           // Main.processing.stroke(0);
            //Main.processing.noFill();
            //Main.processing.rect(x + w/2,y - yView/2 + h/2,xView,yView);
            for (Point p : Main.engine.player.viewPoints) {
                if((p.x <  x + xView+w/2 && p.x > x + w/2)&&(p.y > y-yView/2 + h/2 && p.y < y+yView/2+h/2)){
                    //Main.processing.fill(0);
                    //Main.processing.rect(x + w/2,y - yView/2 + h/2,xView,yView);
                    return "spotted";
                }

            }
        }
        return "nothing";

    }
    public void move(String behavior){

        if(behavior.equals("spotted")){
            if(weaponCoolDown <= 0 && Main.engine.timer > 10) {
                Projectile temp = new Projectile(projectile);
                float px = GEngine.player.x;
                float py = GEngine.player.y;
                double distance = Math.sqrt((x - px) * (x - px) + (y - py) * (y - py));
                temp.dx = (px - x) / (float) distance;
                temp.dy = (py - y) / (float) distance;
                temp.x = x;
                temp.y = y;
                Main.engine.currentMap.addBullet(temp);
                weaponCoolDown = weaponCoolDownRate;
            }


        }
        else{
            getDxy();
        }
        weaponCoolDown --;

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

    public void changeCoordinates(float x, float y){
        this.x = x;
        this.y = y;
        contactPoints = new Point[]{new Point(x,y),new Point(x+w,y),new Point(x,y+h),new Point(x+w,y+h) };
    }


}
