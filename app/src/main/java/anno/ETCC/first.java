package anno.ETCC;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class first extends AppCompatActivity {
    private FirebaseAuth firebaseauth;
    private Button logout;
    private Button reports,about;
    private Button recharge;
    private FloatingActionButton fab1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        firebaseauth = FirebaseAuth.getInstance();
        logout=(Button)findViewById(R.id.Logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseauth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });


        recharge=(Button)findViewById(R.id.btrecharge);
        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),recharge.class));
            }
        });

fab1=(FloatingActionButton)findViewById(R.id.floatingActionButton2);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),profile.class));
            }
        });
        about= (Button) findViewById(R.id.About);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),aboutus.class));
            }
        });
    }
}
