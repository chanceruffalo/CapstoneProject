public class GEngine {
    boolean startGame;
    Library library;

    static MapSystem currentMap,base;
    mapPresets mappresets,mp2;
    Renderer renderer;
    static Player player;
    static UI ui;
    Animation menuScreen;

    public GEngine(){
        startGame = false;
        this.library = Main.library;
        menuScreen = library.menuScreen;
        mappresets = new mapPresets();
        currentMap = new MapSystem(mappresets);
        player = new Player();
        ui = new UI();
        renderer = new Renderer();
    }

    public void start(){
        if(startGame) {
            currentMap.displayBackground();
            renderer.display();
            ui.display();
        }
        else{
            Main.processing.image(menuScreen.display(),0,0);
        }
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
            base = currentMap;
            mp2 = new mapPresets();
            mp2.changeCurrent(x);
            currentMap = new MapSystem(mp2);
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
