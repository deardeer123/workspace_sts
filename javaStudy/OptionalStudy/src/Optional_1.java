import java.util.Optional;
import java.util.function.Consumer;

public class Optional_1 {
    public static void main(String[] args) {
        //옵셔널 사용 - null 체크를 하기 위해서
        //객체 null 체크 기능이 있는 박스에 담아서 사용

        String str = "java";

        Optional<String> o1 = Optional.of(str);
        //Optional<String> o2 = Optional.ofNullable(str);
       if(o1.isPresent()){
           o1.get();
       }
       //Consumer<T> 함수형 인터페이스
        // 소비자 void accept()
        Consumer<String> c1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        Consumer<String> c = (aaa) -> {
            System.out.println(aaa);
        };

        o1.ifPresent(c);

       o1.ifPresent(s->{
           System.out.println(s);
       });
    }
}
