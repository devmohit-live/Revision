import java.util.*;

public class PQ_Basics {
    static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        classSort();
        printArray();
    }

    // this - other, default behaviour.
    // other - this, reverse of default behaviour.

    // sort on bases of second column data

    static void printArray() {
        int[][] arr = new int[][] { { 2, 6, 11, 13 }, { 8, 5, 16, 4 }, { 9, 7, 11, 13 }, { 8, 3, 12, 11 } };

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        for (int[] i : arr) {
            pq.add(i);
        }

        while (pq.size() > 0) {
            System.out.println(Arrays.toString(pq.remove()));
        }

    }

    // sort on basis of class (ram) -> storage -> battery

    static class mobile {
        String comp;
        String modal;
        int ram;
        int storage;
        int battery;

        mobile(String comp, String modal, int ram, int storage, int battery) {
            this.comp = comp;
            this.modal = modal;
            this.ram = ram;
            this.battery = battery;
            this.storage = storage;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Comapy: " + this.comp + "\n");
            sb.append("Modal : " + this.modal + "\n");
            sb.append("Ram: " + this.ram + "GB\n");
            sb.append("Battery : " + this.battery + "Mah\n");
            sb.append("Storage : " + this.storage + "GB\n");
            return sb.toString();
        }
    }

    static void classSort() {
        PriorityQueue<mobile> pq = new PriorityQueue<>((a, b) -> {
            if (a.ram == b.ram) {
                if (a.storage == b.storage) {
                    return b.battery - a.battery;
                } else
                    return b.storage - a.storage;
            } else
                return b.ram - a.ram; // decreasing order of ram
        });

        mobile p = new mobile("A", "A", 32, 128, 5000);
        mobile q = new mobile("B", "B", 32, 128, 4500);
        mobile r = new mobile("C", "C", 32, 256, 5000);
        mobile s = new mobile("E", "E", 16, 128, 5500);
        mobile t = new mobile("F", "F", 16, 512, 5000);
        mobile u = new mobile("G", "G", 8, 512, 5500);
        mobile v = new mobile("H", "H", 8, 512, 5500);
        mobile w = new mobile("I", "I", 8, 512, 5500);

        // for (int i = 0; i < 10; i++) {
        // mobile mb = new mobile(sc.next(), sc.next(), sc.nextInt(), sc.nextInt(),
        // sc.nextInt());
        // sc.nextLine();
        // }

        System.out.println();

        while (pq.size() > 0) {
            System.out.println(pq.remove());
        }

    }

}
