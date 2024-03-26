import java.util.Optional;

public class Optional_4 {
    public static void main(String[] args) {
        String data = "java";
        Optional<String> o1 = Optional.ofNullable(data);
        String str = o1.map(s->s.toUpperCase()).orElse("none");
    }
}
