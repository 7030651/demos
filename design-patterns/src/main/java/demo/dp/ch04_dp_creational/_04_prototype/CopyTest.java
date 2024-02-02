package demo.dp.ch04_dp_creational._04_prototype;

import java.util.HashMap;

import demo.dp.Test;

public class CopyTest {
    public static void main(String[] args) {
        // test1();
        test2();
    }

    private static void test2() {
        HashMap<Test, String> map1 = new HashMap<>();
        map1.put(new Test("a", 1), "aaa");
        map1.put(new Test("b", 2), "bbb");
        map1.put(new Test("c", 3), "ccc");
        System.out.println("--- map1 ---");
        System.out.println(map1);
        HashMap<Test, String> map2 = (HashMap<Test, String>) map1.clone();
        map2.put(new Test("a", 1), "aa2");
        System.out.println(map1);
        System.out.println("--- map2 ---");
        System.out.println(map2);
    }

    private static void test1() {
        HashMap<String, Test> map1 = new HashMap<>();
        map1.put("a", new Test("aaa", 1));
        map1.put("b", new Test("bbb", 2));
        map1.put("c", new Test("ccc", 3));
        
        HashMap<String, Test> map2 = (HashMap<String, Test>) map1.clone();
        System.out.println("--- map2 ---");
        System.out.println(map2);
        map2.put("a", new Test("a2", 0));
        System.out.println(map2);
        System.out.println("--- map1 ---");
        System.out.println(map1);
    }
}
