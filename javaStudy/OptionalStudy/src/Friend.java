public class Friend {
    String name;
    Company company;

    public Friend(String name, Company company){
        this.name = name;
        this.company = company;
    }

    public String getName(){
        return name;
    }

    public Company getCompany(){
        return company;
    }

}

class Company{
    String companyName;
    ContInfo contInfo;

    public Company(String companyName, ContInfo contInfo){
        this.companyName = companyName;
        this.contInfo = contInfo;
    }

    public String getCompanyName(){
        return companyName;
    }
    public ContInfo getContInfo(){
        return contInfo;
    }
}

class ContInfo{
    String tel;
    String addr;

    public ContInfo(String tel, String addr){
        this.tel = tel;
        this.addr= addr;
    }

    public String getTel(){
        return tel;
    }
    public String getAddr(){
        return addr;
    }
}