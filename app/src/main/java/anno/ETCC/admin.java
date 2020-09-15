package anno.ETCC;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class admin extends AppCompatActivity {

    private EditText etparam,etkitikapu;
    private Button bttrigger;
    private String parametr;
    String use,ppriceparam,pehlekyatha,s;
    private Button btback;
    private DatabaseReference databaseReference,dataentry,ddd,dd33;
    private FirebaseAuth firebaseauth;
    private ValueEventListener mchildeventlistener;
    private FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        etparam=(EditText)findViewById(R.id.ettriggerparam);
        etkitikapu=(EditText)findViewById(R.id.kitikapu);





        firebaseauth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();





        ///just the back button
        btback=(Button)findViewById(R.id.backlogin);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        //back button work ends here


        bttrigger= (Button) findViewById(R.id.bttrigger);
        bttrigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ppriceparam=etkitikapu.getText().toString().trim();
                parametr=etparam.getText().toString().trim();
                datapart();

                try {

                    dataentry=firebaseDatabase.getReference().child("Users").child(s).child("balance");

                    Integer n1= Integer.valueOf(pehlekyatha);
                    Integer n2=Integer.valueOf(ppriceparam);
                    if(n1>=n2)
                    {

                        dataentry.setValue(String.valueOf(n1-n2));

                        Toast.makeText(getApplicationContext(),"Deduction Succesfull", Toast.LENGTH_SHORT).show();


                    }
                    else  Toast.makeText(getApplicationContext(),"Users Account Balance Low ,Unsuccessfull!!!", Toast.LENGTH_SHORT).show();

                }catch (Exception e){}
            }

        });

    }




    private void datapart() {

        databaseReference = firebaseDatabase.getReference().child("Users");
        mchildeventlistener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot children : dataSnapshot.getChildren()) {
                    for (DataSnapshot child : children.getChildren()) {
                        //Log.v("key1", "   " + child.getKey());
                        if (child.getKey().equals("tagID")) {
                            for (DataSnapshot child1 : children.getChildren()) {
                                use = child.getValue(String.class);

                                if (use.equals(parametr)) {

                                    s = children.getKey();

                                }
                            }
                            // display(child);
                            // Log.v("TagIDs", "   " + child.getValue().toString());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

                Toast.makeText(getApplicationContext(), "Fetching data failed", Toast.LENGTH_SHORT).show();

            }
        };
        databaseReference.orderByKey().addListenerForSingleValueEvent(mchildeventlistener);
        //second fetching

        mchildeventlistener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot children : dataSnapshot.getChildren()) {
                    if (children.getKey().equals(s))
                     for (DataSnapshot child : children.getChildren()) {
                        //Log.v("key1", "   " + child.getKey());
                        if (child.getKey().equals("balance")) {

                                pehlekyatha = child.getValue(String.class);


                            // display(child);
                            // Log.v("TagIDs", "   " + child.getValue().toString());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

                Toast.makeText(getApplicationContext(), "Fetching data failed", Toast.LENGTH_SHORT).show();

            }
        };
        databaseReference.orderByKey().addListenerForSingleValueEvent(mchildeventlistener);




    }
    }




