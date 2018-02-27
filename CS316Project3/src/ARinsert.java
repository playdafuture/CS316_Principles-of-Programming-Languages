class ARinsert extends AR {
    BST target; // the target BST object for a call to insert()
    int k;
    BST returnVal;

    @Override
    public String toString() {
        return "ARinsert "+"target = "+target+" k = "+k;
    }

    void insert() {   
        if (target instanceof EmptyBST) {
            RuntimeStack.display();            
        } else {
            returnVal = target.insert(k);
        }
    }
}