import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    static List<Student> st = Arrays.asList(new Student(1, "Mohit", 9, "IT", 4, "B.Tech"),
            new Student(2, "Shobhit", 7, "CS", 83, "B.Tech"), new Student(3, "Payal", 5, "IT", 7, "B.Tech"),
            new Student(5, "Ravi", 6, "CS", 1, "B.SC")

    );

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(10, 12, 13, 14, 5, 6, 7, 9, 9, 9, 9, 9, -1, -1, 1, -1, 1, 13, 4, -2, -8, 1,
                15, 17);
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

        // Article: stackabuse.com/java-8-how-to-sort-list-with-stream-sorted/
        // sort student by default order
        st.stream().sorted().forEach(System.out::println);
        System.out.println("Reverse Order");
        st.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        System.out.println(
                "Custom sort of basis of Property of Student : Works on getters on directly on properties/variables");
        System.out.println("Phone : ");
        st.stream().sorted(Comparator.comparingLong(Student::getPhone)).forEach(System.out::println);
        System.out.println("Name : ");
        st.stream().sorted(Comparator.comparing(Student::getName)).forEach(System.out::println);
        System.out.println("Year in Reverse Order : ");
        st.stream().sorted(Comparator.comparing(Student::getYear).reversed()).forEach(System.out::println);

        // Using .thenComparing for Custom Sorting on multiple attributes
        // ex: sort on basis of Course in descending and the sort in order of id

        Comparator<Student> myComp = Comparator.comparing(Student::getCouse).reversed().thenComparing(Student::getId);
        List<Student> sortedSt = st.stream().sorted(myComp).collect(Collectors.toList());
        System.out.println("\nSorted st using cutom comparaotr ny using .thenComparing ");
        sortedSt.stream().forEach(System.out::println);
        System.out.println();

        // Not reliable : use your own compartor like you always do
        // ex: sort on basis of Course in descending and the sort in order of names des
        Comparator<Student> myComp2 = Comparator.comparing(Student::getCouse).reversed().thenComparing(Student::getName)
                .thenComparing(Comparator.reverseOrder());

        List<Student> soted2 = st.stream().sorted(myComp2).collect(Collectors.toList());
        soted2.stream().forEach(System.out::println);
        System.out.println();

        System.out.println();

        // ************************************************
        // Straeam Prop : allMathc, noneMath, anyMatch
        Predicate<Student> p = x -> x.getName().length() < 10;
        System.out.println("All student have name length < 10 " + st.stream().allMatch(p));
        System.out.println("None student have name length < 10 " + st.stream().noneMatch(p));
        System.out.println("Any student whose name length < 10 " + st.stream().anyMatch(p));

        // ***********************************
        // limit skip on answers

        System.out.println(st.stream().limit(2).collect(Collectors.toList()));
        System.out.println(st.stream().skip(2).collect(Collectors.toList()));
        System.out.println(st.stream().skip(2).limit(2).collect(Collectors.toList()));
        System.out.println(st.stream().limit(3).skip(1).collect(Collectors.toList()));

        // ************************************

        // TakeWhile: take elemets while predicate is true : stop on encounterig the
        // first false: just like while

        // Dropwhile: oppsite of takehilw => skips elemts while condition is true and
        // take all the elemets after that (wheteher they mathc criteria or not)

        // Baiscally both works untile first falacity
        System.out.println("\nTake While");
        System.out.println(nums.stream().takeWhile(x -> x > 5).collect(Collectors.toList()));
        System.out.println("\nDrop While");
        System.out.println(nums.stream().dropWhile(x -> x > 5).collect(Collectors.toList()));

        aggregateProperties();
    }

    private static void aggregateProperties() {
        List<Integer> nums = Arrays.asList(10, 12, 13, 14, 5, 6, 7, 9, 9, 9, 9, 9, -1, -1, 1, -1, 1, 13, 4, -2, -8, 1,
                15, 17);
        List<String> courses = Arrays.asList("API", "Microservices", "Java", "Locker", "Mocker", "Maven", "C++",
                "Kubernetes", "Docker", "Python", "Linux");

        // Aggreate functions return OptionalType as an default ans and the actuala ans
        // is also wrapped in Optional Conver
        // we can provide the Optional.empty replacement value too

        // Takes comparator : default or custom : max on which criteria

        System.out.println(nums.stream().max(Comparator.naturalOrder()));

        Comparator<Student> myComp = Comparator.comparing(Student::getCouse).reversed().thenComparing(Student::getId);
        System.out.println(st.stream().max(myComp));

        // Optionals are way to handle null
        // example: on filterning no result is there so min must give Optional.empty
        // instead of null
        System.out.println(nums.stream().filter(x -> x > 9999).min(Comparator.naturalOrder()));

        // Providing default values for empty
        System.out.println(nums.stream().filter(x -> x > 9999).min(Comparator.naturalOrder()).orElse(-1));

        // find the avg marks of students from B.tech whose score > 50
        Predicate<Student> btechScoreGt50 = x -> (x.getBranch().equals("B.Tech") && x.getYear() > 50);
        System.out.println(st.stream().filter(btechScoreGt50).mapToInt(Student::getYear).sum());

    }

}
