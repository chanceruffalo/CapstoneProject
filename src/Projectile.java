public class Projectile extends Graphic{
    float x,y,w,h,dx,dy;
    float attack,speed;
    boolean isFriendly;
    Animation current;
    String address;
    int index;
    public Projectile(float x,float y,float w,float h, float attack, float speed, String address,boolean isFriendly){
     this.x = x;
     this.y = y;
     this.w = w;
     this.h = h;
     index = -1;
     this.isFriendly = isFriendly;
     dx = 0;
     dy= 0;
     this.attack = attack;
     this.speed = speed;
     this.address = address;
     current = new Animation(address,10,(int)w,(int)h);
    }

    public Projectile(Projectile i){
        this.x = i.x;
        this.y = i.y;
        this.w = i.w;
        this.h = i.h;
        this.isFriendly = i.isFriendly;
        dx = 0;
        dy= 0;
        this.attack = i.attack;
        this.speed = i.speed;
        this.address = i.address;
        index = i.index;
        current = new Animation(address,10,(int)w,(int)h);
    }
    public void display() {

            //rotate object based on the direction its going
            float rot = (float)Math.atan(dy/dx);
            if(dx > 0){
                rot -= (float)3.14159/2;
            }
            else{
                rot += (float)3.14159/2;
            }
            Main.processing.pushMatrix();
            Main.processing.translate(x+w/2,y+h/2);
            Main.processing.rotate(rot);
            Main.processing.image(current.display(), 0, 0);
            Main.processing.popMatrix();
        x += dx * speed/100;
        y += dy * speed/100;
        //check if the bullet should be deleted because of collision
        if (x < -50 || y < -50 || x > Main.originalW+50 || y > Main.originalH+50 || Main.engine.currentMap.checkBulletCollision(this)) {
            Main.engine.currentMap.deleteProjectile(index);
        }


        }

    }

