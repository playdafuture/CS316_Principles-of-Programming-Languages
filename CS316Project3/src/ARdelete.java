public class ARdelete extends AR{
    BST target; // the target BST object for a call to delete()
    int t;
    int k;
    int pred;
    BST returnVal;
    
    @Override
    public String toString() {
        return "ARdelete "+"target = "+target+" k = "+k+" pred " + pred;
    }
    
    void delete() {
        if (t == k) {
            RuntimeStack.display();
        }
    }
}
