public class Clauses {
    Clause  c; //Single clause
    Clauses cs;//Multiple clauses
    
    public Clauses(Clause c1) {
        c = c1;
    }    
    
    public Clauses(Clause c1, Clauses c2) {
        c = c1;
        cs = c2;
    }
    
    void printParseTree(String indent){
        IO.displayln("<clause>");
        c.printParseTree(indent + " ");
        if (cs != null) {
            IO.displayln("");
            IO.displayln("--------------------");
            IO.displayln("");
            cs.printParseTree(indent);
        }
    }
          
}
