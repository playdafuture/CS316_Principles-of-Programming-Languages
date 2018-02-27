public class ArithExp {
    Var vLeft;
    Number nLeft;
    String op;
    Var vRight;
    Number nRight;
    
    public ArithExp(Var v1, String s, Var v2) {
        vLeft = v1;
        op = s; //<arith op> → + | − | * | / 
        vRight = v2;
    }
    
    public ArithExp(Var v1, String s, Number n2) {
        vLeft = v1;
        op = s; //<arith op> → + | − | * | / 
        nRight = n2;
    }
    
    public ArithExp(Number n1, String s, Var v2) {
        nLeft = n1;
        op = s; //<arith op> → + | − | * | / 
        vRight = v2;
    }
    
    public ArithExp(Number n1, String s, Number n2) {
        nLeft = n1;
        op = s; //<arith op> → + | − | * | / 
        nRight = n2;
    }
    
    void printParseTree(String indent) {
        if (vLeft != null) {
            vLeft.printParseTree(indent);
        } else {
            nLeft.printParseTree(indent);
        }
        IO.displayln(indent + op);
        if (vRight != null) {
            vRight.printParseTree(indent);
        } else {
            nRight.printParseTree(indent);
        }
    }
}
