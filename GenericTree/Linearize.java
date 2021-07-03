public class Linearize {
    static Node getTail(Node node) {
        while (node.children.size() != 0) {
            //getiing to the last child
            node = node.children.get(0);
        }
        return node;
    }

    public static void linearize(Node node) {

        // faith
        for (Node child : node.children) {
            linearize(child);
        }

        // self
        for (int i = node.children.size() - 1; i > 0; i--) {
            // last scond 's tail'
            Node tail = getTail(node.children.get(i - 1));
            tail.children.add(node.children.get(i));

            // removing the last one from tree

            node.children.remove(i);
        }

    }

}
