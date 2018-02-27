public class BuiltInRelation {
    Var vLeft;
    Number nLeft;
    FunctorTerms fLeft;
    List lLeft;
    
    String op;
    
    ArithExp ae;
    Var vRight;
    Number nRight;
    Term tRight;
    
    public BuiltInRelation(Var v1, String s, ArithExp ae1) {
        //<var> "is" <arith exp> 
        vLeft = v1;
        op = s; //this should be "is"
        ae = ae1;
    }
    
    public BuiltInRelation(Var v1, String s, Var v2) {
        //<var> <comp op 1> <var> 
        vLeft = v1;
        op = s; //<comp op 1> → "<" | "=<" | ">" | ">=" 
        vRight = v2;
    }
    
    public BuiltInRelation(Var v1, String s, Number n2) {
        //<var> <comp op 1> <number> 
        vLeft = v1;
        op = s; //<comp op 1> → "<" | "=<" | ">" | ">=" 
        nRight = n2;
    }
    
    public BuiltInRelation(Var v1, String s, Term t2) {
        //<var> <comp op 2> <term> 
        vLeft = v1;
        op = s; //<comp op 2> → "=" | "\=" 
        tRight = t2;
    }
    
    public BuiltInRelation(Number n1, String s, Var v2) {
        //<number> <comp op 1> <var> 
        nLeft = n1;
        op = s; //<comp op 1> → "<" | "=<" | ">" | ">=" 
        vRight = v2;
    }
    
    public BuiltInRelation(Number n1, String s, Number n2) {
        //<number> <comp op 1> <number> 
        nLeft = n1;
        op = s; //<comp op 1> → "<" | "=<" | ">" | ">=" 
        nRight = n2;
    }
    
    public BuiltInRelation(Number n1, String s, Term t2) {
        //<var> <comp op 2> <term> 
        nLeft = n1;
        op = s; //<comp op 2> → "=" | "\=" 
        tRight = t2;
    }
    
    public BuiltInRelation(FunctorTerms f1, String s, Term t2) {
        //<functorTerms> <comp op 2> <term> 
        fLeft = f1;
        op = s; //<comp op 2> → "=" | "\=" 
        tRight = t2;
    }
    
    public BuiltInRelation(List l1, String s, Term t2) {
        //<list> <comp op 2> <term> 
        lLeft = l1;
        op = s; //<comp op 2> → "=" | "\=" 
        tRight = t2;
    }
    
    void printParseTree(String indent) {
        if (vLeft != null) {
            vLeft.printParseTree(indent + " ");
        } else if (nLeft != null) {
            nLeft.printParseTree(indent + " ");
        } else if (fLeft != null) {
            fLeft.printParseTree(indent + " ");
        } else if (lLeft != null) {
            lLeft.printParseTree(indent + " ");
        }
        if (!op.equals("is")) { //Not sure why!?!?
            IO.displayln(indent + " " + op);
        }        
        if (ae != null) {
            ae.printParseTree(indent + " ");
        } else if (vRight != null) {
            vRight.printParseTree(indent + " ");
        } else if (nRight != null) {
            nRight.printParseTree(indent + " ");
        } else if (tRight != null) {
            tRight.printParseTree(indent + " ");
        }
    }
}
