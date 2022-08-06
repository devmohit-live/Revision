import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Me_while {
    public static void main(String args[]) throws Exception {
        // Write your code here
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        q = Integer.parseInt(br.readLine());

        buildTree();

        for (int i = 0; i < q; i++) {
            String inpsString = br.readLine();
            String[] inp = inpsString.split(" ");
            int type = Integer.parseInt(inp[0]);
            String s = inp[1];
            int id = Integer.parseInt(inp[2]);

            if (type == 1)
                System.out.println(lock(s, id));
            else if (type == 2)
                System.out.println(unlock(s, id));
            else if (type == 3)
                System.out.println(upgrade(s, id));
        }

    }

    private static class Node {
        int lockedBy;
        boolean isLocked;
        Node parent;
        List<Node> children;
        int desCount;
        volatile boolean isInUse;
        String name;

        Node(String name, Node par) {
            this.lockedBy = -1;
            this.isLocked = false;
            this.parent = par;
            this.desCount = 0;
            this.children = new ArrayList<>();
            this.name = name;
            this.isInUse = false;
        }

    }

    private static Node root;
    private static Map<String, Node> map = new HashMap<>();
    private static List<Node> nodes = new ArrayList<>();
    private static List<String> name = new ArrayList<>();
    private static boolean nodeslocked = false;
    static Scanner sc = new Scanner(System.in);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int n, m, q;

    private static void buildTree() throws IOException {
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            if (i == 0) {
                Node newnode = new Node(s, null);
                nodes.add(newnode);
                map.put(s, newnode);
                root = newnode;

            } else {
                int pidx = (i - 1) / m;
                Node par = nodes.get(pidx);
                Node newNode = new Node(s, par);
                nodes.add(newNode);
                map.put(s, newNode);
            }
        }

        // add children
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= m; j++) {
                int cidx = m * i + j;
                if (cidx < n) {
                    Node par = nodes.get(i);
                    par.children.add(nodes.get(cidx));
                }
            }
        }
    }

    private static boolean lock(String s, int uid) {
        Node curr = map.get(s);
        if (curr == null)
            return false;
        while (curr.isInUse) ;

            
        if (curr.isLocked)
            return false;

        if (curr.desCount > 0)
            return false;

        Node par = curr.parent;

        while (par != null) {
            while(par.isInUse);

            if (par.isLocked)
                return false;

            par = par.parent;
        }

        curr.lockedBy = uid;
        curr.isLocked = true;

        par = curr.parent;
        while (par != null) {
            while(par.isInUse);
            
            par.desCount++;
            par = par.parent;
        }

        return true;
    }

    private static boolean unlock(String s, int uid) {
        Node curr = map.get(s);
        if (curr == null)
            return false;
        if (!curr.isLocked)
            return false;
        if (curr.lockedBy != uid)
            return false;

        curr.isLocked = false;
        curr.lockedBy = -1;

        Node par = curr.parent;
        while (par != null) {
            par.desCount--;
            par = par.parent;
        }

        return true;
    }

    private static boolean upgrade(String s, int uid) {
        Node curr = map.get(s);
        if (curr == null)
            return false;
        if (curr.isLocked)
            return false;
        if (curr.desCount == 0)
            return false;

        if (checkDesc(curr, uid) && nodeslocked) {
            setDesc(curr);
            curr.isLocked = true;
            curr.lockedBy = uid;
            Node par = curr.parent;
            while (par != null) {
                par.desCount++;
                par = par.parent;
            }
            return true;
        }

        return false;
    }

    private static boolean checkDesc(Node node, int id) {
        if (node == null)
            return false;
        boolean ans = false;

        for (int i = 0; i < node.children.size(); i++) {
            Node child = node.children.get(i);
            if (child.isLocked) {
                if (child.lockedBy != id)
                    return false;
                nodeslocked = true;
            } else if (child.desCount > 0) {
                ans = ans || nodeslocked || checkDesc(child, id);
            }
        }
        return true;
    }

    private static void setDesc(Node node) {
        if (node == null)
            return;

        for (Node child : node.children) {
            if (child.isLocked) {
                child.isLocked = false;
                child.lockedBy = -1;
                Node par = child.parent;
                while (par != null) {
                    par.desCount--;
                    par = par.parent;
                }
            } else if (child.desCount > 0) {
                setDesc(child);
            }
        }
    }

}
