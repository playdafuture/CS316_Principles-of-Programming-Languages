public class Term {
    Var v;
    Number n;
    FunctorTerms ft;
    List l;
    
    public Term(Var v1) {
        v = v1;
    }
    
    public Term(Number n1) {
        n = n1;
    }
    
    public Term(FunctorTerms f1) {
        ft = f1;
    }
    
    public Term(List l1) {
        l = l1;
    }    
    
    void printParseTree(String indent) {
        if (v != null) {
            v.printParseTree(indent);
        } else if (n != null) {
            n.printParseTree(indent);
        } else if (ft != null) {
            ft.printParseTree(indent);
        } else if (l != null) {
            l.printParseTree(indent);
        } else {
            IO.displayln("NULL TERM");
        }        
    }
}
