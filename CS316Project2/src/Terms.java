public class Terms {
    Term t;
    Terms ts;
    
    public Terms(Term t1) {
        t = t1;
    }
    
    public Terms(Term t1, Terms t2) {
        t = t1;
        ts = t2;
    }
    
    void printParseTree(String indent){
        t.printParseTree(indent + " ");
        if (ts != null) {
            ts.printParseTree(indent);
        }
    }
}
