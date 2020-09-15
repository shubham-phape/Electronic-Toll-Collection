package anno.ETCC;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class signup extends AppCompatActivity {
    private Button buttonregister;
    private TextView tvLogin;
    private EditText etemail;
    private EditText etpassword;
    private ProgressDialog progressdialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!= null)
        {
            //directly start the profile activity
            finish();
            startActivity(new Intent(getApplicationContext(),first.class));
        }
        progressdialog = new ProgressDialog(this);
        buttonregister =(Button) findViewById(R.id.btregister);
        tvLogin = (TextView) findViewById(R.id.tvLogin);
        etemail =(EditText)   findViewById(R.id.etEmail);
        etpassword =(EditText) findViewById(R.id.etPassword);

        buttonregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUser();
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

    }
    private void RegisterUser() {

        String email= etemail.getText().toString().trim();
        String password = etpassword.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            //email check here
            Toast.makeText(this,"please enter email",Toast.LENGTH_SHORT).show();
            //stop this function
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            //password empty check
            Toast.makeText(this,"please enter password",Toast.LENGTH_SHORT).show();
            //stop this function
            return;
        }
        //user input is not enpty the
        progressdialog.setMessage("Registering you in...");
        progressdialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressdialog.dismiss();
                        if (task.isSuccessful()) {

                            Toast.makeText(signup.this, "Registration completed", Toast.LENGTH_SHORT).show();

                            finish();
                            startActivity(new Intent(getApplicationContext(),form.class));
                        }else{
                            Toast.makeText(signup.this, "Registration failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });


    }
}
