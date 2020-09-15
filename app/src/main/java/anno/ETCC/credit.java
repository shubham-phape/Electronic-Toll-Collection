package anno.ETCC;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class credit extends AppCompatActivity {
    private Spinner spinnercredt;
    private EditText amountrecharge,card1,crad2,card3,card4,ccccv,dt,nm;
    private String cardtype;
    private FirebaseAuth firebaseauth;
    private DatabaseReference databaseReference,dddddd;
    private String rechamt;
    String prevbalance;
    String currbalance;
    private ValueEventListener mchildeventlistener;
    private FirebaseDatabase firebaseDatabase;
    private Button btpaycredit;
    Integer prev,cur;
    TextView t1;
    String att;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
     //   rechamt = getIntExtra("Value",0);
        amountrecharge = (EditText) findViewById(R.id.rechaargeamount);
        card1=(EditText)findViewById(R.id.cardno_1);
        crad2=(EditText)findViewById(R.id.cardno_2);
        card3=(EditText)findViewById(R.id.cardno_3);
        card4=(EditText)findViewById(R.id.cardno_4);
        ccccv=(EditText)findViewById(R.id.etccv);
        dt=(EditText)findViewById(R.id.exdtae);
        nm=(EditText)findViewById(R.id.nameoncard);


        firebaseauth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseauth.getCurrentUser();
        spinnercredt = (Spinner) findViewById(R.id.creditspinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.typeofcard, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnercredt.setAdapter(adapter);
        spinnercredt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cardtype = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
t1=(TextView)findViewById(R.id.entercardno);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Users");


        mchildeventlistener = new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                form1card value = dataSnapshot.getValue(form1card.class);

                display(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                Toast.makeText(credit.this, "Nahi ho paya", Toast.LENGTH_SHORT).show();
            }
        };
        databaseReference.orderByKey().equalTo(user.getUid()).addListenerForSingleValueEvent(mchildeventlistener);




        dddddd=FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid());
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).child("balance");
        btpaycredit = (Button) findViewById(R.id.payanddone);



        btpaycredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                att = amountrecharge.getText().toString().trim();
                String a1=crad2.getText().toString().trim();
                String a2=card1.getText().toString().trim();
                String a3=card3.getText().toString().trim();
                String a4=card4.getText().toString().trim();
                String a5=ccccv.getText().toString().trim();
                String a6=dt.getText().toString().trim();
                String a7=nm.getText().toString().trim();

                if (TextUtils.isEmpty(a1)) {
                    //email check here
                    Toast.makeText(getApplicationContext(), "Enter full card number", Toast.LENGTH_SHORT).show();
                    //stop this function
                    return;
                }
                if (TextUtils.isEmpty(a2)) {
                    //email check here
                    Toast.makeText(getApplicationContext(), "Enter full card number", Toast.LENGTH_SHORT).show();
                    //stop this function
                    return;
                }

                if (TextUtils.isEmpty(a3)) {
                    //email check here
                    Toast.makeText(getApplicationContext(), "Enter full card number", Toast.LENGTH_SHORT).show();
                    //stop this function
                    return;
                }

                if (TextUtils.isEmpty(a4)) {
                    //email check here
                    Toast.makeText(getApplicationContext(), "Enter full card number", Toast.LENGTH_SHORT).show();
                    //stop this function
                    return;
                }
                if (TextUtils.isEmpty(a5)) {
                    //email check here
                    Toast.makeText(getApplicationContext(), "Enter CCV code", Toast.LENGTH_SHORT).show();
                    //stop this function
                    return;
                }
                if (TextUtils.isEmpty(a6)) {
                    //email check here
                    Toast.makeText(getApplicationContext(), "Please enter date", Toast.LENGTH_SHORT).show();
                    //stop this function
                    return;
                }

                if (TextUtils.isEmpty(a7)) {
                    //email check here
                    Toast.makeText(getApplicationContext(), "Please enter name", Toast.LENGTH_SHORT).show();
                    //stop this function
                    return;
                }

                if (TextUtils.isEmpty(att)) {
                    //email check here
                    Toast.makeText(getApplicationContext(), "Enter Amount", Toast.LENGTH_SHORT).show();
                    //stop this function
                    return;
                }
                try{


                    if(att != null && !att.trim().isEmpty()) {
                        cur = Integer.valueOf(att);}
                    databaseReference.setValue(String.valueOf(cur+prev));

                    finish();
                    finish();
                    startActivity(new Intent(getApplicationContext(), first.class));
                }
                catch (Exception e)
                {

                }


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
                    prev = Integer.valueOf(prevbalance);
                    t1.setText(prevbalance);
                }
            }

        }
    }
}
