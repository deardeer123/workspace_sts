public class AfterGeneric {
    public static void main(String[] args) {
        GBox<String> b1 = new GBox<String>();
        b1.set("aaa");
        b1.get();
        GBox<Apple> b2 = new GBox<Apple>();
        b2.set(new Apple());
        b2.get();

        DoubleBox<Integer, String> db = new DoubleBox<>();
        db.set(10,"asdf");
        System.out.println(db);
    }
}
