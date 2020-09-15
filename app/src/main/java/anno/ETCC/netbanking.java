package anno.ETCC;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class netbanking extends AppCompatActivity {
    private Button btcon;
    private FirebaseAuth firebaseauth;
    private DatabaseReference databaseReference;
    private String rechamt;
    String prevbalance;
    private ValueEventListener mchildeventlistener;
    private FirebaseDatabase firebaseDatabase;
    private Button btpaycredit;
    Integer prev0;
    TextView t1;
    private EditText etprice,etus,etpsd;
    String att1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netbanking);
        firebaseauth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseauth.getCurrentUser();
etus=(EditText)findViewById(R.id.etusernamenet);
        etpsd=(EditText)findViewById(R.id.passdnet);


        btcon = (Button) findViewById(R.id.netpaybt);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Users");

        firebaseDatabase = FirebaseDatabase.getInstance();


        mchildeventlistener = new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                form1card value = dataSnapshot.getValue(form1card.class);

                display(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        };
        databaseReference.orderByKey().equalTo(user.getUid()).addListenerForSingleValueEvent(mchildeventlistener);





        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).child("balance");
        btpaycredit = (Button) findViewById(R.id.netpaybt);
        etprice = (EditText) findViewById(R.id.pricekiti);

        btpaycredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a6=etpsd.getText().toString().trim();
                String a7=etus.getText().toString().trim();

                if (TextUtils.isEmpty(a6)) {
                    //email check here
                    Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
                    //stop this function
                    return;
                }
                if (TextUtils.isEmpty(a7)) {
                    //email check here
                    Toast.makeText(getApplicationContext(), "Please enter Username", Toast.LENGTH_SHORT).show();
                    //stop this function
                    return;
                }
                try{
                    att1= etprice.getText().toString().trim();
                    String change=att1;
                    int i=Integer.valueOf(change);
                    att1=String.valueOf(i+prev0);
                    databaseReference.setValue(att1);
                    Toast.makeText(getApplicationContext(), "Payment Successfull", Toast.LENGTH_LONG).show();
                    finish();
                    finish();
                    startActivity(new Intent(getApplicationContext(), first.class));
                }
                catch (Exception e)
                {}

            }
        });
    }

    public void display(DataSnapshot u) {

        FirebaseUser user = firebaseauth.getCurrentUser();
        Object p = u.child(user.getUid()).getValue();

        if (p instanceof HashMap) {
            HashMap pollHash = (HashMap) p;

            if (pollHash.containsKey("balance")) {

                prevbalance =(String) pollHash.get("balance");


                if(prevbalance != null && !prevbalance.trim().isEmpty()) {
                    prev0 = Integer.valueOf(prevbalance);

                }
            }

        }
    }
}

