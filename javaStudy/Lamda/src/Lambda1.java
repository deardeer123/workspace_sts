interface Calculator2{
        int cal(GBox<? extends Integer> a, GBox<? extends Integer> b);
}

interface Calculator{
    int cal(int a, int b);
}

interface Basic{
    void print();
}

interface PrintMsg{
    void print(String msg);
}

class GBox<T> {
    private T obj;
    public void set(T obj){
        this.obj= obj;
    }

    public T get(){
        return obj;
    }
}



public class Lambda1 {
    public static void main(String[] args) {
        ((Basic) () ->{
            System.out.println("javajava123");
        }).print();

        PrintMsg m1 = a -> {
            System.out.println(a);
        };
        m1.print("자바자바123");

        Calculator calcal = (num1,num2) -> num1+num2;

        System.out.println(calcal.cal(10,20));

        Calculator2 calcal2 = (n1,n2) ->{
            return n1.get() + n2.get();
        };

        GBox<Integer> g1 = new GBox<>();
        g1.set(100);
        GBox<Integer> g2 = new GBox<>();
        g2.set(200);

        System.out.println(calcal2.cal(g1,g2));

    }
}
