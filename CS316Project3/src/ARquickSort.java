public class ARquickSort extends AR{
    int p;
    int r;
    int[] A;
    public String toString() {
        return "ARquickSort p = " + p +" r = "+ r + 
                " A =  "+ printArray(A);
    }
    
    void quickSort() {
        if (p < r) {
            // int q = partition(A, p, r);
            ARpartition newPartition = new ARpartition();
            newPartition.A = this.A;
            newPartition.p = this.p;
            newPartition.r = this.r;
            RuntimeStack.push(newPartition);
            newPartition.partition();
            int q = newPartition.returnVal;
            RuntimeStack.pop();
            
            // quickSort(A, p, q-1);
            ARquickSort newARquickSort1 = new ARquickSort();
            newARquickSort1.p = p;
            newARquickSort1.r = q - 1;
            newARquickSort1.A = this.A;
            RuntimeStack.push(newARquickSort1);
            newARquickSort1.quickSort();
            RuntimeStack.pop();
            
            // quickSort(A, q+1, r);
            ARquickSort newARquickSort2 = new ARquickSort();
            newARquickSort2.p = q + 1;
            newARquickSort2.r = r;
            newARquickSort2.A = this.A;
            RuntimeStack.push(newARquickSort2);
            newARquickSort2.quickSort();
            RuntimeStack.pop();
        }
    }
    
    public static void main(String[] argv) {
        if (argv.length > 0) {
            Output.setOutput( argv[0] );
        }
        ARquickSort newARquickSort = new ARquickSort();
        newARquickSort.p = 0;
        newARquickSort.r = 9;
        int[] temp = {8, 3, 5, 1, 9, 7, 4, 6, 2, 10};
        newARquickSort.A = temp;
        RuntimeStack.push(newARquickSort);
        newARquickSort.quickSort();
        RuntimeStack.pop();
        
        System.out.println(printArrayS(temp));
        if (argv.length > 0) {
            Output.closeOutput();
        }
    }
    
    public static String printArrayS(int[] A) {
        String s = "[";
        for (int i = 0; i < A.length; i++) {
            s += A[i];
            s += ", ";
        }
        if (A.length > 0) {
            s = s.substring(0, s.length() - 2); //trim off the last ", "
        }
        s += "]";
        return s;
    }
    
    public String printArray(int[] A) {
        String s = "[";
        for (int i = 0; i < A.length; i++) {
            s += A[i];
            s += ", ";
        }
        if (A.length > 0) {
            s = s.substring(0, s.length() - 2); //trim off the last ", "
        }
        s += "]";
        return s;
    }
}
