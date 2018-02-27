public class Heap {
    static Obj root;
    static LinkedList allowcated = new LinkedList();
    static int idCount;
    static boolean reachable = false;
    
    public Heap() {
        allowcated = new LinkedList();
        idCount = 0;
        reachable = false;
    }
    
    public static void add(Obj o) {
        o.objId = idCount++;
        o.mark = reachable;
        allowcated.add(o);
    }
    
    static void markAndSweep() {
        Output.println("---------------");
        Output.println();
        Output.println("Mark-and-sweep GC emulation started.");
        Output.println();
        mark();
        Output.println();
        sweep();
        Output.println();
        Output.println("The unreachable objects have been deleted from the linked list.");
        Output.println();
    }
    
    static void mark() {
        Output.println("Mark phase started.");
        reachable = !reachable;
        Output.println("The value of \"reachable\" variable changed to " + reachable + ".");
        Output.println("The following reachable objects visited by traversal.");
        Output.println("Display format is mark:objID:(description of object).");
        Output.println();
        
        if (root != null)
        root.traverse(reachable);
    }
    
    static void sweep() {
        Output.println("Sweep phase started.");
        Output.println("The following allocated objects swept.");
        Output.println("Reachable objects are prefixed by +, unreachable objects by -.");
        Output.println();
                
        for (int i = 0; i < allowcated.cap; i++) {
            Obj iter = allowcated.get(i);
            if (iter == null) continue;
            if (iter.mark == reachable) {
                Output.print("+:");       
                Output.println(iter.toString());
            } else {
                Output.print("-:");
                Output.println(iter.toString());
                allowcated.delete(iter);
            }            
        }
    }
}
