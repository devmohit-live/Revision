import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class A_new {
    static Map<String, Node> map = new HashMap<>();
    static int n, m, q;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static class Node {
        String name;
        boolean isLocked;
        boolean isLockable;
        int id;
        Node par;
        List<Node> children;

        Node(String name) {
            this(name, null);
        }

        Node(String name, Node parent) {
            this.name = name;
            this.isLocked = false;
            this.isLockable = true;
            this.id = -1;
            this.par = parent;
            this.children = new ArrayList<>();
        }
    }

    private static boolean lock(String s, int uid) {
        Node node = map.get(s);
        if (node == null)
            return false;

        if (!node.isLockable || node.isLocked)
            return false;
        Node curr = node;

        Queue<Node> q = new ArrayDeque<>();
        q.add(curr);
        while (!q.isEmpty()) {
            Node rm = q.remove();
            for (Node child : rm.children) {
                if (child.isLocked)
                    return false;
                q.add(child);
            }
        }

        //lock the node
        node.id = uid;
        node.isLocked = true;
        q.add(node);


        // not set the islockable status of desc to false;
        while (!q.isEmpty()) {
            Node rm = q.remove();
            for (Node child : rm.children) {
                child.isLockable = false;
                q.add(child);
            }
        }
        return true;
    }

    private static boolean unlock(String s, int uid) {
        Node node = map.get(s);
        if (node == null)
            return false;

        if (node.isLockable == false || node.isLocked == false || (node.isLocked && uid != node.id))
            return false;

        Node curr = node;
        node.isLocked = false;
        node.id = -1;
        Queue<Node> q = new ArrayDeque<>();
        q.add(curr);
        while (!q.isEmpty()) {
            Node rm = q.remove();
            for (Node child : rm.children) {
                child.isLockable = true;
                q.add(child);
            }
        }
        return true;
    }

    private static boolean upgrade(String s, int uid) {
        Node node = map.get(s);
        if (node == null)
            return false;

        if (node.isLockable == false || node.isLocked == true)
            return false;
        Node curr = node;
        Queue<Node> q = new ArrayDeque<>();
        q.add(curr);
        boolean flag = false;
        while (!q.isEmpty()) {
            Node rm = q.remove();
            for (Node child : rm.children) {
                if (child.isLocked == true && child.id != uid) {
                    return false;
                }
                if (child.isLocked)
                    flag = true;
                q.add(child);
            }
        }

        if (!flag)
            return false;

        // unlocking desc

        q.add(curr);
        while (!q.isEmpty()) {
            Node rm = q.remove();
            for (Node child : rm.children) {
                if (child.isLocked) {
                    // child.isLocked = false;
                    if (!unlock(child.name, uid))
                        return false;
                }
                q.add(child);
            }
        }
        // locking(n,uid);
        return lock(node.name, uid);
    }

    public static void main(String[] args) throws Exception {
        String[] intInp = br.readLine().split(" ");
        n = Integer.parseInt(intInp[0]);
        m = Integer.parseInt(intInp[1]);
        q = Integer.parseInt(intInp[2]);

        buildTree();

        for (int i = 0; i < q; i++) {
            String inpsString = br.readLine();
            String[] inp = inpsString.split(" ");
            int type = Integer.parseInt(inp[0]);
            int uid = Integer.parseInt(inp[2]);
            String name = inp[1];

            if (type == 1) {
                System.out.println(lock(name, uid));
            } else if (type == 2) {
                System.out.println(unlock(name, uid));
            } else if (type == 3) {
                System.out.println(upgrade(name, uid));
            }
        }
    }

    private static void buildTree() throws IOException {
        String s = br.readLine();
        Node root = new Node(s);
        map.put(s, root);
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        int k = 1;
        while (!queue.isEmpty()) {
            Node rm = queue.remove();
            while (k < n && rm.children.size() < m) {
                String name = br.readLine();
                Node child = new Node(name, rm);
                map.put(name, child);
                rm.children.add(child);
                queue.add(child);
                k++;
            }
        }

        // print(root);
    }

}
