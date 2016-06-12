import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.TreeMap;

public class main {
    public static void main(final String[] args) throws Exception {
        TreeMap<String, TreeMap<Integer, int[]>> tmpmap = new TreeMap<String, TreeMap<Integer, int[]>>();
        TreeMap<Integer, int[]> docsMap;                        // 文档频率 所在文档集的数组
        System.out.println("1.是否需要重新生成外部字典? 是(Y), 否(N)");
        Scanner command = new Scanner(System.in);
        String command1 = command.next();
        // 1.重新生成字典,并直接载入内存中
        if (command1.toLowerCase().equals("y")) {
            Index createIndex = new Index();
            tmpmap = createIndex.Create();
        } else {
            // 2.从外部字典载入
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Dictionary.txt"));
            String line = bufferedReader.readLine();
            while (line != null) {
                line = line.replaceAll("\\s+", " ");            // 处理字符串中的多空格问题
                String temp[] = line.trim().split(" ");         // 已空格隔开
                String word = temp[0];                          // 提取出单词
                int docFre = Integer.parseInt(temp[1]);         // 提取词项的出现频率
                int[] docs = new int[temp.length - 2];
                for (int i = 2; i < temp.length; i++) {
                    docs[i - 2] = Integer.parseInt(temp[i]);
                }
                docsMap = new TreeMap<Integer, int[]>();
                docsMap.put(docFre, docs);
                tmpmap.put(word, docsMap);
                line = bufferedReader.readLine();
            }
        }
        Inquire search = new Inquire();
        search.inquire(tmpmap);
    }
}

