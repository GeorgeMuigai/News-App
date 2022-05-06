package com.gdev.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class NewsDetails extends AppCompatActivity {
    String title, content, url, urlToImage, desc;
    ImageView img_passed;
    TextView txt_title_passed, txt_content_passed, txt_desc_passed;
    Button btn_read_more;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        // getting strings from NewsAdapter
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        urlToImage = getIntent().getStringExtra("image");
        content = getIntent().getStringExtra("content");
        desc = getIntent().getStringExtra("desc");

        img_passed = findViewById(R.id.img_passed);
        txt_title_passed = findViewById(R.id.txt_title_passed);
        txt_content_passed = findViewById(R.id.txt_content_passed);
        txt_desc_passed = findViewById(R.id.txt_desc_passed);
        btn_read_more = findViewById(R.id.btn_read_more);

        // setting the imageview resource
        Picasso.get().load(urlToImage).into(img_passed);

        // setting text to TextViews
        txt_title_passed.setText(title);
        txt_content_passed.setText(content);
        txt_desc_passed.setText(desc);

        // adding click listener on the button
        btn_read_more.setOnClickListener(View ->{
            // opening the news in a web browser
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        });
    }
}