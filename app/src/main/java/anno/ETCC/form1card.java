package anno.ETCC;

/**
 * Created by Shubham Phape on 4/3/2017.
 */

public class form1card {
    public form1card(){}
    public String name;
    public String gender;
    public String phoneno;
    public String adharcardno;
    public String address;
    public String city;
    public String pincode;
    public String  balance;
    public  String tagID;
    public form1card(String name, String gender, String phoneno, String adharcardno, String address, String city,String pincode, String balance,String tag) {
        this.name = name;
        this.gender = gender;
        this.phoneno = phoneno;
        this.adharcardno = adharcardno;
        this.address = address;
        this.city = city;
        this.pincode=pincode;
        this.balance=balance;
        this.tagID=tag;
    }

    public String getTagID() {
        return tagID;
    }

    public String getbalance(){

        return balance;

    }


}
