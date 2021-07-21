package com.app.BUNews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

public class DetailBerita extends AppCompatActivity {

    TextView judul ;
    TextView sumber;
    TextView waktu;
    TextView url;
    TextView author;
    TextView desk;
    ImageView imgBerita;
    WebView website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getDetailBerita();


    }

    private void getDetailBerita() {

        /*
        if(getIntent().hasExtra("urlBerita")     && getIntent().hasExtra("deskripsi") ) {
         */
        if(getIntent().hasExtra("deskripsi") && getIntent().hasExtra("title") && getIntent().hasExtra("time")
                && getIntent().hasExtra("source") && getIntent().hasExtra("author") && getIntent().hasExtra("image_url")){


            String desc = getIntent().getStringExtra("deskripsi");
            String title = getIntent().getStringExtra("title");
            String time = getIntent().getStringExtra("time");
            String source = getIntent().getStringExtra("source");
            String author = getIntent().getStringExtra("author");
            String image_url = getIntent().getStringExtra("image_url");
            String urlBerita = getIntent().getStringExtra("urlBerita");


            setDetailBerita(desc, title, time, source, author, image_url, urlBerita);
        }
        else{
            Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show();
        }

    }

    private void setDetailBerita(String desc, String title, String time, String source, String penulis, String image_url, String urlBerita) {
        desk = (TextView)findViewById(R.id.deskripsiBerita);
        judul = (TextView)findViewById(R.id.judulBerita);
        waktu = (TextView)findViewById(R.id.timePublished);
        sumber = (TextView)findViewById(R.id.publisherName);
        author = (TextView)findViewById(R.id.authorName);
        imgBerita = (ImageView) findViewById(R.id.ivDetailBerita);
        url = (TextView) findViewById(R.id.urlBerita) ;
        website = (WebView)findViewById(R.id.webBerita);

        desk.setText(desc);
        judul.setText(title);
        waktu.setText(time);
        sumber.setText(source);
        author.setText(penulis);
        Picasso.get().load(image_url).into(imgBerita);
        url.setText(urlBerita);

        website.loadUrl(urlBerita);

        Uri uri = Uri.parse(urlBerita);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }
}