package anno.ETCC;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class recharge extends AppCompatActivity {
  private Button credit;
    private Button Netbt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

        credit=(Button)findViewById(R.id.creditbt);
        Netbt=(Button)findViewById(R.id.netbt);
        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i1= new Intent(getApplicationContext(), credit.class);
                finish();
                int m=10;
                i1.putExtra("Value",m);
                startActivity(i1);
            }
        });
        Netbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2= new Intent(getApplicationContext(), netbanking.class);
                finish();
                startActivity(i2);

            }
        });



    }
}
