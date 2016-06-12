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
        // map��keyΪ������, value Ϊ�µ�TreeMap<��Ƶ, �����ֵ�λ��>
        TreeMap<String, TreeMap<Integer, int[]>> map = new TreeMap<String, TreeMap<Integer, int[]>>();
        TreeMap<Integer, int[]> Smap;
        int[] arr;
        try {
            // ��������ĵ�����5���ĵ�
            for (int DocId = 1; DocId <= 5; DocId++) {
                String line = "";
                BufferedReader br = new BufferedReader(new FileReader("docs/doc" + DocId));
                line = br.readLine();                               // ��ȡһ��
                while (line != null) {
                    String[] text = line.split("[^A-Za-z]+");       // ������ʽƥ��,��������������ĸ��ͷ�Ĵ�(��Ӣ�ĵ���)
                    for (int i = 0; i < text.length; i++) {
                        text[i] = text[i].toLowerCase();            // ����ʵ��л�û�е�ǰ����,���������
                        if (!map.containsKey(text[i])) {
                            Smap = new TreeMap<Integer, int[]>();
                            arr = new int[10];
                            arr[0] = DocId;
                            Smap.put(1, arr);
                            map.put(text[i], Smap);
                        } else {                                    // ����ʵ����Ѿ��е�ǰ����,������Ƶ��
                            int count = 0;
                            Smap = new TreeMap<Integer, int[]>();
                            int a = this.getkey(map.get(text[i]));          // ��ȡ�����Ƶ��
                            int[] tmp = this.getvalue(map.get(text[i]));    // ��ȡ������ֵ�λ��
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

        // �ֵ����
        BufferedWriter out = new BufferedWriter(new FileWriter("Dictionary.txt"));
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String tmp = (String) it.next();    // ��ȡ����
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
