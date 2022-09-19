import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Collectors;

public class FindDuplicateInFileSystem_609 {
    public List<List<String>> findDuplicate(String[] paths) {
        int n = paths.length;
        List<List<String>> ans = new ArrayList<>();
        if (n == 0)
            return ans;

        HashMap<String, List<String>> map = new HashMap<>();
        for (int p = 0; p < n; p++) {
            String[] data = paths[p].split(" ");
            String dir = data[0];

            for (int i = 1; i < data.length; i++) {
                String[] content = data[i].split("\\(");
                String file = content[0];
                String fileContent = content[1];
                String s = fileContent.substring(0, fileContent.length() - 1);
                System.out.println("s " + s);

                map.putIfAbsent(s, new ArrayList<String>());
                map.get(s).add(dir + "/" + file);
            }
        }

        for (Map.Entry<String, List<String>> e : map.entrySet()) {
            if (e.getValue().size() > 1)
                ans.add(e.getValue());
        }

        // System.out.println(map);
        return ans;
    }

    // Optimized in Runtime
    public List<List<String>> findDuplicateUsingStreams(String[] paths) {
        int n = paths.length;
        List<List<String>> ans = new ArrayList<>();
        if (n == 0)
            return ans;

        List<File> files = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] data = paths[i].split(" ");
            // data[0] => dir
            for (int j = 1; j < data.length; j++)
                files.add(new File(data[0], data[j]));
        }

        // System.out.println(files);

        // group on basis on content
        Map<String, List<File>> map = files.stream().collect(Collectors.groupingByConcurrent(file -> file.content));

        // Works with Map but fails with HashMap

        // HashMap<String, List<File>> map =
        // files.stream().collect(Collectors.groupingByConcurrent(file->file.content));
        // now we just have to add those group whose len > 1
        // 1st stream is a Stream of List and futher is a stream of Files

        // WOrking
        // List<List<File>> bucket =
        // map.values().stream().filter(el->el.size()>1).collect(Collectors.toList());

        // return bucket.stream().map(
        // listOfFiles -> listOfFiles.stream().map(file -> file.filePath)
        // .collect(Collectors.toList()) )
        // .collect(Collectors.toList());

        // Completely stream in one go :
        return map
                .values().stream().filter(el -> el.size() > 1).map(listOfFiles -> listOfFiles.stream()
                        .map(file -> file.dir + "/" + file.fileName).collect(Collectors.toList()))
                .collect(Collectors.toList());

    }

    class File {
        String dir;
        String fileName;
        String content;
        // String filePath;

        File(String dir, String file) {
            this.dir = dir;
            parse(file);
        }

        private void parse(String s) {
            int idx = s.indexOf("(");
            this.fileName = s.substring(0, idx);
            this.content = s.substring(idx + 1, s.length() - 1);
            // this.filePath = this.dir+"/"+this.fileName;
        }

        // @Override
        // public String toString(){
        // return this.dir+"/"+this.fileName+" with content : "+this.content;
        // }
    }
}
