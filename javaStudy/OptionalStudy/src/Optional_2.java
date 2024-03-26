import java.util.Optional;
import java.util.function.Function;

public class Optional_2 {
    public static void main(String[] args) {
        Optional<String> o1 = Optional.of("Optional String");

        //Function<String, U>
        //Function<String, String> f = s -> s.replace(" " ,"_");

        System.out.println(o1.map(s -> s.replace(" " ,"_")).get());
    }
}
