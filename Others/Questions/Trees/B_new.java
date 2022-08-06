import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class B_new {
    static Map<String, Node> map = new HashMap<>();
    static int n, m, q;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static class Node {
        volatile boolean isInUse;
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
            this.isInUse = false;
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

        // race
        if (node.isInUse)
            while (node.isInUse)
                ; // can't simply return as return false may be wrong;

        node.isInUse = true;

        if (!node.isLockable || node.isLocked) {
            node.isInUse = false;
            return false;
        }

        Node curr = node;

        Queue<Node> q = new ArrayDeque<>();
        List<Node> currentlyInUse = new ArrayList<>();
        q.add(curr);
        while (!q.isEmpty()) {
            Node rm = q.remove();
            for (Node child : rm.children) {
                while (child.isInUse)
                    ;
                child.isInUse = true;
                currentlyInUse.add(child);
                if (child.isLocked) {
                    // before returning unlock all the inuse nodes by this function
                    for (int i = 0; i < currentlyInUse.size(); i++)
                        currentlyInUse.get(i).isInUse = false;

                    node.isInUse = false;
                    return false;
                }

                q.add(child);
            }
        }

        node.id = uid;
        node.isLocked = true;
        q.add(node);

        // not set the islockable status of desc to false;
        while (!q.isEmpty()) {
            Node rm = q.remove();
            for (Node child : rm.children) {
                while (child.isInUse)
                    ;
                child.isInUse = true;
                child.isLockable = false;
                q.add(child);

                // no need to keep track of in use as we are not return ing immediatly in
                // between
                child.isInUse = false;
            }
        }

        node.isInUse = false;
        return true;
    }

    private static boolean unlock(String s, int uid) {
        Node node = map.get(s);
        if (node == null)
            return false;

        while (node.isInUse)
            ;

        if (node.isLockable == false || node.isLocked == false || (node.isLocked && uid != node.id)) {
            node.isInUse = false;
            return false;
        }

        Node curr = node;
        node.isLocked = false;
        node.id = -1;
        Queue<Node> q = new ArrayDeque<>();
        q.add(curr);
        while (!q.isEmpty()) {
            Node rm = q.remove();
            for (Node child : rm.children) {
                while (child.isInUse)
                    ;
                child.isInUse = true;

                child.isLockable = true;
                q.add(child);
                child.isInUse = false;
            }
        }

        node.isInUse = false;
        return true;
    }

    // Upgrade is havinf some issues
    private static boolean upgrade(String s, int uid) {
        Node node = map.get(s);
        if (node == null)
            return false;
        while (node.isInUse)
            ;
        node.isInUse = true;

        if (node.isLockable == false || node.isLocked == true) {
            node.isInUse = false;
            return false;
        }

        Node curr = node;
        Queue<Node> q = new ArrayDeque<>();
        List<Node> currentlyInUse = new ArrayList<>();
        q.add(curr);
        boolean flag = false; // atleadt one child is locked

        while (!q.isEmpty()) {
            Node rm = q.remove();
            for (Node child : rm.children) { 
                while (child.isInUse)
                    ;

                child.isInUse = true;
                currentlyInUse.add(child);
                if (child.isLocked == true && child.id != uid) {
                    for (int i = 0; i < currentlyInUse.size(); i++)
                        currentlyInUse.get(i).isInUse = false;
                    node.isInUse = false;
                    return false;
                }
                
                // locked by same node
                if (child.isLocked)
                    flag = true;
                child.isInUse = false;
                q.add(child);
            }
        }

        // unlock all inuse
        for (int i = 0; i < currentlyInUse.size(); i++)
            currentlyInUse.get(i).isInUse = false;

        if (!flag) {
            node.isInUse = false;
            return false;
        }

        // unlocking desc
        currentlyInUse = new ArrayList<>();

        q.add(curr);
        while (!q.isEmpty()) {
            Node rm = q.remove();
            for (Node child : rm.children) {
                while (child.isInUse)
                    ;
                child.isInUse = true;
                currentlyInUse.add(child);

                if (child.isLocked) {
                    unblockResources(child);
                    while (child.isInUse)
                        ;

                    // not setting isinUse of child or making currentisUse
                    // as it will all be done by lock function

                    unlock(child.name, uid);
                }
                child.isInUse = false;
                q.add(child);
            }
        }
        while (node.isInUse)
            ;
        node.isInUse = false;
        return lock(node.name, uid);
    }

    private static void unblockResources(Node n) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(n);
        while (!q.isEmpty()) {
            Node temp = q.remove();
            for (Node x : temp.children) {
                x.isInUse = false;
                q.add(x);
            }
        }
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

    }

}
