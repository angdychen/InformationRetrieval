import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.TreeMap;

public class main {
    public static void main(final String[] args) throws Exception {
        TreeMap<String, TreeMap<Integer, int[]>> tmpmap = new TreeMap<String, TreeMap<Integer, int[]>>();
        TreeMap<Integer, int[]> docsMap;                        // �ĵ�Ƶ�� �����ĵ���������
        System.out.println("1.�Ƿ���Ҫ���������ⲿ�ֵ�? ��(Y), ��(N)");
        Scanner command = new Scanner(System.in);
        String command1 = command.next();
        // 1.���������ֵ�,��ֱ�������ڴ���
        if (command1.toLowerCase().equals("y")) {
            Index createIndex = new Index();
            tmpmap = createIndex.Create();
        } else {
            // 2.���ⲿ�ֵ�����
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Dictionary.txt"));
            String line = bufferedReader.readLine();
            while (line != null) {
                line = line.replaceAll("\\s+", " ");            // �����ַ����еĶ�ո�����
                String temp[] = line.trim().split(" ");         // �ѿո����
                String word = temp[0];                          // ��ȡ������
                int docFre = Integer.parseInt(temp[1]);         // ��ȡ����ĳ���Ƶ��
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

