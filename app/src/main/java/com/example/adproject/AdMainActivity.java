//  right click your projects root directory and select Git > Add. This will add all your project files to your Git repository.
//  right click the project name again and this time select Git > Commit Directory.
//  right click the project name, select Git > Repository > Push.

package com.example.adproject;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdMainActivity extends AppCompatActivity {
    // Remove the below line after defining your own ad unit ID.
    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";

    ViewPager viewPager;
    CustomSwipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_main);

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        // shows the help message
        //Toast toast=Toast.makeText(this, "Başlat tuşuna basarak başlayabilirsiniz. Başladıktan sonra dinozorların üstüne uzun basarak gerçek adını ve özelliklerini görebilirsiniz.", Toast.LENGTH_LONG).show();
        Toast toast=Toast.makeText(this, "Başlat tuşuna basarak başlayabilirsiniz. Başladıktan sonra dinozorların üstüne uzun basarak gerçek adını ve özelliklerini görebilirsiniz.",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 300);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
     }

    // define button
    public void klikle(View v)
    {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new CustomSwipeAdapter(this);
        viewPager.setAdapter(adapter);
        // below is not necessary because it is made invisible right after
        ((Button) v).setText(" Başa Dön ");
        View b = findViewById(R.id.button);
        b.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
         getMenuInflater().inflate(R.menu.menu_ad_main, menu);
         return true;
     }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
