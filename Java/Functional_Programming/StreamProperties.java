import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class StreamProperties {

    static class Student implements Comparable<Student> {
        int id;
        String name;
        long phone;
        String branch;
        int year;
        String couse;

        /**
         * @return the id
         */
        public int getId() {
            return id;
        }

        /**
         * @param id the id to set
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the phone
         */
        public long getPhone() {
            return phone;
        }

        /**
         * @param phone the phone to set
         */
        public void setPhone(long phone) {
            this.phone = phone;
        }

        /**
         * @return the branch
         */
        public String getBranch() {
            return branch;
        }

        /**
         * @param branch the branch to set
         */
        public void setBranch(String branch) {
            this.branch = branch;
        }

        /**
         * @return the year
         */
        public int getYear() {
            return year;
        }

        /**
         * @param year the year to set
         */
        public void setYear(int year) {
            this.year = year;
        }

        /**
         * @return the couse
         */
        public String getCouse() {
            return couse;
        }

        /**
         * @param couse the couse to set
         */
        public void setCouse(String couse) {
            this.couse = couse;
        }

        /**
         * @param id
         * @param name
         * @param phone
         * @param branch
         * @param year
         * @param couse
         */
        public Student(int id, String name, long phone, String branch, int year, String couse) {
            this.id = id;
            this.name = name;
            this.phone = phone;
            this.branch = branch;
            this.year = year;
            this.couse = couse;
        }

        public int compareTo(Student o) {
            return this.id - o.id;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */

        @Override
        public String toString() {
            return "Student [branch=" + branch + ", couse=" + couse + ", id=" + id + ", name=" + name + ", phone="
                    + phone + ", year=" + year + "]";
        }

    }

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(10, 9, 9, 9, 9, 9, -1, -1, 1, -1, 1, 13, 4, -2, -8, 1, 15, 17);
        List<String> courses = Arrays.asList("API", "Microservices", "Java", "Locker", "Mocker", "Maven", "C++",
                "Kubernetes", "Docker", "Python", "Linux");
        nums.stream().distinct().forEach(System.out::println);
        System.out.println();
        // nums.stream().sorted().forEach(System.out::println);
        // System.out.println();
        // // courses.stream().sorted().forEach(System.out::println);

        // Custom Sorting

        /*
         * Default : Comparator.naturalOrder : //
         * courses.stream().sorted().forEach(System.out::println); : //
         * courses.stream().sorted(Comparator.naturalOrder()).forEach(System.out::
         * println);
         */

        // reverse order

        courses.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        System.out.println();
        // Totally custom

        // Based on length and then lexo order: using lambda expression
        courses.stream().sorted(Comparator.comparing(str -> str.length())).forEach(System.out::println);
        System.out.println();

        System.out.println("Custom Sorted ");
        courses.stream().sorted((a, b) -> {
            if (a.length() == b.length())
                return a.compareTo(b);
            return a.length() - b.length();
        }).forEach(System.out::println);

        List<Student> st = Arrays.asList(new Student(1, "Mohit", 9, "IT", 4, "B.Tech"),
                new Student(2, "Shobhit", 7, "CS", 83, "B.Tech"), new Student(3, "Payal", 5, "IT", 7, "B.Tech"),
                new Student(5, "Ravi", 6, "CS", 1, "B.SC")

        );
        // sort student by default order
        st.stream().sorted().forEach(System.out::println);
        System.out.println("Reverse Order");
        st.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        System.out.println("Custom sort of basis of Property of Student : Works on getters on directly on properties/variables");
        System.out.println("Phone : ");
        st.stream().sorted(Comparator.comparingLong(Student::getPhone)).forEach(System.out::println);
        System.out.println("Name : ");
        st.stream().sorted(Comparator.comparing(Student::getName)).forEach(System.out::println);
        System.out.println("Year in Reverse Order : ");
        st.stream().sorted(Comparator.comparing(Student::getYear).reversed()).forEach(System.out::println);

    }

}
