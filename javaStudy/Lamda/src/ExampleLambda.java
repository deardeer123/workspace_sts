import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

interface Predict{
    boolean test(int num);
}
public class ExampleLambda {
    public static void main(String[] args) {
        //매개변수로 들어온 수가 짝수 일때만 true 리턴
        Predict p1 = a -> a%2==0;
        System.out.println(p1.test(10));
        //매개변수로 들어온 수가 10보다 작을때만 true 리턴
        Predict p2 = a -> a<10;
        System.out.println(p2.test(9));
        //매개변수로 들어온 수가 10이면 true
        Predict p3 = a -> a==10;
        System.out.println(p3.test(10));

        Integer[] arr = {1,2,3,4,5,6,7,8,9,10};
        List<Integer> list = Arrays.asList(arr);
        List<Integer> list1 = new ArrayList<>(list);


        System.out.println(list);
        lambda(list , a -> a%2==0);

        Predicate<Integer> p = a -> (a<5);
        list1.removeIf(p);
        list1.forEach(num -> System.out.println(num));
    }

    public static void lambda(List<Integer> list, Predict p){
        int sum = 0;

        for(int e : list){
            if(p.test(e)){
                sum = sum + e;
            }
        }
        System.out.println(sum);
    }
}
