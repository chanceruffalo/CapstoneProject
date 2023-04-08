import processing.core.PFont;

public class UI {
    float x,y,inventoryBarWidth,inventoryBarHeight;
    float healthBarLength,experienceBarLength;

    Animation inventoryUI;
    int abilityHighlight;
    String[] attributes;
    static boolean pause;

    public UI(){
        x = 5;
        y = 400;
        healthBarLength = 117;
        experienceBarLength = 117;
        inventoryBarWidth = 150;
        inventoryBarHeight = 40;
        abilityHighlight = -1;
        inventoryUI = new Animation("ImageAssets/inventoryUI",10,290,70);
        attributes = new String[]{"Health","Attack","Defense","Speed"};
        pause = false;
        //font = Main.processing.createFont("TheConfessionRegular-YBpv.ttf",128);
    }

    public void display(){
        if(pause){
            displayMenuScreen();
        }
        else {
            //method call for player inventory
            displayPlayerInventory();
            //display item menus
            displayItemDescriptions();
        }

        }

        public void displayMenuScreen(){
            float menuX = 200;
            float menuY = 100;
            float menuW = 560;
            float menuH = 340;
            Main.processing.fill(0,0,0);
            Main.processing.rect(menuX,menuY,menuW,menuH);

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

        //item highlight
        if(abilityHighlight != -1) {
            Main.processing.noFill();
            Main.processing.rect(300 + 27 * abilityHighlight, 494, 25, 25);
        }

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
        //display player level
        Main.processing.fill(250,250,250);
        Main.processing.textSize(15);
        if(Main.engine.player.level > 9){
            Main.processing.text( Main.engine.player.level ,x+ 422,y+84);
        }
        else{
            Main.processing.text( Main.engine.player.level ,x+ 426,y+84);
        }


    }

    public void displayItemDescriptions(){
        float mX = Main.processing.mouseX;
        float mY = Main.processing.mouseY;
        for(Item i: Main.engine.currentMap.items){
            if(i != null && mX > i.x && mX < i.x + i.w && mY > i.y && mY < i.y + i.h) {
                //regular items
                if(!i.isWeapon){
                    //box for descriptions
                    String name = i.name;
                    float widthOfTitle = Main.processing.textWidth(name)+6;
                    float widthOfHighlightBoarder = 2;
                    float cursor_offsetX = 10;
                    float cursor_offsetY = 3;
                    float innerBox_offsetX = mX + cursor_offsetX + widthOfHighlightBoarder;
                    float innerBox_offsetY = mY + cursor_offsetY + widthOfHighlightBoarder;
                        //checks if the menu would go off screen
                    if(widthOfTitle+mX > Main.originalW){
                        cursor_offsetX = -106;
                        cursor_offsetY = +3;
                        innerBox_offsetX = mX + cursor_offsetX + widthOfHighlightBoarder;
                        innerBox_offsetY = mY + cursor_offsetY + widthOfHighlightBoarder;
                        float middle_textX = innerBox_offsetX+50;
                        float transparencyLevel = 180;

                        //color highlight box
                        Main.processing.fill(28,28,28, transparencyLevel);
                        Main.processing.rect( mX +cursor_offsetX, mY+cursor_offsetY,100+ widthOfHighlightBoarder*2, 100);
                        //inside box
                        Main.processing.fill(129,129,129, 100);
                        Main.processing.rect(innerBox_offsetX, innerBox_offsetY,100, 96);
                        float textHeight = 14;
                        Main.processing.textSize(textHeight);
                        Main.processing.fill(0, 0, 0);
                        //DISPLAY TITLE
                        Main.processing.text(name,  middle_textX - Main.processing.textWidth(i.name)/2, innerBox_offsetY + textHeight);
                        Main.processing.noStroke();
                        Main.processing.rect(innerBox_offsetX + middle_textX, innerBox_offsetY + textHeight, (Main.processing.textWidth(name)), 1);
                        Main.processing.textSize(12);
                        Main.processing.text(i.description, innerBox_offsetX, innerBox_offsetY + 14 * 2, 1);
                        Main.processing.noStroke();
                        Main.processing.rect(middle_textX - Main.processing.textWidth(i.name)/2, innerBox_offsetY + textHeight,Main.processing.textWidth(i.name),1);


                    }
                    else {
                        cursor_offsetX = 8;
                        cursor_offsetY = +3;
                        innerBox_offsetX = mX + cursor_offsetX + widthOfHighlightBoarder;
                        innerBox_offsetY = mY + cursor_offsetY + widthOfHighlightBoarder;
                        float middle_textX = innerBox_offsetX+50;
                        float transparencyLevel = 180;

                        //color highlight box
                        Main.processing.fill(28,28,28, transparencyLevel);
                        Main.processing.rect( mX +cursor_offsetX, mY+cursor_offsetY,100+ widthOfHighlightBoarder*2, 100);
                        //inside box
                        Main.processing.fill(169,169,169, 100);
                        Main.processing.rect(innerBox_offsetX, innerBox_offsetY,100, 96);
                        float textHeight = 14;
                        Main.processing.textSize(textHeight);
                        Main.processing.fill(0, 0, 0);
                        //DISPLAY TITLE
                        Main.processing.text(name,  middle_textX - Main.processing.textWidth(i.name)/2, innerBox_offsetY + textHeight);
                        Main.processing.noStroke();
                        Main.processing.rect(innerBox_offsetX + middle_textX, innerBox_offsetY + textHeight, (Main.processing.textWidth(name)), 1);
                        Main.processing.textSize(12);
                        Main.processing.text(i.description, innerBox_offsetX, innerBox_offsetY + 14 * 2, 1);
                        Main.processing.noStroke();
                        Main.processing.rect(middle_textX - Main.processing.textWidth(i.name)/2, innerBox_offsetY + textHeight,Main.processing.textWidth(i.name),1);

                    }
                }
                //check if menu will go off screen

                //If item is weapon special menu
                if (i.isWeapon) {
                    //box for descriptions
                    String nameLevel = i.name + "-Power: " + (int)i.powerLevel;
                    float widthOfTitle = Main.processing.textWidth(nameLevel)+6;
                    float widthOfHighlightBoarder = 2;
                    float middle_textX = (widthOfTitle/2) -(Main.processing.textWidth(nameLevel)/2)  + widthOfHighlightBoarder;
                    float cursor_offsetX = 10;
                    float cursor_offsetY = 3;
                    float innerBox_offsetX =mX  + cursor_offsetX+widthOfHighlightBoarder;
                    float innerBox_offsetY = mY+ cursor_offsetY+widthOfHighlightBoarder;
                    float transparencyLevel = 180;
                    //LOW QUALITY WEAPON 0 - 600
                    if (i.powerLevel < 400.) {
                        //color highlight box
                        Main.processing.fill(0,204,0, transparencyLevel);
                        Main.processing.rect(mX + cursor_offsetX, mY + cursor_offsetY,widthOfTitle+ widthOfHighlightBoarder*2, 100);
                        //inside box
                        Main.processing.fill(0,102,0, 100);
                        Main.processing.rect(innerBox_offsetX, innerBox_offsetY, widthOfTitle- widthOfHighlightBoarder/2, 96);

                    }
                    // 601-1000
                    else if (i.powerLevel >= 400 && i.powerLevel < 1000) {
                        //color highlight box
                        Main.processing.fill(102, 255, 255,transparencyLevel);
                        Main.processing.rect(mX + cursor_offsetX, mY + cursor_offsetY, widthOfTitle+ widthOfHighlightBoarder*2, 100);
                        //inside box
                        Main.processing.fill(102, 102, 255,transparencyLevel);
                        Main.processing.rect(innerBox_offsetX, innerBox_offsetY, widthOfTitle- widthOfHighlightBoarder/2, 96);
                    }
                    //add more colors here

                    // text for weapons pop-up menu
                    float textHeight = 14;
                    Main.processing.textSize(textHeight);

                    Main.processing.fill(0,0,0);
                    //DISPLAY TITLE
                    Main.processing.text(nameLevel, innerBox_offsetX+middle_textX, innerBox_offsetY + textHeight);
                    Main.processing.noStroke();
                    Main.processing.rect(innerBox_offsetX+middle_textX, innerBox_offsetY + textHeight,(Main.processing.textWidth(nameLevel)),1);
                    Main.processing.textSize(14);
                    float widthOfText = Main.processing.textWidth("- Weapon -")+6;
                    middle_textX = (widthOfTitle+ widthOfHighlightBoarder*2)/2-(widthOfText/2) ;
                    Main.processing.text("- Weapon -",mX + cursor_offsetX+middle_textX,innerBox_offsetY + textHeight+11);
                    //DISPLAY WEAPON STATS
                    for(int j = 0; j < attributes.length; j ++){
                        String temp = attributes[j] + ": +" + i.statChanges[j];
                        Main.processing.text(temp,mX + cursor_offsetX+10,innerBox_offsetY + textHeight+14*(j+2));

                    }



                }
                //end transparency
                Main.processing.noTint();
            }
        }
    }

}
