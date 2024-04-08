import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream6 {
    public static void main(String[] args) {
        //스트림을 생성 -> 중간 연산 -> 최종 연산
        int[] arr = {1,2,3};
        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = IntStream.rangeClosed(1,3).boxed().toList();
        Integer[] arr3 = list1.toArray(new Integer[list1.size()]);


        String[] arr1 = {"java", "python", "c++"};
        int a =Arrays.stream(arr1)
                .filter(s-> s.length()>3)
                .mapToInt(s->s.length())
                .sum();

        Arrays.stream(arr1)
                .filter(s->s.length() > 3)
                .map(s->s.length())
                .forEach(s-> System.out.println(s));
    }
}
