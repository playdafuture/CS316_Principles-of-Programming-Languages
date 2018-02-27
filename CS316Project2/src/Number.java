public class Number {
    String type;
    String val;
    public Number(String t, String v) {
        type = t.toLowerCase();
        if (type.equals("floate")) {
            type = "float"; //not sure why!?!?
        }
        val = v;
    }
    
    void printParseTree(String indent) {
        IO.displayln(indent + "<" + type + "> " + val);
    } 
}
