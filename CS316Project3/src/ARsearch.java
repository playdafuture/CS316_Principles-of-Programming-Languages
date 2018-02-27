class ARsearch extends AR {
    BST target; // the target BST object for a call to search()
    int k;
    BST t; // temporary var to store the return value
    BST returnVal;

    @Override
    public String toString() {
        return "ARsearch "+"target = "+target+" k = "+k;
    }

    void search() {
        if (t == target) {
            //results found
            RuntimeStack.display();     
        }
        returnVal = t;
    }
}