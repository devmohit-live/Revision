import java.nio.file.Paths;
import java.io.IOError;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class FileOp {
    public static void main(String[] args) throws IOException {
        Files.lines(Paths.get("file.txt")).forEach(System.out::println);

        // Files.lines(Paths.get("file.txt") ) => gives stream

        // Get distinct words into a list : flatMap needed to be used
        Files.lines(Paths.get("file.txt")).map(s -> s.split(" ")).flatMap(Arrays::stream).distinct()
                .forEach(System.out::println);
    }
}
