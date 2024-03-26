import java.util.Optional;
import java.util.function.Consumer;

public class Optional_5 {
    public static void main(String[] args) {
        ContInfo c = new ContInfo("01011112222", "울산시");
        Optional<ContInfo> c1 = Optional.ofNullable(c);
        System.out.println(c1.map(s->s.getTel()).orElse("연락처없어요"));

    }
}
