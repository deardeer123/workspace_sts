import java.util.ArrayList;
import java.util.List;

public class Stream9 {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("홍길동", 20, 90));
        studentList.add(new Student("이순신", 30, 80));
        studentList.add(new Student("유관순", 40, 75));
        studentList.add(new Student("임꺽정", 50, 70));
        studentList.add(new Student("신사임당", 60, 85));

        //35세 이상인 학생들의 총점을 출력
        System.out.println(studentList.stream().filter(s->s.getAge()>=35).mapToInt(s->s.getScore()).sum());

        System.out.println(studentList.stream()
                .filter(s->s.getAge()>=35)
                .map(s->s.getScore())
                .reduce((a,b)->a+b).get());
        //1등 학생 출력
        System.out.println(studentList.stream().
                reduce((a,b)->a.getScore()>b.getScore()?a:b)
                .orElse(new Student("디폴트",0,0)).getName());
    }
}

class Student{
    private String name;
    private int age;
    private int score;

    public Student(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getScore() {
        return score;
    }
}