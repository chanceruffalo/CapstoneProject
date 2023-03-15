import processing.core.PFont;

public class UI {
    float x,y,inventoryBarWidth,inventoryBarHeight;
    float healthBarLength,experienceBarLength;

    Animation inventoryUI;
    int abilityHighlight;

    public UI(){
        x = 5;
        y = 400;
        healthBarLength = 117;
        experienceBarLength = 117;
        inventoryBarWidth = 150;
        inventoryBarHeight = 40;
        abilityHighlight = -1;
        inventoryUI = new Animation("ImageAssets/inventoryUI",10,290,70);
        //font = Main.processing.createFont("TheConfessionRegular-YBpv.ttf",128);
    }

    public void display(){
        //method call for player inventory
        displayPlayerInventory();
        //display map boarder if one available

        }


    public void displayPlayerInventory(){
        Main.processing.fill(0,0,0);

        //black background for health and experience bar
        Main.processing.rect(x+293,y+70,117,100);
        Main.processing.rect(x+450,y+70,117,100);
        int slot = 0;
        Main.processing.fill(250,0,0);
        float pHealthK= (Main.engine.player.health / Main.engine.player.maxHealth);
        Main.processing.rect(x+293,y+70, (healthBarLength*pHealthK),20);
        Main.processing.fill(0,0,250);
        Main.processing.rect(x+450,y+70, experienceBarLength*(Main.engine.player.experience/Main.engine.player.maxExperience),18);

        //Display items
        Main.processing.image(inventoryUI.display(),290,470);
        for(Item item: Main.engine.player.inventory){
            if(item != null){
                item.animation.setSize(24,24);
                item.x = 301 + 27*slot;
                item.y = 495;
                slot++;
                item.display();
                if(!item.isWeapon){
                    Main.processing.textSize(10);
                    Main.processing.fill(250,250,250);
                    Main.processing.text(item.uses,item.x,item.y+5);
                }

            }
            else {
                slot++;
            }
        }
        Main.processing.fill(250,250,250);
        //item highlight
        if(abilityHighlight != -1) {
            Main.processing.noFill();
            Main.processing.rect(300 + 27 * abilityHighlight, 494, 25, 25);
        }
        Main.processing.textSize(15);

        if(Main.engine.player.level > 9){
            Main.processing.text( Main.engine.player.level ,x+ 422,y+84);
        }
        else{
            Main.processing.text( Main.engine.player.level ,x+ 426,y+84);
        }
        //display bullet counts

        Main.processing.text(Main.engine.currentMap.bulletCount,x+422,y+44);


    }
}
