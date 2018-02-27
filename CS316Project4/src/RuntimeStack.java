abstract class RuntimeStack {
    static final AR runtimeStack[] = new AR[10000];
    static int top = -1;

    static int totalNumFunCalls = 0;
    static int maxStackSize = 0;

    static void pop() {
        top--;
    }

    static void push(AR ar)    {
        top++;
        runtimeStack[top] = ar;

        totalNumFunCalls++;
        if ( top == maxStackSize ) {
            maxStackSize++;
        }                
    }

    static void display()    {
        Output.println("\nThe total number of function calls so far = "+totalNumFunCalls);
        Output.println("The maximum stack size so far = "+maxStackSize);
        Output.println("\nThe contents of the runtime stack will be displayed from top to bottom:\n");

        for (int i = top; i >= 0; i--)        {
            Output.println(runtimeStack[i].toString());
        }
    }
}