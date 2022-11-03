public class FlattenLinkedList {
    Node flatten(Node root) {
        if (root == null || root.next == null)
            return root;

        root.next = flatten(root.next);

        Node merged = mergeTwoSortedList(root, root.next);

        return merged;

    }

    private Node mergeTwoSortedList(Node list1, Node list2) {
        Node sorted = new Node(-1), curr = sorted, a = list1, b = list2;

        if (a == null || b == null) {
            return a == null ? b : a;
        }

        while (a != null && b != null) {
            if (a.data <= b.data) {
                curr.bottom = a;
                a = a.bottom;
            } else {
                curr.bottom = b;
                b = b.bottom;
            }

            curr = curr.bottom;
        }

        // while(a!=null){
        // curr.bottom = a;
        // a=a.bottom;
        // curr=curr.bottom;
        // }

        // while(b!=null){
        // curr.bottom = b;
        // b=b.bottom;
        // curr=curr.bottom;
        // }

        if (a != null) {
            curr.bottom = a;
        } else if (b != null)
            curr.bottom = b;

        return sorted.bottom;
    }
}
