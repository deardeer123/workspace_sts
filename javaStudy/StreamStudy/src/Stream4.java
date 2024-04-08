import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stream4 {
    public static void main(String[] args) {

        List<String> list1 = Arrays.asList("box", "robot", "simple");
        List<String> list2 = new ArrayList<>(list1);
        list2.add("hello");
        list2.add("aaabbbcc");
        list2.stream().map(s->s.length()).forEach(s-> System.out.println(s));






    }
}
