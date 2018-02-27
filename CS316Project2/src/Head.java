public class Head {
    Functor f;
    Terms t;
    
    public Head (Functor f1) {
        f = f1;
    }
    
    public Head (Functor f1, Terms t1) {
        f = f1;
        t = t1;
    }
    
    void printParseTree(String indent) {
        IO.displayln("");
        IO.displayln(indent + "<head>");
        f.printParseTree(indent + " ");
        if (t != null) {
            t.printParseTree(indent + " ");
        }
    }
}
