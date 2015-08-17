import java.util.Stack;

/**
 * Created by wangshun on 15-8-13.
 */
class MyQueue {
    Stack<Integer> stk1=new Stack<Integer>(),stk2=new Stack<Integer>();
    // Push element x to the back of queue.
    public void push(int x) {
       stk2.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
      if(!stk1.isEmpty()){
          stk1.pop();
      }else {
          while (!stk2.isEmpty()){
              int y =stk2.pop();
              stk1.push(y);
          }
          stk1.pop();
      }

    }

    // Get the front element.
    public int peek() {
        if(!stk1.isEmpty()){
           return stk1.peek();
        }else {
            while (!stk2.isEmpty()){
                int y =stk2.pop();
                stk1.push(y);
            }
            return stk1.peek();
        }
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return stk1.isEmpty()&&stk2.isEmpty();
    }
}