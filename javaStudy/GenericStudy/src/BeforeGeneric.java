public class BeforeGeneric {
    public static void main(String[] args) {
        //상자 하나 생성
        Box b1 = new Box();
        b1.set(new Apple());

        Box b2 = new Box();
        b2.set(new Orange());

        //b1 에서 데이터를 리턴
        Object o1 = b1.get();
        System.out.println(o1);
        System.out.println(o1.toString());
        //객체를 출력 - 해당 객체의 toString() 메서드 호출
        //다형성을 통해 객체가 만들어지면
        // 부모 클래스의 메서드만 실행
        // 다만 메소드 오버라이딩 개념을 통해 메소드를 만들면 자식 클래스의 메서드 실행

    }
}
