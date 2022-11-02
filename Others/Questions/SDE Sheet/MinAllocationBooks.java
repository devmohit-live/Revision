public class MinAllocationBooks {
    // Min allocation of pages
    public int minAllocationBook(int[] books, int students) {
        if (students > books.length)
            return -1; // each studetns must be alloted 1 books
        int sum = 0, max = -1;
        for (int el : books) {
            sum += el;
            max = Math.max(max, el);
        }
        int si = max, ei = sum; // why max bcz that no of pages have to be alloted to some candiates
        // we can't divide the pages
        int ans = -1;
        while (si <= ei) {
            int mid = si + (ei - si) / 2;
            if (isPossible(books, students, mid)) {
                ans = mid;
                // try to resuce burdern on each student
                ei = mid - 1;
            } else {
                // more student we required to finish books
                // increase the pages per head;
                si = mid + 1;
            }
        }

        return ans;

    }

    boolean isPossible(int[] arr, int students, int limit) {
        int pages = 0, count = 1;// start with first student
        for (int el : arr) {
            if (el > limit)
                return false;

            pages += el;

            if (pages > limit) {
                pages = el;
                count++;
            }

        }

        return (count <= students);
    }

}
