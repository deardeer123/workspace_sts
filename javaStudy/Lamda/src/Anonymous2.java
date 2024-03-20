interface GetNum{
    void get(int num1 ,int num2);
}

class GetSum implements GetNum{
    public void get(int num1, int num2){
        System.out.println(num1 + num2);
    }
}

class GetSub implements GetNum{
    @Override
    public void get(int num1, int num2){
        System.out.println(num1 - num2);
    }
}

interface TestTest{
    void test1();
    void test2(int a);
}


public class Anonymous2 {
    public static void main(String[] args) {
        new GetNum() {
            @Override
            public void get(int num1, int num2) {
                System.out.println(num1 + num2);
            }

        }.get(1,2);

        new GetNum(){
            @Override
            public void get(int a, int b){
                System.out.println(a - b);
            }
        }.get(1,2);

        GetNum n1 = new GetSum();
        n1.get(10,20);
        GetNum n2 = new GetSub();
        n2.get(10,20);
        ////

        ((GetNum) (num1, num2) -> System.out.println(num1 + num2)).get(1,2);

        ((GetNum) (num1,num2) ->System.out.println(num1 + num2)).get(1,2);

        GetNum n5 = (num1, num2)-> System.out.println(num1 + num2);
        n5.get(20,10);

        GetNum n6 = (num1, num2)-> System.out.println(num1 * num2);
        n6.get(20,10);
    }
}
