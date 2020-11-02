package LeetCodePremium.Amazon.Design;

import java.util.Stack;

/*
Design a stack that supports push, pop, top, and retrieving
the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.

Constraints:
Methods pop, top and getMin operations will always be called on non-empty stacks.
 */
public class MinStack {
    // Cannot use a map as we cannot store duplicate keys in a map. 
    private Stack<int[]> stack = new Stack<>();

    public MinStack() { }

    public void push(int x) {

        /* If the stack is empty, then the min value
         * must just be the first value we add. */
        if (stack.isEmpty()) {
            stack.push(new int[]{x, x});
            return;
        }

        int currentMin = stack.peek()[1];
        stack.push(new int[]{ x, Math.min(x, currentMin) } );
    }


    public void pop() {
        stack.pop();
    }


    public int top() {
        return stack.peek()[0];
    }


    public int getMin() {
        return stack.peek()[1];
    }
}
