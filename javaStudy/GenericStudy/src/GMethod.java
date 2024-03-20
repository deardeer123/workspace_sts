import javax.annotation.processing.Generated;

public class GMethod {
    public static Box makeBox(Object obj){
        Box box = new Box();
        box.set(obj);
        return box;
    }

    //제네릭 메소드
    public static <T> GBox<T> makeGBox(T obj){
       GBox<T> gBox = new GBox<>();
       gBox.set(obj);
       return gBox;
    }

    //제네릭 메소드2
    public static <T extends C2> GBox<T> makeGBox2(T obj){
        GBox<T> gBox = new GBox<>();
        gBox.set(obj);
        obj.aaa();
        obj.bbb();

        return gBox;
    }

    //제네릭 메소드임
    public static <T> void peekBox1(GBox<T> box){
        System.out.println(box);
    }

    //와일드 카드
    public static void peekBox3(GBox<?> box){
        System.out.println(box);
    }

    //제네릭 메소드 아님
    public static void peekBox2(GBox<Object> box){
        System.out.println(box);
    }

    public void aaa(){
        GBox<String> b1 = new GBox<>();
        peekBox3(b1);
    }
}
