package anno.ETCC;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class adminlogin extends AppCompatActivity {
    private EditText etadminemail;
    private  EditText etpassadmin;
    private Button btadminlo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);


        etadminemail=(EditText)findViewById(R.id.adminusername);
        etpassadmin=(EditText)findViewById(R.id.adminpasswd);
        btadminlo=(Button)findViewById(R.id.btadminlogon);
        btadminlo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e=etadminemail.getText().toString();
                String p=etpassadmin.getText().toString();
                if(e.equals("admin") && p.equals("maihu")){

                finish();
                    startActivity(new Intent(getApplicationContext(),admin.class));
            }}
        });

    }
}
