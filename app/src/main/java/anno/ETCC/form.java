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

import java.util.Random;

public class form extends AppCompatActivity implements View.OnClickListener {
    private Spinner spinnercity;
    ArrayAdapter<CharSequence> adapter;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseauth;
    private EditText etname;
    private EditText phoneno;
    private EditText adharcarno;
    private RadioGroup radiotypegroup;
    private EditText address;
    private Button btnext;
    private String city;
    private RadioButton radiotype;
    public String typeofgender;
private EditText pincode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseauth =FirebaseAuth.getInstance();

        if(firebaseauth.getCurrentUser()== null)
        {
            finish();
            startActivity(new Intent(this,Login.class));
        }

        setContentView(R.layout.activity_form);
        spinnercity=(Spinner) findViewById(R.id.cityspinner);


        adapter=ArrayAdapter.createFromResource(this,R.array.citynames,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnercity.setAdapter(adapter);
        spinnercity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city= (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        radiotypegroup=(RadioGroup)findViewById(R.id.radiogroup);
        etname=(EditText)findViewById(R.id.etnamee);
        phoneno=(EditText)findViewById(R.id.phno);
        adharcarno=(EditText)findViewById(R.id.adharcrd);
        btnext=(Button)findViewById(R.id.next);
        pincode=(EditText)findViewById(R.id.pincode);
        radiotype=(RadioButton) findViewById(R.id.rbteacher);
        address=(EditText)findViewById(R.id.address);
        FirebaseUser user= firebaseauth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();



        btnext.setOnClickListener(this);

    }
    public void onClick(View v) {

        int selectedId;
        selectedId = radiotypegroup.getCheckedRadioButtonId();
        radiotype = (RadioButton) findViewById(selectedId);
        typeofgender = radiotype.getText().toString();
        if(btnext==v)
        {
            if(senduserInfo()==Boolean.TRUE);
            {
                finish();
                startActivity(new Intent(getApplicationContext(), form2.class));
            }
        }

    }
    private Boolean senduserInfo() {
        Boolean result = Boolean.TRUE;
        String username = etname.getText().toString().trim();
        String adhar = adharcarno.getText().toString().trim();
        String addresss = address.getText().toString().trim();
        String phone = phoneno.getText().toString().trim();
        String pin=pincode.getText().toString().trim();

        String male="male";
        String tag=uniquetagID();
        String female="female";
       String tak="";
        FirebaseUser user= firebaseauth.getCurrentUser();
        if(typeofgender.equals(male)) {
                  tak=male;

        }
        else if(typeofgender.equals(female)) {tak=female;

        }
else result=false;
        String zero="0";
        form1card f11 = new form1card(username, tak, phone, adhar, addresss, city,pin,zero,tag);
        databaseReference.child("Users").child(user.getUid()).setValue(f11);
        return result;
    }
    public String uniquetagID()
    {
        char[] chars1 = "ABCjsbdbDEF012GHIJKL345MNOPQR678STUVdfsd123544jukuij84477WXYZ9".toCharArray();
        StringBuilder sb1 = new StringBuilder();
        Random random1 = new Random();
        for (int i = 0; i < 6; i++)
        {
            char c1 = chars1[random1.nextInt(chars1.length)];
            sb1.append(c1);
        }
        return sb1.toString();
    }
}
