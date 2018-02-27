class BSTArray extends Obj {
    public BST[] bstArray;

    BSTArray(BST[] bst_array) {
        bstArray = bst_array;
    }

    public String toString() {
        return mark+":"+objId+":BSTArray of length "+bstArray.length;
    }

    void traverse(boolean newMark) {
        this.mark = newMark;
        Output.println(this.toString());
        for (int i = 0; i < bstArray.length; i++) {
            if (bstArray[i] != null) {
                bstArray[i].traverse(newMark);
            }
        }
    }
}
