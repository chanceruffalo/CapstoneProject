import processing.core.PFont;

public class UI {
    float x,y;
    float healthBarLength,experienceBarLength;

    public UI(){
        x = 5;
        y = 400;
        healthBarLength = 100;
        experienceBarLength = 100;
        //font = Main.processing.createFont("TheConfessionRegular-YBpv.ttf",128);
    }

    public void display(){
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
}
