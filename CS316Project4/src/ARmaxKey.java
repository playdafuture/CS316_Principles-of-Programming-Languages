class ARmaxKey extends AR {
    NonEmptyBST target; // the target NonEmptyBST object for a call to maxKey()
    NonEmptyBST t;
    int returnVal;

    public String toString() {
        return "ARmaxKey "+"target = "+target+" t = "+t;
    }

    void traverse() {

    }

    void maxKey() {
        t = target;
        while ( t.right != EmptyBST.emptyBST ) {
            t = (NonEmptyBST)t.right;
        }                
        returnVal = t.val;
    }
}