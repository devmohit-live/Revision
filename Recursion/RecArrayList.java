import java.util.*;

/*
RETURN TYPE FAITH: anser will be developed while coming back:

children will(next level will bring its' path and i will add my own step(h/v/d) along with the jump(number) to the path to make it a complete path)
I will do this for every path obtained from child(all elemnets from Arrylist of recursion)


*/
public class RecArrayList {
    static int dir[][] = { { 0, 1 }, { 1, 0 } };
    static String[] dirs = { "h", "v" };
    final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        // ArrayList<String> res =new ArrayList<>();
        // int sols = getMazePaths(0,0,n-1,m-1,"",res);
        ArrayList<String> res = getMazePaths(0, 0, n - 1, m - 1);
        System.out.println(res);

    }

    // return type => top to bottom => anser aate qwt bottom me bnega
    public static ArrayList<String> getMazePaths(int sr, int sc, int dr, int dc) {
        if (sr == dr && sc == dc) {
            ArrayList<String> base = new ArrayList<>();
            // empty string se start hoga bnna answer or neeche aate aate bnta jaega
            base.add("");
            return base;
        }
        ArrayList<String> myans = new ArrayList<>();
        for (int i = 0; i < dir.length; i++) {
            int r = sr + dir[i][0];
            int c = sc + dir[i][1];

            if (r >= 0 && c >= 0 && r <= dr && c <= dc) {
                ArrayList<String> small = getMazePaths(r, c, dr, dc);
                for (String s : small)
                    myans.add(dirs[i] + s);
            }

        }
        return myans;
    }

    // void type bottom up => upr jaate wqt bnega
    public static int getMazePaths(int sr, int sc, int dr, int dc, String psf, ArrayList<String> res) {
        if (sr == dr && sc == dc) {
            res.add(psf);
            return 1;
        }

        int count = 0;

        for (int i = 0; i < dir.length; i++) {
            int r = sr + dir[i][0];
            int c = sc + dir[i][1];

            if (r >= 0 && c >= 0 && r <= dr && c <= dc)
                count += getMazePaths(r, c, dr, dc, psf + dirs[i], res);

        }

        return count;

    }

    // jumps allowed -> return type:
    public static ArrayList<String> getMazePaths(int sr, int sc, int dr, int dc) {
        if (sr == dr && sc == dc) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> res = new ArrayList<>();

        for (int i = 0; i < dir.length; i++) {
            for (int jump = 1; jump <= Math.max(dr, dc); jump++) {
                int r = sr + jump * dir[i][0];
                int c = sc + jump * dir[i][1];
                if (r >= 0 && c >= 0 && r <= dr && c <= dc) {
                    ArrayList<String> small = getMazePaths(r, c, dr, dc);
                    for (String s : small) {
                        res.add(dirs[i] + jump + s);
                    }
                }
            }
        }

        return res;

    }

    // void Type
    public static int getMazePaths(int sr, int sc, int dr, int dc, String psf, ArrayList<String> res) {
        if (sr == dr && sc == dc) {
            res.add(psf);
            return 1;
        }

        int count = 0;

        for (int i = 0; i < dir.length; i++) {
            for (int jump = 1; jump <= Math.max(dr, dc); jump++) {
                int r = sr + jump * dir[i][0];
                int c = sc + jump * dir[i][1];
                if (r >= 0 && c >= 0 && r <= dr && c <= dc)
                    count += getMazePaths(r, c, dr, dc, psf + dirs[i] + jump, res);
            }
        }

        return count;

    }

    // return type
    public static ArrayList<String> getStairPaths(int n) {
        if (n == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> ans = new ArrayList<>();

        for (int jump = 1; jump <= 3; jump++) {
            if (n - jump >= 0) {
                ArrayList<String> small = getStairPaths(n - jump);
                for (String s : small)
                    ans.add(jump + s);
            }
        }

        return ans;
    }

    // void type

    public static int getStairPaths(int n, String psf, ArrayList<String> res) {
        if (n == 0) {
            res.add(psf);
        }

        int count = 0;

        for (int jump = 1; jump <= 3; jump++) {
            if (n - jump >= 0) {
                count += getStairPaths(n - jump, psf + jump, res);
            }
        }

        return count;
    }

    // subsequnce -> return type
    public static ArrayList<String> gss(String str) {
        if (str.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        char ch = str.charAt(0);

        ArrayList<String> res = new ArrayList<>();

        ArrayList<String> small = gss(str.substring(1));
        for (String s : small) {
            res.add(s);
        }
        for (String s : small) {
            res.add(ch + s);
        }

        return res;

    }

    // void type:
    public static int gss(String str, int idx, String psf, ArrayList<String> res) {
        if (str.length() == idx) {
            res.add(psf);
            return 1;
        }

        int count = 0;
        char ch = str.charAt(idx);
        count += gss(str, idx + 1, psf, res);
        count += gss(str, idx + 1, psf + ch, res);

        return count;

    }

}
