import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Stream5 {
    public static void main(String[] args) {
        List<Toy> toyList = new ArrayList<>();
        toyList.add(new Toy("토이스토리", 20000));
        toyList.add(new Toy("로봇", 30000));
        toyList.add(new Toy("레고", 50000));
        int sum = 0;
        System.out.println(toyList.stream().mapToInt(s -> s.getPrice()).filter(s -> s > 25000).sum());


    }
}

class Toy{
    private String modelName;
    private int price;

    public Toy(String a, int b){
        modelName = a;
        price = b;
    }

    public int getPrice(){
        return price;
    }
}