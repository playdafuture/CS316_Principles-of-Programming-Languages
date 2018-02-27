public class Var {
    String v;
    
    public Var(String v1) {
        v = v1;
    }
    
    void printParseTree(String indent){
        IO.displayln(indent + "<var> " + v);
    }
}
