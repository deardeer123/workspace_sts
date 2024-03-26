import java.util.Optional;

public class FriendTest {
    public static void main(String[] args) {
        showCompanyAddr(null);
    }
    public static void showCompAddr(Optional<Friend> f){
        String addr = f.map(s-> s.getCompany()).map(s-> s.getContInfo()).map(s->s.getAddr()).orElse("주소없어요");
        System.out.println(addr);


    }

    //매개벼누로 전달된 친구가 다니는 회사의 주소 정보를 출력
    public static void showCompanyAddr(Friend f){
        //null체크가 전혀 되어 있지 않음.
        String addr = null;
        Optional<Friend> o1 = Optional.ofNullable(f);

        o1.map(s->s.getCompany()).map(s->s.getContInfo()).map(s->s.getAddr()).ifPresentOrElse(s-> System.out.println(s), ()-> System.out.println("주소가 없어요"));
        if(f != null){
            Company company = f.getCompany();
            if(company != null){
                ContInfo contInfo = company.getContInfo();
                if(contInfo != null){
                    addr = contInfo.getAddr();
                }
            }
        }

        if(addr != null){
            System.out.println(addr);
        }else{
            System.out.println("주소가 없어요");
        }


    }
}
