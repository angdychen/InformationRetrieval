import java.io.*;
import java.util.*;

public class Index {

    public int getkey(TreeMap<Integer, int[]> Smap) {
        return  Smap.firstEntry().getKey();
    }

    public int[] getvalue(TreeMap<Integer, int[]> Smap) {
        return Smap.firstEntry().getValue();
    }

    public TreeMap<String, TreeMap<Integer, int[]>> Create() throws Exception {
        // map中key为单词项, value 为新的TreeMap<词频, 所出现的位置>
        TreeMap<String, TreeMap<Integer, int[]>> map = new TreeMap<String, TreeMap<Integer, int[]>>();
        TreeMap<Integer, int[]> Smap;
        int[] arr;
        try {
            // 这里假设文档集有5个文档
            for (int DocId = 1; DocId <= 5; DocId++) {
                String line = "";
                BufferedReader br = new BufferedReader(new FileReader("docs/doc" + DocId));
                line = br.readLine();                               // 读取一行
                while (line != null) {
                    String[] text = line.split("[^A-Za-z]+");       // 正则表达式匹配,分离中其中以字母开头的词(即英文单词)
                    for (int i = 0; i < text.length; i++) {
                        text[i] = text[i].toLowerCase();            // 如果词典中还没有当前单词,则加入其中
                        if (!map.containsKey(text[i])) {
                            Smap = new TreeMap<Integer, int[]>();
                            arr = new int[10];
                            arr[0] = DocId;
                            Smap.put(1, arr);
                            map.put(text[i], Smap);
                        } else {                                    // 如果词典中已经有当前单词,则增加频率
                            int count = 0;
                            Smap = new TreeMap<Integer, int[]>();
                            int a = this.getkey(map.get(text[i]));          // 获取词项的频率
                            int[] tmp = this.getvalue(map.get(text[i]));    // 获取词项出现的位置
                            for (int j = 0; j < tmp.length; j++) {
                                if (tmp[j] == DocId) {
                                    count++;
                                }
                            }
                            if (count == 0) {
                                a++;
                                tmp[a - 1] = DocId;
                                Smap.put(a, tmp);
                                map.put(text[i], Smap);
                            }
                        }
                    }
                    line = br.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 字典输出
        BufferedWriter out = new BufferedWriter(new FileWriter("Dictionary.txt"));
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String tmp = (String) it.next();    // 获取词项
            TreeMap<Integer, int[]> DocFeq = map.get(tmp);
            int[] temp = getvalue(DocFeq);
            int s = getkey(DocFeq);
            out.write(String.format("%20s" + " " +"%5s",tmp, s));
            for (int t = 0; t < temp.length; t++) {
                if (temp[t] != 0)
                    out.write(String.format(" " + "%4s",temp[t]));
            }
            out.newLine();
        }
        out.close();
        return map;

    }
}
