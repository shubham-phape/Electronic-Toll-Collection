package anno.ETCC;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import static anno.ETCC.R.id.etEmail;

public class Login extends AppCompatActivity {
    private Button buttonlogin;
    private EditText edtEmail;
    private EditText edtPassword;
    private TextView tvsignup;
private CardView crd;
    private FirebaseAuth firebaseauth;
    private FirebaseDatabase firebaseDatabase;
    private ProgressDialog progressdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseauth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseauth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        if (firebaseauth.getCurrentUser() != null) {
            //directly start the profile activity
            finish();
            startActivity(new Intent(getApplicationContext(), first.class));
        }
        setContentView(R.layout.activity_login);
        progressdialog = new ProgressDialog(this);
        edtEmail = (EditText) findViewById(etEmail);
        edtPassword = (EditText) findViewById(R.id.etPassword);
        buttonlogin = (Button) findViewById(R.id.btsignin);
        tvsignup = (TextView) findViewById(R.id.tvSignup);

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginuser();
            }
        });

        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), signup.class));
            }
        });

        crd=(CardView)findViewById(R.id.baradmin);
        crd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),adminlogin.class));
            }
        });
    }



    private void loginuser() {

        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            //email check here
            Toast.makeText(this, "please enter email", Toast.LENGTH_SHORT).show();
            //stop this function
            return;
        }
        if (TextUtils.isEmpty(password)) {
            //password empty check
            Toast.makeText(this, "please enter password", Toast.LENGTH_SHORT).show();
            //stop this function
            return;
        }
        progressdialog.setMessage("Logging you in...");
        progressdialog.show();


        firebaseauth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    //this thing that happens after regular login
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressdialog.dismiss();


                        if (task.isSuccessful()) {

                            startActivity(new Intent(getApplicationContext(), first.class));
                        } else {
                            Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
