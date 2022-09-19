public class FindDuplicateInFileSystem_609 {
    public List<List<String>> findDuplicate(String[] paths) {
        int n = paths.length;
        List<List<String>> ans = new ArrayList<>();
        if(n==0) return ans;
        
        HashMap<String , List<String>> map = new HashMap<>();
        for(int p=0;p<n;p++){
            String[] data = paths[p].split(" ");
            String dir = data[0];
        
            for(int i=1;i<data.length;i++){
                String[] content = data[i].split("\\(");
                String file = content[0];
                String fileContent = content[1];
                String s = fileContent.substring(0,fileContent.length()-1);
                System.out.println("s "+s);

                map.putIfAbsent(s, new ArrayList<String>());
                map.get(s).add(dir + "/" + file);                
            }
        }
        
        
        for(Map.Entry<String , List<String>> e: map.entrySet()){
            if(e.getValue().size()>1)
                ans.add(e.getValue());
        }
        
        // System.out.println(map);        
        return ans;
    }
}
