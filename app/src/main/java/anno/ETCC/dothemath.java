package anno.ETCC;

/**
 * Created by Shubham Phape on 4/5/2017.
 */

public class dothemath {
    public dothemath() {
    }
    public String var1;
    public String var2;

    public dothemath(String var1, String var2) {
        this.var1 = var1;
        this.var2 = var2;
    }



      public String addkaro(String var1,String var2)
      {
         Integer n1,n2 = null;
          n1=Integer.valueOf(var1);
          if(var2 != null && !var2.trim().isEmpty()) {
              n2 = Integer.valueOf(var2);
          }
          return Integer.toString(n1+n2);
      }
}
