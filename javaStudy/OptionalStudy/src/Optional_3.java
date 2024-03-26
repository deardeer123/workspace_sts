import java.util.Optional;

public class Optional_3 {
    public static void main(String[] args) {
        Optional<String> o1 = Optional.of("java Python");
        System.out.println(o1.map(s -> s.replace(" ", ""))
                .map(s -> s.toUpperCase()).get());
    }
}
