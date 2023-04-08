public class GEngine {
    boolean startGame,playerDeath;
    Library library;

    static MapSystem currentMap,base;
    mapPresets mappresets,mp2;
    Renderer renderer;
    static Player player;
    static UI ui;
    Double timer;
    Animation menuScreen,playerDead;

    public GEngine(){
        startGame = false;
        playerDeath = false;
        this.library = Main.library;
        menuScreen = library.menuScreen;
        playerDead = library.deathScreen;
        mappresets = new mapPresets();
        currentMap = new MapSystem(mappresets);
        player = new Player();
        ui = new UI();
        ui.pause = false;
        renderer = new Renderer();
        base = currentMap;
        timer = 0.0;
    }

    public void start(){
        if(ui.pause){
            ui.display();
        }
        else {
            if (playerDeath) {
                Main.processing.image(playerDead.display(), 0, 0);
            } else if (startGame) {
                currentMap.displayBackground();
                renderer.display();
                ui.display();
            } else {
                Main.processing.image(menuScreen.display(), 0, 0);
            }
        }
        timer += .1;
    }

    public void playerDeath(){
        playerDeath = true;
    }

    public void resize(double displayWk,double displayHk){
        currentMap.resize(displayWk,displayHk);
        player.resize(displayWk,displayHk);
    }

    public void changeMap(int x){

        if( x == 0){
            currentMap = base;
            player.moveLocation(870,250);
        }
        else {
            mp2 = new mapPresets();
            mp2.changeCurrent(x);
            currentMap = new MapSystem(mp2);
            timer = 0.0;
        }

    }

    public void mousepressed(float mouseX,float mouseY){

    }

    public void mousereleased(float mouseX, float mouseY){
        if(Main.loads){
            player.useWeapon(mouseX,mouseY);
        }
    }


}
