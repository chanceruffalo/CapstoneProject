public class GEngine {

    MapSystem currentMap,base,level1;
    mapPresets mappresets,mp2;
    Renderer renderer;
    static Player player;
    static UI ui;

    public GEngine(){
        mappresets = new mapPresets();
        //base = new MapSystem(mappresets);
        currentMap = new MapSystem(mappresets);
        player = new Player();
        ui = new UI();
        renderer = new Renderer();
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

    public void changeMap(int x){

        if( x == 0){
            currentMap = base;
            //player x,y start location
            player.moveLocation(870,220);
        }
        else {
            base = currentMap;
            mp2 = new mapPresets();
            mp2.changeCurrent(x);
            currentMap = new MapSystem(mp2);
            player.moveLocation(870,220);
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
