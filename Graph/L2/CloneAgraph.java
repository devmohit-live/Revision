public class CloneAgraph {
    public HashMap<Node, Node> map;

    public Node cloneGraph(Node node) {
        map = new HashMap<>();
        // return cloneDFS(node);
        return cloneBFS(node);
    }

    // dfs
    private Node cloneDFS(Node node) {
        if (node == null)
            return null;

        if (map.containsKey(node))
            return map.get(node);

        Node newNode = new Node(node.val, new ArrayList<Node>());
        map.put(node, newNode);
        for (Node neighbor : node.neighbors)
            newNode.neighbors.add(cloneDFS(neighbor));
        return newNode;
    }

    // bfs
    private Node cloneBFS(Node node) {
        if (node == null)
            return null;

        LinkedList<Node> q = new LinkedList<>();
        map.put(node, new Node(node.val, new ArrayList<>())); // mark visited
        q.addLast(node);

        // bfs without cycle
        while (q.size() > 0) {
            Node rm = q.removeFirst();
            for (Node nbr : rm.neighbors) {
                if (!map.containsKey(nbr)) {
                    map.put(nbr, new Node(nbr.val, new ArrayList<>()));
                    q.addLast(nbr); // only add not visited neighbours: visited
                }
                map.get(rm).neighbors.add(map.get(nbr));

            }
        }
        return map.get(node);

    }
}
