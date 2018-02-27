public class AtomicFormulas {
    AtomicFormula af;
    AtomicFormulas afs;
    
    public AtomicFormulas(AtomicFormula af1) {
        af = af1;
    }
    
    public AtomicFormulas(AtomicFormula af1, AtomicFormulas af2) {
        af = af1;
        afs = af2;
    }
    
    void printParseTree(String indent) {        
        af.printParseTree(indent);
        if (afs != null) {
            afs.printParseTree(indent);
        }
    }
}
