class Functor {    
    String functor;
    
    Functor(String f) {
        functor = f;        
    }
    
    void printParseTree(String indent){
        IO.displayln(indent + "<functor> " + functor);
    }
}
