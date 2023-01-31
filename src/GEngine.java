public class GEngine {

    MapSystem home,currentMap;
    mapPresets mappresets;
    static Player player;
    static UI ui;

    public GEngine(){
        mappresets = new mapPresets();
        home = new MapSystem(mappresets);
        currentMap = home;
        player = new Player();
        ui = new UI();
    }

    public void start(){
        currentMap.displayBackground();
        player.display();
        currentMap.displayForground();
        ui.display();
    }

    public void resize(double displayWk,double displayHk){
        currentMap.resize(displayWk,displayHk);
        player.resize(displayWk,displayHk);
    }


}
