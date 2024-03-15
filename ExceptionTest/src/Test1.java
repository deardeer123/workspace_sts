import java.util.InputMismatchException;
import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        System.out.println("프로그램 시작");
        Scanner sc = new Scanner(System.in);
        try {
            //예외가 발생할 수 있는 코드
            int num1 = sc.nextInt();
            int num2 = sc.nextInt();
            System.out.println(num1 / num2);
        } catch (ArithmeticException e){
            System.out.println("모든 수는 0으로 나눌 수 없어요");
        } catch (InputMismatchException e){
            System.out.println("숫자를 입력 받으세요");
        } finally {
            //예외 발생 유무와 상관없이 무조건 실행되어야하는 코드
        }

        System.out.println("프로그램 종료");

        

    }
}
