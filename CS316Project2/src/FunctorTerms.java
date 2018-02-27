public class FunctorTerms {
    Functor f;
    Terms ts;
    
    public FunctorTerms (Functor f1) {
        f = f1;
    }
    
    public FunctorTerms (Functor f1, Terms t1) {
        f = f1;
        ts = t1;        
    }
    
    void printParseTree(String indent) {
        f.printParseTree(indent);
        if (ts != null) {
            ts.printParseTree(indent);
        }
    }
}
