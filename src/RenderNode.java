public class RenderNode {
    Graphic graphic;
    RenderNode next;
    public RenderNode(Graphic graphic){
        this.graphic = graphic;

        next = null;
    }
    public void display(){
        graphic.display();
    }
}
