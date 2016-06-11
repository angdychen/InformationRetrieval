import jdk.nashorn.internal.ir.IndexNode;
import jdk.nashorn.internal.ir.ReturnNode;
import sun.plugin2.gluegen.runtime.BufferFactory;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.List;


public class main {
    public static void main(final String[] args) throws Exception {
        TreeMap<String, TreeMap<Integer, int[]>> tmpmap = new TreeMap<String, TreeMap<Integer, int[]>>();
        TreeMap<Integer, int[]> docsMap;        // �ĵ�Ƶ�� �����ĵ���������
        System.out.println("1.�Ƿ���Ҫ���������ⲿ�ֵ�? ��(Y), ��(N)");
        Scanner command = new Scanner(System.in);
        String command1 = command.next();
        // 1.���������ֵ�,��ֱ�������ڴ���
        if (command1.toLowerCase().equals("y")) {
            CreateIndex createIndex = new CreateIndex();
            tmpmap = createIndex.Create();
        } else {
            // 2.���ⲿ�ֵ�����
            BufferedReader bufferedReader = new BufferedReader(new FileReader("dict.txt"));
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
        BooleanInquire search = new BooleanInquire();
        search.inquire(tmpmap);
    }
}

