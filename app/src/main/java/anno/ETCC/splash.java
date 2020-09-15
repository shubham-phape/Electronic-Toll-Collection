package anno.ETCC;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;

public class splash extends AppCompatActivity {
private GifImageView gggfff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        gggfff=(GifImageView)findViewById(R.id.gifwala);
        try
        {
            InputStream inputStream=getAssets().open("FORMSF14.gif");
            byte[] bytes= IOUtils.toByteArray(inputStream);
            gggfff.setBytes(bytes);
            gggfff.startAnimation();

        }
        catch (Exception e){}

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splash.this.startActivity(new Intent(splash.this,Login.class));
                splash.this.finish();;
            }
        },3000);
    }
}
