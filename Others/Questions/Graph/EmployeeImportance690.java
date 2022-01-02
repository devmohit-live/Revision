/**
 * EmployeeImportance690
 */
public class EmployeeImportance690 {
    HashMap<Integer, Employee> map;

    public int getImportance(List<Employee> emp, int id) {
        if (emp == null || emp.size() == 0)
            return 0;

        // no need of visited : distinct subordinated list and w ehave to traverse in
        // that list only(n-ary tree)
        map = new HashMap<>();
        Employee root = null;
        for (Employee e : emp) {
            if (e.id == id) {
                root = e;
            }
            map.put(e.id, e);
        }

        // return bfs(id);
        return dfs(id);
    }

    private int bfs(int id) {
        int ans = 0;
        LinkedList<Employee> q = new LinkedList<>();
        q.addLast(map.get(id));

        while (q.size() > 0) {
            Employee rm = q.removeFirst();
            ans += rm.importance;
            for (Integer subId : rm.subordinates) {
                q.addLast(map.get(subId));
            }

        }
        return ans;
    }

    private int dfs(int srcId) {
        Employee src = map.get(srcId);
        int imp = src.importance;
        for (int subId : src.subordinates) {
            imp += dfs(subId);
        }

        return imp;
    }

}