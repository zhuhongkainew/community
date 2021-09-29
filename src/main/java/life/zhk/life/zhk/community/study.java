package life.zhk.life.zhk.community;

import java.util.Arrays;
import java.util.List;

public class study {
    public static void main(String[] args) {
//        Integer[] arr={1,2,3};
//        testPerson(arr1->{
//            System.out.println(arr1[1]+arr1[2]);
//        },arr);
        List<String> integerList = Arrays.asList("11","12","13");
        integerList.stream().map(s -> Integer.parseInt(s)+1).forEach(System.out::println);
        integerList.stream().map(s -> Integer.parseInt(s)+1).sorted((s1,s2)->s1-s2).forEach(System.out::println);
        integerList.stream().limit(2).forEach(System.out::println);
        integerList.stream().skip(2).forEach(System.out::println);
        boolean b = integerList.stream().map(s -> Integer.parseInt(s)+1).allMatch(s -> s > 1);
        boolean c = integerList.stream().map(s -> Integer.parseInt(s)+1).noneMatch(s -> s > 1);
        boolean d = integerList.stream().map(s -> Integer.parseInt(s)+1).anyMatch(s -> s > 1);


//        integerList.stream().filter(s -> s.length()==3).forEach(s->{
//            System.out.println(s);
//            if(s.startsWith("里")){
//                    System.out.println("第二次输出-----"+s);}
//        }
//        );
    }


    static void testPerson(person person,Integer[] arr){
        System.out.println(arr);
            person.test(arr);
    }

    interface person{
        void test(Integer[] arr);
    }
}
