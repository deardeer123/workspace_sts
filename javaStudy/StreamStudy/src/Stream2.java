import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream2 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        IntStream intStream = Arrays.stream(arr);
        int result1=intStream.filter(s->s<7).filter(s->s%2==0).sum();
        System.out.println(result1);
        System.out.println();

        //3
        List<Integer> list1 = IntStream.rangeClosed(1,3).boxed().toList();
        list1.stream().filter(s->s%2==0).forEach(s-> System.out.println(s));

        System.out.println();
        String[] names = {"kim", "lee", "park"};
        Stream<String> stream = Arrays.stream(names);
        stream.filter(s->s.length()==3).forEach(s-> System.out.println(s));


    }
}
