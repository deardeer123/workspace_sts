import java.util.Arrays;
import java.util.List;

public class Stream8 {
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("java", "python", "c++", "mariadb", "spring");
        //리스트에서 갈자수가 짝수인 글자들의 나열한 결과를 출력 Stream
        String c =list1.stream().filter(s -> s.length()%2==0).reduce("e",(a,b)->(a.length()+b.length())>99?"a":"b");
        System.out.println(c);
    }
}
