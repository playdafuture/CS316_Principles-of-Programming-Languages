/**
 *
 * @author Future
 */
public abstract class AR {
    public static stack RuntimeStack;
    public static int height;
    public static int maxHeight;
    public static int funcCalls;
    public int dynamicLink;
    
    public AR() {
        RuntimeStack = new stack();
    }
    
    public class stack {   
        public AR[] frames;        
        public stack() {
            funcCalls++;
            if (RuntimeStack == null) {
                frames = new AR[100];
                //initialize
            } else {
                frames = RuntimeStack.frames;
            }       
        }
        
        public void display() {
            if (Output.valid) {
                fileOutput();
            } else {
                System.out.println("The total number of function calls so far = " + funcCalls);
                System.out.println("The maximum stack size so far = " + maxHeight);
                System.out.println();
                System.out.println("The contents of the runtime stack will be displayed from top to bottom:");
                System.out.println();

                int i = height;            
                while (frames[i] != null) {
                    System.out.println(frames[i].toString());
                    i = frames[i].dynamicLink;
                }
                System.out.println();
            }
        }
        
        public void fileOutput() {
            Output.displayln("The total number of function calls so far = " + funcCalls);
            Output.displayln("The maximum stack size so far = " + maxHeight);
            Output.displayln("");
            Output.displayln("The contents of the runtime stack will be displayed from top to bottom:");
            Output.displayln("");
            
            int i = height;            
            while (frames[i] != null) {
                Output.displayln(frames[i].toString());
                i = frames[i].dynamicLink;
            }
            Output.displayln("");
        }
        
        public void push(AR newFrame) {
            newFrame.dynamicLink = height;
            height++;
            try {
                frames[height] = newFrame;
            } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                System.out.println("Stack overflow");
                System.exit(0);
            }
            
            if (maxHeight < height) {
                maxHeight = height;
            }
        }
        
        public void pop() {
            height--;
        }
    }
}
