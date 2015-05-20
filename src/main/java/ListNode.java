/**
 * Created by Lenovo on 15-4-26.
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        String s = "";
        ListNode node = this;
        while (node != null) {
            s += "ListNode{" +
                    "val=" + node.val +
                    '}';
            node = node.next;
        }
        return s;
    }
}
