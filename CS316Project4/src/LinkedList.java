public class LinkedList {    
    Obj[] objList;
    int cap;
    public LinkedList() {
        objList = new Obj[1000];
        cap = 0;
    }
    
    public void add(Obj newOb) {        
        objList[cap++] = newOb;
    }
    
    public void delete(Obj unwanted) {
        for (int i = 0; i < cap; i++) {
            if (objList[i] == unwanted) {
                objList[i] = null;
            }
        }
    }
    
    public Obj get(int idx) {
        return objList[idx];
    }
}
