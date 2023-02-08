import processing.core.PFont;

public class UI {
    float x,y,inventoryBarWidth,inventoryBarHeight;
    float healthBarLength,experienceBarLength;

    Animation inventoryUI;

    public UI(){
        x = 5;
        y = 400;
        healthBarLength = 100;
        experienceBarLength = 100;
        inventoryBarWidth = 150;
        inventoryBarHeight = 40;
        inventoryUI = new Animation("ImageAssets/inventoryUI",10,290,55);
        //font = Main.processing.createFont("TheConfessionRegular-YBpv.ttf",128);
    }

    public void display(){
        displayPlayerInventory();
        //Main.processing.textFont(font);
        Main.processing.textSize(15);
            //health bar settings
        //health bar text
        Main.processing.fill(255,255,255);
        Main.processing.rect(x-2,y-18,120,20);
        //text health
        Main.processing.fill(0,0,0);
        float pHealthK= (Main.engine.player.health / Main.engine.player.maxHealth);
        Main.processing.text("Health: " + Main.engine.player.health + "/"+ Main.engine.player.maxHealth,x,y);
        //healthbar
        Main.processing.rect(x-2,y+5,healthBarLength,10);
        Main.processing.fill(250,0,0);
        Main.processing.rect(x-2,y+5, (healthBarLength*pHealthK),10);

            //Experience settings
        //experience bar text
        Main.processing.fill(255,255,255);
        Main.processing.rect(x-2,y+32,100,20);
        Main.processing.fill(0,0,0);
        Main.processing.text("Level: " + Main.engine.player.level ,x,y+50);
        //experiencebar
        Main.processing.rect(x-2,y+55,healthBarLength,10);
        Main.processing.fill(0,0,250);
        Main.processing.rect(x-2,y+55, experienceBarLength*(Main.engine.player.experience/Main.engine.player.maxExperience),10);
    }

    public void displayPlayerInventory(){
        int slot = 0;
        Main.processing.image(inventoryUI.display(),290,485);
        for(Item item: Main.engine.player.inventory){
            if(item != null){
                item.animation.setSize(24,24);
                item.x = 301 + 27*slot;
                item.y = 495;
                slot++;
                item.display();
                Main.processing.textSize(10);
                Main.processing.fill(250,250,250);
                Main.processing.text(item.uses,item.x,item.y+5);
            }
            else {
                slot++;
            }
        }
    }
}
