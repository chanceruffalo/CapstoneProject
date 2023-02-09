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
        inventoryUI = new Animation("ImageAssets/inventoryUI",10,290,70);
        //font = Main.processing.createFont("TheConfessionRegular-YBpv.ttf",128);
    }

    public void display(){
        displayPlayerInventory();
        }

    public void displayPlayerInventory(){
        int slot = 0;
        Main.processing.fill(250,0,0);
        float pHealthK= (Main.engine.player.health / Main.engine.player.maxHealth);
        Main.processing.rect(x+293,y+70, (healthBarLength*pHealthK),20);
        Main.processing.fill(0,0,250);
        Main.processing.rect(x+450,y+70, experienceBarLength*(Main.engine.player.experience/Main.engine.player.maxExperience),18);


        Main.processing.image(inventoryUI.display(),290,470);
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
        Main.processing.textSize(15);
        Main.processing.fill(0,0,0);
        if(Main.engine.player.level > 9){
            Main.processing.text( Main.engine.player.level ,x+ 422,y+84);
        }
        else{
            Main.processing.text( Main.engine.player.level ,x+ 426,y+84);
        }
    }
}
