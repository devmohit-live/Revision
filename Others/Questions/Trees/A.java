import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TreeOfSpace {
    static Boolean nodesLocked = null;

    static class Node {
        String name;
        int desCount;
        boolean isLocked;
        // int isLocked; //for multihreading check (int > 1) (made 1 and return back)
        int lockedBy;
        List<Node> children;
        Node parent;

        Node(String s) {
            this(s, null);
        }

        Node(String name, Node par) {
            this.name = name;
            this.parent = par;
            this.desCount = 0;
            this.isLocked = false;
            this.parent = par;
            this.lockedBy = -1;
            this.children = new ArrayList<Node>();
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    final static int N = 7, M = 2, Q = 5, UNLOCKED = -1;
    final static Scanner sc = new Scanner(System.in);
    static Node root;
    static List<String> names = new ArrayList<>();
    static List<Node> nodes = new ArrayList<>();
    static Map<String, Node> map = new HashMap<>();

    private static void buildTree() {
        for (int i = 0; i < N; i++) {
            String s = sc.nextLine();
            if (i == 0) {
                Node newNode = new Node(s, null);
                nodes.add(newNode);
                map.put(s, newNode);
                root = newNode;
            } else {
                int parIdx = (i - 1) / M;
                Node parent = nodes.get(parIdx);
                Node newNode = new Node(s, parent);
                nodes.add(newNode);
                map.put(s, newNode);
            }
        }

        // add children to appropriate node:
        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= M; j++) {
                int childIdx = M * i + j; // children index
                if (childIdx < N) { // if valid index
                    Node par = nodes.get(i);
                    par.children.add(nodes.get(childIdx));
                }
            }
        }

        System.out.println(map);
        System.out.println("Nodes " + nodes);

    }

    // can't lock any desc or anscestor of already locked node,
    private static boolean lock(String s, int uid) {
        Node curr = map.get(s);
        if (curr.isLocked)
            return false; // already locked
        // check for desendants
        if (curr.desCount > 0)
            return false;
        // check for ansectors
        Node par = curr.parent;
        while (par != null) {
            if (par.isLocked)
                return false;

            par = par.parent;
        }
        // every coondition is followed: lock this node and increase it;s parent desc
        // count
        curr.lockedBy = uid;
        curr.isLocked = true;

        par = curr.parent;
        while (par != null) {
            par.desCount++;
            par = par.parent;
        }

        return true;
    }

    private static boolean unLock(String s, int uid) {
        Node curr = map.get(s);

        if (!curr.isLocked)
            return false;
        if (curr.lockedBy != uid)
            return false; // unlocking by wrong person
        // now we can unlock

        curr.isLocked = false;
        curr.lockedBy = -1;

        // tell it's ancestors that a child has been unlocked
        Node par = curr.parent;
        while (par != null) {
            par.desCount--;
            par = par.parent;
        }

        return true;

    }

    // basically tells that lock s node if atleast one of it's children is locked by
    // same user(upgrade lock from child(already lockec) -> to parent(s))
    // note: upgrade should work as lock by default (already locked s) shoudl return
    // false;
    // note : s can have more than 1 child locked(siblings of locked or descendant
    // of locked siblings) but they all should be locked by same user

    private static boolean upgrade(String s, int uid) {
        Node curr = map.get(s);
        if (curr.isLocked)
            return false;
        if (curr.desCount == 0)
            return false; // no locked descendant (can't ugrade lock to curr node)

        // check if desandants are locked by same user or not
        // if (checkDesendants(curr, uid) == 0)
        // return false;

        if (checkDesc(curr, uid) && nodesLocked) {
            // now unlock all desendants and apply lock to curr node
            // // setDescandantsFree(curr);
            // curr.isLocked = true;
            // curr.lockedBy = uid;
            // Node par = curr.parent;
            // while (par != null) {
            // par.desCount++;
            // par = par.parent;
            // }
            setDescandantsFree2(curr, uid);
            return lock(curr.name, uid);
        }

        return false;
    }

    // private static int checkDesendants(Node node, int id) {
    // if (node == null)
    // return 0;

    // int count = 0; // to get the details of no of locked nodes
    // // check for immmediate children
    // for (int i = 0; i < node.children.size(); i++) {
    // Node child = node.children.get(i);
    // if (child.isLocked) {
    // if (child.lockedBy != id)
    // return 0;
    // else
    // count++;
    // } else if (child.desCount > 0) {
    // // if any of the desendants are locked check for them too
    // int recAns = checkDesendants(child, id);
    // count += recAns;
    // }
    // }
    // return count;
    // }

    private static boolean checkDesc(Node node, int id) {
        if (node == null)
            return false;
        boolean ans = false;

        for (int i = 0; i < node.children.size(); i++) {
            Node child = node.children.get(i);
            if (child.isLocked) {
                if (child.lockedBy != id)
                    return false;
                nodesLocked = true;
            } else if (child.desCount > 0) {
                ans = ans || nodesLocked || checkDesc(child, id);
            }
        }
        return true;
    }

    private static void setDescandantsFree(Node node) {
        if (node == null)
            return;

        // unlock for it's immediate child
        for (Node child : node.children) {
            if (child.isLocked == true) {

                // I should have called unlock(child.name, uid);
                child.isLocked = false; // unlock
                child.lockedBy = -1; // remove user
                Node par = child.parent;
                while (par != null) {
                    par.desCount--;
                    par = par.parent;
                }
            } else if (child.desCount > 0) { // if some descendats(subtree is further locked)
                setDescandantsFree(child);
            }
        }
    }

    private static void setDescandantsFree2(Node node, int uid) {
        if (node == null)
            return;

        // unlock for it's immediate child
        for (Node child : node.children) {
            if (child.isLocked == true) {
                unLock(child.name, uid);
            } else if (child.desCount > 0) { // if some descendats(subtree is further locked)
                setDescandantsFree(child);
            }
        }
    }

    private static void display(Node node) {
        if (node == null)
            return;

        // preorder
        System.out.println(node);
        for (Node child : node.children) {
            display(child);
        }
    }

    public static void main(String[] args) {
        // N : no of nodes in total , M => max size of childrens(M ary), Q : no of api
        // operations

        buildTree();

        // 1: lock , 2: unlock , 3 : upgrade

        for (int i = 0; i < Q; i++) {
            String[] inp = sc.nextLine().split(" ");
            int type = Integer.parseInt(inp[0]);
            String s = inp[1];
            int uid = Integer.parseInt(inp[2]);

            if (type == 1)
                System.out.println(lock(s, uid));

            else if (type == 2)
                System.out.println(unLock(s, uid));

            else if (type == 3)
                System.out.println(upgrade(s, uid));

        }

    }
}
