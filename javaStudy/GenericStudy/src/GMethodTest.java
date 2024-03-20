import java.lang.reflect.Type;

public class GMethodTest {
    public static void main(String[] args) {
        Apple apple = new Apple();
        Box box = GMethod.makeBox(apple);
        Apple a = (Apple)box.get();



        GBox<String> b1 = GMethod.makeGBox("apple");
        String data1 = b1.get();

        GBox<Apple> b2 = GMethod.makeGBox(apple);
        Apple data2 = b2.get();


    }
}
