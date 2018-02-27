public class Clause {
    Head h;
    AtomicFormulas af;
    
    public Clause(Head h1) {
        h = h1;
    }
    
    public Clause(Head h1, AtomicFormulas af1) {
        h = h1;
        af = af1;
    }
    
    void printParseTree(String indent) {
        h.printParseTree(indent);
        if (af != null) {
            af.printParseTree(indent);
        }
    }
}