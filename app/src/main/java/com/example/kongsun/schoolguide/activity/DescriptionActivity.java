package com.example.kongsun.schoolguide.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.kongsun.schoolguide.R;
import com.example.kongsun.schoolguide.singleton.MySingleTon;

public class DescriptionActivity extends AppCompatActivity {

    TextView kh_Name,en_Name,desc;
    NetworkImageView logoUrl,photoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Toolbar toolbar = (Toolbar) findViewById(R.id.desc_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        kh_Name = (TextView) findViewById(R.id.desc_txt_khName);
        String khName = getIntent().getStringExtra("Kh_Name");
        kh_Name.setText(khName);

        en_Name = (TextView) findViewById(R.id.desc_txt_enName);
        String enName = getIntent().getStringExtra("En_Name");
        en_Name.setText(enName);

        desc = (TextView) findViewById(R.id.txt_description);
        String Description = getIntent().getStringExtra("Description");
        desc.setText(Description);

        logoUrl = (NetworkImageView) findViewById(R.id.desc_img_logo);
        String LogoUrl = getIntent().getStringExtra("LogoUrl");
        logoUrl.setImageUrl(LogoUrl, MySingleTon.getInstance(this).getImageLoader());

        photoUrl = (NetworkImageView) findViewById(R.id.desc_img_background);
        String Photo = getIntent().getStringExtra("PhotoUrl");
        photoUrl.setImageUrl(Photo, MySingleTon.getInstance(this).getImageLoader());
    }
    //Back to home button


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
