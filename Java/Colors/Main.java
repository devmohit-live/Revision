
public class Main {
    // public static final String RED = "\u001B[31m";
    // public static final String BLACK = "\u001B[30m";
    // public static final String BLACK_BACKGROUND = "\u001B[40m";
    // public static final String RED_BACKGROUND = "\u001B[41m";
    // public static final String GREEN = "\u001B[32m";
    // public static final String GREEN_BACKGROUND = "\u001B[42m";
    // public static final String YELLOW = "\u001B[33m";
    // public static final String YELLOW_BACKGROUND = "\u001B[43m";
    // public static final String BLUE = "\u001B[34m";
    // public static final String BLUE_BACKGROUND = "\u001B[44m";
    // public static final String PURPLE = "\u001B[35m";
    // public static final String PURPLE_BACKGROUND = "\u001B[45m";
    // public static final String CYAN = "\u001B[36m";
    // public static final String CYAN_BACKGROUND = "\u001B[46m";
    // public static final String WHITE = "\u001B[37m";
    // public static final String WHITE_BACKGROUND = "\u001B[47m";
    // public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        final ANSIColors colors = new ANSIColors();
        System.out.println(colors.BLUE + "This is blue color");
        System.out.println(colors.BLACK + "This is black color");
        System.out.println(colors.RED + "This is red color");
        System.out.println(colors.GREEN + "This is green color");
        System.out.println(colors.PURPLE + "This is purple color");
        System.out.println(colors.CYAN + "This is cyan color");
        System.out.println(colors.WHITE + "This is white color");
        System.out.println(colors.YELLOW + "This is yellow color");
        System.out.println(colors.BLACK_BACKGROUND + "This is black background color");
        System.out.println(colors.BLUE_BACKGROUND + "This is BLUE background color");
        System.out.println(colors.RED_BACKGROUND + "This is RED background color");
        System.out.println(colors.GREEN_BACKGROUND + "This is GREEN background color");
        System.out.println(colors.PURPLE_BACKGROUND + "This is PURPLE background color");
        System.out.println(colors.CYAN_BACKGROUND + "This is CYAN background color");
        System.out.println(colors.WHITE_BACKGROUND + "This is WHITE background color");
        System.out.println(colors.YELLOW_BACKGROUND + "This is YELLOW background color");
        System.out.println(colors.YELLOW_BACKGROUND + colors.RED + "This is redcolor on yellow background color");
        System.out.println(colors.ANSI_RESET + "Resetting Color");
    }
}