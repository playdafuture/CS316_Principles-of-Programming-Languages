public class ARpartition extends ARquickSort{
    int x;
    int i;
    int j;
    int returnVal;
    
    public String toString() {
        return "ARpartition A = "+printArray(A)
                +" p = "+p
                +" r = "+r
                +" x = "+x
                +" i = "+i
                +" j = "+j;
    }
    
    void partition() {
        x = A[r];
        i = p - 1;
        int temp;
        for (j = p; j <= r-1; j++) {
            if (A[j] <= x) {
                i++;
                temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        temp = A[i+1];
	A[i+1] = A[r];
        A[r] = temp;
        returnVal = i+1;
        RuntimeStack.display();
    }
}
