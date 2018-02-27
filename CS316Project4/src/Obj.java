abstract class Obj {
    int objId; // the unique object identifier
    boolean mark;

    public abstract String toString();

    abstract void traverse(boolean newMark); // Perform traversal of the object-graph in the mark phase.
}