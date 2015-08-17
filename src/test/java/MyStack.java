import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by wangshun on 15-8-13.
 */
class MyStack {
    Queue<Integer> q1 = new ArrayDeque<Integer>(),q2=new ArrayDeque<Integer>();
    // Push element x onto stack.
    public void push(int x) {
        q2.add(x);
        while (!q1.isEmpty()){
           int y = q1.remove();
            q2.add(y);
        }
        Queue<Integer> tmp = q1;
        q1 = q2;
        q2=tmp;
    }

    // Removes the element on top of the stack.
    public void pop() {
         if(!q1.isEmpty())
         q1.remove();
    }

    // Get the top element.
    public int top() {
       return q1.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
       return q1.isEmpty()&&q2.isEmpty();
    }
}
