public class Island {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[m][n];

        for (int i = 0; i < arr.length; i++) {
            String parts = br.readLine();
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = Integer.parseInt(parts.split(" ")[j]);
            }
        }

        // no of island
        noIsland(arr, m, n);

        maxArear(arr, m, n);
    }

    public static noIsland(int[][]arr,int m,int n){
            boolean[][] visited = new boolean[m][n];
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && arr[i][j] == 0) {
                    count++;
                    dfs(arr, i, j, visited, m, n);
                }
            }
        }

        System.out.println(count);
       }

    public static maxArea(int[][]arr,int m,int n){
            boolean[][] visited = new boolean[m][n];
             int area = Integer.MIN_VALUE;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && arr[i][j] == 0) {
                    int curr = dfs2(arr, i, j, visited, m, n);
                    area = Math.max(area,curr);
                }
            }
        }

        System.out.println(area);
       }

    public static void dfs(int[][] graph, int i, int j, boolean visited[][], int m, int n) {
        if (i < 0 || i >= m || j >= n || j < 0 || visited[i][j] || graph[i][j] == 1)
            return;

        visited[i][j] = true;

        dfs(graph, i + 1, j, visited, m, n);
        dfs(graph, i - 1, j, visited, m, n);
        dfs(graph, i, j - 1, visited, m, n);
        dfs(graph, i, j + 1, visited, m, n);
    }

    public static int dfs2(int[][] graph, int i, int j, boolean visited[][], int m, int n) {
        if (i < 0 || i >= m || j >= n || j < 0 || visited[i][j] || graph[i][j] == 1)
            return Integer.MIN_VALUE;
        int area = 1;
        visited[i][j] = true;

        area += dfs2(graph, i + 1, j, visited, m, n);
        area += dfs2(graph, i - 1, j, visited, m, n);
        area += dfs2(graph, i, j - 1, visited, m, n);
        area += dfs2(graph, i, j + 1, visited, m, n);

        return area;
    }
}
