public class AtomicFormula {
    FunctorTerms fts;
    BuiltInRelation bir;
    
    public AtomicFormula (FunctorTerms f1) {
        fts = f1;
    }    
    
    public AtomicFormula (BuiltInRelation b1) {
        bir = b1;
    }
    
    void printParseTree(String indent) {
        IO.displayln("");
        IO.displayln(indent + "<atomic formula>");
        if (fts != null) {
            fts.printParseTree(indent+" ");
        } else {
            bir.printParseTree(indent);
        }        
    }
}
