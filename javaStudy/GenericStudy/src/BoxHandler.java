public class BoxHandler {
    public static void moveBox(GBox<? extends Toy> box1 , GBox<? super  Toy> box2){
        box2.set(box1.get());
    }

    //상자에 들어있는 내용물을 추출하는 메서드
    public static <T extends Toy> void outBox(GBox<? extends T> box){
         T toy = box.get();
//        setter가 안됨
       //box.set(new Toy());

    }
    //상자에 내용물을 적재하는 메서드
    public static void inBox(GBox<? super Toy> box, Toy toy){
        box.set(toy);
        //trash t = box.get();
    }

}
class trash {}

class Toy extends trash{
    public void broken(){

    }
}
class Robot extends Toy{
    public void wingChicken(){

    }
}
class Car extends Toy{
    public void drive(){

    }
}