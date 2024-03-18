// 제네릭 클래스의 타입 인자 제한하기
// 타입 인자 제한이 없다면 호출할 수 있는 메서드는 Object 메서드만 사용가능
// T extends C!
// T(정해지지 않은 자료형)에는 C1 클래스를 상속받은 클래스 혹은 C1 클래스만 올 수 있다.
public class GBox2<T extends C1> {
    private T obj;
    public void set(T obj){
        this.obj= obj;

    }

    public T get(){
        return obj;
    }
}

class C1{
    public void aaa(){};
}
class C2 extends C1{
    public void bbb(){};
}
class C3 extends C2{
    public void ccc(){};
}