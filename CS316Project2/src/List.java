public class List {
    Terms ts;
    
    Term t;
    Var v;
    List l;
    
    public List(Terms t1) {
        ts = t1;
    }
    
    public List(Term t1, Var v1) {
        t = t1;
        v = v1;
    }
    
    public List(Term t1, List l1) {
        t = t1;
        l = l1;
    }
    
    void printParseTree(String indent) {
        if (t != null) {
            IO.displayln(indent + "<list with \"|\">");
            t.printParseTree(indent + " ");
            if (v != null) {
                v.printParseTree(indent + " ");
            } else {
                l.printParseTree(indent + " ");
            }
        } else {
            IO.displayln(indent + "<list of terms>");
            if (ts != null) {
                ts.printParseTree(indent + " ");
            }
            //else, empty terms
        }
    } 
}
