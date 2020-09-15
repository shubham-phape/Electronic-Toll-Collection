package anno.ETCC;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class profile extends AppCompatActivity {
    private ImageView male1,female1;
    private FirebaseAuth firebaseauth;
    private DatabaseReference databaseReference;
    private ProgressDialog progressdialog;
    String sss;
    private ValueEventListener mchildeventlistener;
    private FirebaseDatabase firebaseDatabase;
    private TextView etnameprof,etphnoprof,etadhr,etaddss,tvcity,tvpincode,tvvehno,tvtag,tvregregion,tvvehtype,tvbal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseauth = FirebaseAuth.getInstance();
        final FirebaseUser user= firebaseauth.getCurrentUser();
        databaseReference=firebaseDatabase.getReference().child("Users");
        etnameprof=(TextView) findViewById(R.id.profilename);
        etphnoprof=(TextView) findViewById(R.id.profphoneno);
        etadhr=(TextView) findViewById(R.id.profadharcrd);
        etaddss=(TextView) findViewById(R.id.profaddress);
        tvcity=(TextView) findViewById(R.id.profcity);
        tvpincode=(TextView) findViewById(R.id.profpincode);
        tvvehno=(TextView) findViewById(R.id.profvehno);
        tvtag=(TextView) findViewById(R.id.proftagid);
        tvregregion=(TextView) findViewById(R.id.profregno);
        tvvehtype=(TextView) findViewById(R.id.profvehtype);
        tvbal=(TextView) findViewById(R.id.profbalance);
        male1= (ImageView) findViewById(R.id.profilephotomale);


        mchildeventlistener = new ValueEventListener() {



            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Object p = dataSnapshot.child(user.getUid()).getValue();
                Object c= dataSnapshot.child(user.getUid()).child("Vehicle").getValue();
                if (p instanceof HashMap) {
                    HashMap pollHash = (HashMap) p;

                    if (pollHash.containsKey("balance")) {
                        String f1 =(String) pollHash.get("balance");
                        tvbal.setText(f1);
                    }

                    if (pollHash.containsKey("gender")) {
                        sss =(String) pollHash.get("gender");
                        if(sss.equalsIgnoreCase("male"))
                            male1.setImageResource(R.drawable.man2);
                        else male1.setImageResource(R.drawable.female2);
                    }

                    if (pollHash.containsKey("name")) {
                        String f1 =(String) pollHash.get("name");
                        etnameprof.setText(f1);
                    }
                    if (pollHash.containsKey("phoneno")) {
                        String f1 =(String) pollHash.get("phoneno");
                        etphnoprof.setText(f1);
                    }
                    if (pollHash.containsKey("adharcardno")) {
                        String f1 =(String) pollHash.get("adharcardno");
                        etadhr.setText(f1);
                    }
                    if (pollHash.containsKey("address")) {
                        String f1 =(String) pollHash.get("address");
                        etaddss.setText(f1);
                    }
                    if (pollHash.containsKey("city")) {
                        String f1 =(String) pollHash.get("city");
                        tvcity.setText(f1);
                    }
                    if (pollHash.containsKey("pincode")) {
                        String f1 =(String) pollHash.get("pincode");
                        tvpincode.setText(f1);
                    }
                    if (pollHash.containsKey("tagID")) {
                        String f1 =(String) pollHash.get("tagID");
                        tvtag.setText(f1);
                    }
                }
                if (c instanceof HashMap) {
                    HashMap pollHash = (HashMap) c;

                    if (pollHash.containsKey("registration_region")) {
                        String f1 = (String) pollHash.get("registration_region");
                        tvregregion.setText(f1);
                    }

                    if (pollHash.containsKey("typeofvehicle")) {
                        String f1 = (String) pollHash.get("typeofvehicle");
                        tvvehtype.setText(f1);
                    }
                    if (pollHash.containsKey("vehicleno")) {
                        String f1 = (String) pollHash.get("vehicleno");
                      tvvehno.setText(f1);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }


        };
        databaseReference.orderByKey().equalTo(user.getUid()).addListenerForSingleValueEvent( mchildeventlistener );

    }

}
