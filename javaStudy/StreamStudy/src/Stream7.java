import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BinaryOperator;

public class Stream7 {
    public static void main(String[] args) {
        //중간연산 : filter, map, map, mapToInt, reduce
        List<String> list = Arrays.asList("box", "simple", "complex", "robot");
//        BinaryOperator<String> b =
//            (String o, String o2) -> {
//                return o.length()>o2.length() ? o : o2;
//            };
//  첫번째 매개변수 : 기존 데이터의 첫번째 요소로 추가할 데이터
//  두번째 매개변수는 BinaryOperator<T> : T apply(T t1, T t2)

        String result = list.stream().reduce("ㄴㅇㄹㄴㅇㄹ",(a,b)->a.length()>9999999 ?a:"b");
        System.out.println(result);
    }
}
