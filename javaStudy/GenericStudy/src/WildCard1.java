public class WildCard1 {
   public static void peekBox(GBox<?> box){
       System.out.println(box);
   }

   //와일드카드의 상한제한<T extend C1>
   public static void peekBox1(GBox<? extends C1> box){
       System.out.println(box);
   }
   //하한제한
   public static void peekBox2(GBox<? super C3> box){
       System.out.println(box);
   }
}
