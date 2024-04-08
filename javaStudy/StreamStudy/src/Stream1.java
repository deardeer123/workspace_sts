import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream1 {
    public static void main(String[] args) {
        //Stream은 배열, 리스트 데이터에서 활용
        int[] arr = {1,2,3,4,5};

        //배열의 스트림 생성(여과기에 흘려보낼 수 있는 형태로로 데이터를 변환한다.)
        IntStream stm1  =Arrays.stream(arr);
        IntStream stm2  =Arrays.stream(arr);
        System.out.println(stm2.filter(s->s%2 != 0).sum());
        stm1.filter(s->s%2!=0).peek(s-> System.out.print(s+" ")).filter(s->s>4).forEach(s-> System.out.println("\n"+s));
    }
}
