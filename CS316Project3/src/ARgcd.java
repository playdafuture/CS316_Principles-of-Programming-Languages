public class ARgcd extends AR {
    int x;
    int y;
    int returnVal;
    
    public String toString() {
        return "ARgcd x = "+x+" y = "+y;
    }
    
    void gcd() {
        if (x == y) {
            RuntimeStack.display();
            this.returnVal = x;
        } else if (x < y) {
            ARgcd newARgcd = new ARgcd();
            newARgcd.x = this.x;
            newARgcd.y = this.y-x;
            RuntimeStack.push(newARgcd);
            newARgcd.gcd();
            RuntimeStack.pop();
            this.returnVal = newARgcd.returnVal;
        } else {
            ARgcd newARgcd = new ARgcd();
            newARgcd.x = this.x-y;
            newARgcd.y = this.y;
            RuntimeStack.push(newARgcd);
            newARgcd.gcd();
            RuntimeStack.pop();
            this.returnVal = newARgcd.returnVal;
        }
    }
    
    public static void main(String argv[]) {
        if (argv.length > 0) {
            Output.setOutput( argv[0] );
        }
        ARgcd newARgcd = new ARgcd();
        newARgcd.x = 84;
        newARgcd.y = 36;
        RuntimeStack.push(newARgcd);
        newARgcd.gcd();
        System.out.println(newARgcd.returnVal);
        RuntimeStack.pop();
        if (argv.length > 0) {
            Output.closeOutput();
        }
    }
}
