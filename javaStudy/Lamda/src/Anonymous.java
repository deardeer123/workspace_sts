//인터페이스 -> 익명 이너 클래스 -> 람다

interface  Printable{
    void print();


}

class MyPrinter implements Printable{

    @Override
    public void print() {
        System.out.println("안녕2");
    }
}


public class Anonymous{
    public static void main(String[] args) {
        //printable 인터페이스의 print() 메소드를 호출
        // 호출 결과 '안녕하세요'가 출력되면됨

        new MyPrinter().print();

        //익명 이너 클래스
        new Printable() {
            @Override
            public void print() {
                System.out.println("반갑다.");
            }
        }.print();

        ((Printable) ()->{
            System.out.println("람다 123123");
        }).print();

        Printable p2 = () ->
                System.out.println(111);


        p2.print();

    }


}
