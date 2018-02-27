/**
 *
 * @author Future
 */
public class ARfib extends AR {
    int n; // parameter
    int t1; // temporary var to store the value of fib(n-1)
    int t2; // temporary var to store the value of fib(n-2)
    int returnVal;

    public String toString() {
        return "ARfib "+"n = "+n+" t1 = "+t1+" t2 = "+t2;
    }
    
    void fib() {
        if (n <= 1) {
            if (n == 0) {
                RuntimeStack.display();
            }
            this.returnVal = n;
        } else {  
            ARfib newfib1 = new ARfib();
            newfib1.n = this.n - 1;            
            RuntimeStack.push(newfib1);
            newfib1.fib();
            this.t1 = newfib1.returnVal;
            RuntimeStack.pop();
            
            ARfib newfib2 = new ARfib();
            newfib2.n = this.n - 2;
            RuntimeStack.push(newfib2);
            newfib2.fib();
            this.t2 = newfib2.returnVal;
            RuntimeStack.pop();
            
            this.returnVal = this.t1 + this.t2;
        }
        
    }
    
    public static void main(String argv[]) {
        if (argv.length > 0) {
            Output.setOutput( argv[0] );
        }
        ARfib newARfib = new ARfib();
        newARfib.n = 10;
        RuntimeStack.push(newARfib);
        newARfib.fib();
        System.out.println(newARfib.returnVal);
        RuntimeStack.pop();
        if (argv.length > 0) {
            Output.closeOutput();
        }
    }            
}
