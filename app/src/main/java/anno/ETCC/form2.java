package anno.ETCC;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class form2 extends AppCompatActivity implements View.OnClickListener {
    private Spinner spinnerregion;
    ArrayAdapter<CharSequence> adapter;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseauth;
    private RadioButton radiotype;
    private RadioGroup radiotypegroup;
    private EditText vehicleno ;
    private String region;
    Button proceed;
    private String typeoftrans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2);
        firebaseauth =FirebaseAuth.getInstance();

        if(firebaseauth.getCurrentUser()== null)
        {
            finish();
            startActivity(new Intent(this,Login.class));
        }

        setContentView(R.layout.activity_form2);
        spinnerregion=(Spinner) findViewById(R.id.regionspinner);


        adapter=ArrayAdapter.createFromResource(this,R.array.region,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerregion.setAdapter(adapter);
        spinnerregion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                region= (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        radiotypegroup=(RadioGroup)findViewById(R.id.radiogroup123);
        vehicleno=(EditText)findViewById(R.id.vehno);
        proceed=(Button)findViewById(R.id.proced);
        FirebaseUser user= firebaseauth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        proceed.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        int selectedId;
        selectedId = radiotypegroup.getCheckedRadioButtonId();
        radiotype = (RadioButton) findViewById(selectedId);
        typeoftrans = radiotype.getText().toString();
        if(proceed==v)
        {
            if(senduserInfo()==Boolean.TRUE);
            {
                finish();
                startActivity(new Intent(getApplicationContext(), first.class));
            }
        }

    }
    private Boolean senduserInfo() {
        Boolean result = Boolean.TRUE;
        String vehno = vehicleno.getText().toString().trim();
        String privatetrans="Private Transport";
        String publictrans="Public Transport";
        String tak="";
        FirebaseUser user= firebaseauth.getCurrentUser();
        if(typeoftrans.equals(privatetrans)) {
            tak=privatetrans;

        }
        else if(typeoftrans.equals(publictrans)) {tak=publictrans;

        }
        else result=false;
        int zero=0;

        formcard2 f211 = new formcard2(vehno,tak,region);
        databaseReference.child(user.getUid()).child("Vehicle").setValue(f211);
        return result;
    }

}
