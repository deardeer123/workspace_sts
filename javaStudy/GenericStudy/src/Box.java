import java.util.ArrayList;
import java.util.List;

//무엇이든 저장하고, 저장된 내용을 리턴할 수 있는 클래스
public class Box {
    private Object obj;
    // 무엇이든 넣을 수 있는 메소드
    public void set(Object obj){
        this.obj= obj;
    }

    // 저장된 데이터를 리턴하는 메소드
    public Object get(){
        return obj;
    }
}

class Apple{

    @Override
    public String toString(){
        return "나는 사과";
    }
}

class Orange{
    public String toString(){
        return "나는 오렌지";
    }
}

class Parent{
    public void p1(){
        System.out.println("111");
    }
}

class Child extends Parent{
    public void c1(){}


    public void p1() {
        System.out.println("222");
    }
}

// 변수 선언, 생성자 , 메소드의 정의
class Test{
    public void aaa(){
        Child ch1 = new Child();
        ch1.c1();

        Parent p = new Child();
        p.p1();
        // p.c1(); 오류
    }
}