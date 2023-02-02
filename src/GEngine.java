public class GEngine {

    MapSystem currentMap;
    mapPresets mappresets;
    Renderer renderer;
    static Player player;
    static UI ui;

    public GEngine(){
        mappresets = new mapPresets();
        currentMap = new MapSystem(mappresets);
        player = new Player();
        ui = new UI();
        renderer = new Renderer();
        renderer.addGraphics(currentMap.buildings);
        renderer.addGraphics(currentMap.items);
        renderer.push(player);
    }

    public void start(){
        currentMap.displayBackground();
        renderer.display();
        ui.display();
    }

    public void resize(double displayWk,double displayHk){
        currentMap.resize(displayWk,displayHk);
        player.resize(displayWk,displayHk);
    }


}
