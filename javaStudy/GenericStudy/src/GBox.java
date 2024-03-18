
// 클래스 구현 시 자료형을 정하지 않고 기능을 구현 하는것
// ㅇ자료형은 객체 생성시 정함
public class GBox<T> {
    private T obj;
    public void set(T obj){
        this.obj= obj;
    }

    public T get(){
        return obj;
    }
}
