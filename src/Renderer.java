import java.util.ArrayList;
import java.util.List;

public class Renderer {
    RenderNode first;



    public Renderer(){
        first = null;

    }

    public void push(Graphic graphic){
        if(first == null){
            first = new RenderNode(graphic);
        }
        else{
            RenderNode temp = new RenderNode(graphic);
            temp.next = first;
            first = temp;
        }
    }

    public void addGraphics(Graphic[] graphics){
        for(Graphic x : graphics) {
            if (x != null) {
                if (first == null) {
                    first = new RenderNode(x);

                } else {
                    RenderNode temp = new RenderNode(x);
                    temp.next = first;
                    first = temp;
                }
            }
        }
    }

    public void display(){
        first = null;
        addGraphics(Main.engine.currentMap.buildings);
        addGraphics(Main.engine.currentMap.items);
        addGraphics(Main.engine.currentMap.bullets);
        addGraphics(Main.engine.currentMap.baddies);
        push(Main.engine.player);
        first = mergeSort(first);
        RenderNode pointer = first;
        Graphic player = null;
        while(pointer != null){
            //finds player in stack as it traverses
            if(pointer.graphic == Main.engine.player){
                player = pointer.graphic;
                pointer.graphic.display();
                pointer = pointer.next;
            }
            //displays graphics without tints
            else if(player == null) {
                pointer.graphic.display();
                pointer = pointer.next;
            }
            //checks if a tint needs to be applied
            else{
                Graphic temp = pointer.graphic;
                if(player != null) {
                    boolean behindCharacter = false;
                    //checks for all points of character and applies tint if the player is behind it
                    for (Point p : Main.engine.player.contactPoints) {
                        if (p.x > temp.x && p.x < temp.x + temp.w && p.y > temp.y && p.y < temp.y + temp.h) {
                            Main.processing.tint(255, 126);
                            pointer.graphic.display();
                            pointer = pointer.next;
                            Main.processing.noTint();
                            behindCharacter = true;
                            break;
                        }
                    }
                    // wont apply tint since player is'nt directly behind it
                    if(!behindCharacter) {
                        pointer.graphic.display();
                        pointer = pointer.next;
                    }
                }
            }

        }
        //display map boarder if one is avaiable
        if(Main.engine.currentMap.mapBoarder != null) {
            Main.processing.image(Main.engine.currentMap.mapBoarder.display(), 0, 0);
        }
    }

    public RenderNode sortedMerge(RenderNode a, RenderNode b)
    {
        RenderNode result = null;
        /* Base cases */
        if (a == null)
            return b;
        if (b == null)
            return a;


        if (a.graphic.value <= b.graphic.value) {
            result = a;
            result.next = sortedMerge(a.next, b);
        }
        else {
            result = b;
            result.next = sortedMerge(a, b.next);
        }
        return result;
    }

    public RenderNode mergeSort(RenderNode h)
    {
        // Base case : if head is null
        if (h == null || h.next == null) {
            return h;
        }

        // get the middle of the list
        RenderNode middle = getMiddle(h);
        RenderNode nextofmiddle = middle.next;

        // set the next of middle node to null
        middle.next = null;

        // Apply mergeSort on left list
        RenderNode left = mergeSort(h);

        // Apply mergeSort on right list
        RenderNode right = mergeSort(nextofmiddle);

        // Merge the left and right lists
        RenderNode sortedlist = sortedMerge(left, right);
        return sortedlist;
    }

    // Utility function to get the middle of the linked list
    public static RenderNode getMiddle(RenderNode head)
    {
        if (head == null)
            return head;

        RenderNode slow = head, fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


}
