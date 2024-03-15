public class Test2 {
    public static void main(String[] args) {
        m2();
    }

    public static void m1(int n1, int n2){
        int result = n1/n2;
    }
    public static void m2(){
        m1(10,0);
    }
}
