package anno.ETCC;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class reports extends AppCompatActivity {
  EditText et1,et2,et3,et4;
    Button b1;
    private FirebaseAuth firebaseauth;
    private ValueEventListener mchildeventlistener;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseauth = FirebaseAuth.getInstance();
        databaseReference=firebaseDatabase.getReference().child("Users");
        et1=(EditText)findViewById(R.id.lastrech);
        et2=(EditText)findViewById(R.id.balbefore);
        et3=(EditText)findViewById(R.id.lastdeduc);
        et4=(EditText)findViewById(R.id.befdec);
        final FirebaseUser user= firebaseauth.getCurrentUser();
        mchildeventlistener = new ValueEventListener() {



            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Object p = dataSnapshot.child(user.getUid()).getValue();

                if (p instanceof HashMap) {
                    HashMap pollHash = (HashMap) p;

                    if (pollHash.containsKey("previousbalancebeforerec")) {
                        String f1 = (String) pollHash.get("previousbalancebeforerec");
                        et2.setText(f1);

                    }
                    if (pollHash.containsKey("lastrecharge")) {
                        String f1 = (String) pollHash.get("lastrecharge");
                        et1.setText(f1);

                    }
                    if (pollHash.containsKey("balancebefdeduc")) {
                        String f1 = (String) pollHash.get("balancebefdeduc");
                        et3.setText(f1);

                    }
                    if (pollHash.containsKey("previousdeduction")) {
                        String f1 = (String) pollHash.get("previousdeduction");
                        et4.setText(f1);

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
