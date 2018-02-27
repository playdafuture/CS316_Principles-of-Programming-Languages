public class ARmixFactFib extends AR {
    int n; // parameter
    int t1; // temporary var to store the value of factOrFib(n)
    int t2; // temporary var to store the value of factOrFib(n-1)
    int returnVal;    
    
    public String toString() {
        return "ARmixFactFib n = " + n+" t1 = "+t1+" t2 = "+t2;
    }
    
    void mixFactFib() {
        //mixFactFib(n) = factOrFib(n)+factOrFib(n-1);
        ARmixFactFib ForF = new ARmixFactFib();
        ForF.n = this.n;            
        RuntimeStack.push(ForF);
        ForF.factOrFib();
        this.t1 = ForF.returnVal;
        RuntimeStack.pop();
        
        ARmixFactFib ForF1 = new ARmixFactFib();
        ForF1.n = this.n - 1;            
        RuntimeStack.push(ForF1);
        ForF1.factOrFib();
        this.t2 = ForF1.returnVal;
        RuntimeStack.pop();
        
        this.returnVal = this.t1 + this.t2;
    }
    
    void factOrFib() {        
        //System.out.println(">> running factOrFib " + n);
        if (n%2 == 0) {
            ARfact newARfact = new ARfact();
            newARfact.n = n;
            RuntimeStack.push(newARfact);
            newARfact.fact();
            this.returnVal = newARfact.returnVal;
            //System.out.println(">>>newARfact " + n +" returned " + newARfact.returnVal);
            RuntimeStack.pop();
        } else {
            ARfib newARfib = new ARfib();
            newARfib.n = n;
            RuntimeStack.push(newARfib);
            newARfib.fib();
            this.returnVal = newARfib.returnVal;
            //System.out.println(">>>newARfib " + n +" returned " + newARfib.returnVal);
            RuntimeStack.pop();
        }
    }
    
    public static void main(String argv[]) {
        if (argv.length > 0) {
            Output.setOutput( argv[0] );
        }
        ARmixFactFib newARmixFactFib = new ARmixFactFib();
        newARmixFactFib.n = 5;
        RuntimeStack.push(newARmixFactFib);
        newARmixFactFib.mixFactFib();
        System.out.println(newARmixFactFib.returnVal);
        RuntimeStack.pop();
        if (argv.length > 0) {
            Output.closeOutput();
        }
    }
}
