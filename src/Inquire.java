import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.*;


public class Inquire {
    public int[] getvalue(String word, TreeMap<String, TreeMap<Integer, int[]>> map) {
        return map.get(word).firstEntry().getValue();
    }

    public int[] AndReslut(String word1, String word2, TreeMap<String, TreeMap<Integer, int[]>> map) {
        List<Integer> list = new LinkedList<Integer>();
        int[] tmp1 = new int[0];
        int[] tmp2 = new int[0];
        if (map.get(word1) != null) {
            tmp1 = getvalue(word1, map);
        } else
            System.out.println("第一个单词不存在！");

        if (map.get(word2) != null) {
            tmp2 = getvalue(word2, map);
        } else
            System.out.println("第二个单词不存在！");

        // tmp1 和 tmp2都是已经排序的,可以优化
        for (int i = 0, j = 0, k = 0; i < tmp1.length + tmp2.length; i++) {
            if (j < tmp1.length && k < tmp2.length) {
                if (tmp1[j] < tmp2[k]) {
                    j++;
                } else if (tmp1[j] > tmp2[k]) {
                    k++;
                } else {
                    list.add(tmp1[j]);
                    j++;
                    k++;
                }
            } else break;
        }

        if (list.isEmpty()) {
            int[] a = {-1};     // 满足此条件的文档不存在
            return a;
        }
        int[] tmp = new int[list.size()];
        for (int m = 0; m < tmp.length; m++)
            tmp[m] = list.get(m);
        return tmp;
    }

    public int[] OrReslut(String word1, String word2, TreeMap<String, TreeMap<Integer, int[]>> map) {
        Set<Integer> set = new TreeSet<Integer>();
        if (map.get(word1) != null) {
            int tmp1[] = getvalue(word1, map);
            for (Integer i : tmp1)
                set.add(i);
        } else
            System.out.println("第一个单词不存在！");

        if (map.get(word2) != null) {
            int tmp2[] = getvalue(word2, map);
            for (Integer i : tmp2)
                set.add(i);
        } else
            System.out.println("第二个单词不存在！");

        if (set.isEmpty()) {
            int a[] = {-1};
            return a;
        }
        int[] tmp = new int[set.size()];
        int i = 0;
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            tmp[i++] = it.next();
        }
        return tmp;
    }

    public void OutPut(int[] arr) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            if ((!list.contains(arr[i])) && (arr[i] != 0)) {//如果数组 list 不包含当前项，则增加该项到数组中
                list.add(arr[i]);
            }
        }
        int[] t = new int[list.size()];
        for (int m = 0; m < t.length; m++)
            t[m] = list.get(m);
        Arrays.sort(t);
        for (int k = 0; k < t.length; k++)
            if (t[k] != 0)
                System.out.print(t[k] + "  ");
        System.out.print("\n");
    }

    public void inquire(TreeMap<String, TreeMap<Integer, int[]>> map) {
        boolean runing = true;
        while (runing) {
            System.out.println("2.请输入查询词项(支持布尔查询): 退出(E)");
            System.out.println("\t查询格式: word1 and/or word2");
            Scanner input = new Scanner(System.in);
            String line = input.nextLine();
            if (line.toUpperCase().equals("E")) {
                runing = false;
                System.out.println("程序正常退出!");
                break;
            }
            String[] InPut = line.split("\\s+");
            String[] tmp = new String[10];
            int j = 0;
            for (int i = 0; i < InPut.length; i++) {
                if (!InPut[i].equals(null)) {
                    tmp[j] = InPut[i].toLowerCase();
                    j++;
                }
            }
            if (j == 1) {
                if (map.containsKey(tmp[0])) {
                    int[] a = getvalue(tmp[0], map);
                    OutPut(a);
                } else {
                    System.out.println("不存在!");
                }
            } else {
                if (tmp[1].equals("and")) {
                    int[] temp1 = AndReslut(tmp[0], tmp[2], map);
                    if (temp1[0] == -1) {
                        System.out.println("不存在!");
                    } else {
                        OutPut(temp1);
                    }
                }
                if (tmp[1].equals("or")) {
                    int[] temp2 = OrReslut(tmp[0], tmp[2], map);
                    if (temp2[0] == -1)
                        System.out.println("不存在!");
                    else {
                        OutPut(temp2);
                    }
                }
            }
        }
    }
}
