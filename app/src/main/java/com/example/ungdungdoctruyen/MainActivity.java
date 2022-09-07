package com.example.ungdungdoctruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ungdungdoctruyen.modle.Truyen;
import com.example.ungdungdoctruyen.adapter.adapterTruyen;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;
import com.example.ungdungdoctruyen.database.databaseDoctruyen;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listView, listViewNew,listViewThongTin;
    DrawerLayout drawerLayout;

    String email;
    String tentaikhoan;

    ArrayList<Truyen> TruyenArraylist;
    adapterTruyen adapterTruyen;
    databaseDoctruyen databaseDoctruyen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        databaseDoctruyen = new databaseDoctruyen( this );

        Intent intentpq = getIntent();
        int i = intentpq.getIntExtra( "phang", 0 );
        int idd = intentpq.getIntExtra( "idd", 0);
        email = intentpq.getStringExtra( "email" );
        tentaikhoan = intentpq.getStringExtra( "tentaikhoan" );


        anhxa();
        ActionBar();
        ActionViewFlipper();

        // tao su kien cho listviewnew
        listViewNew.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,ManNoiDung.class);
                String tent = TruyenArraylist.get( i ).getTenTruyen();
                String noidungt = TruyenArraylist.get( i ).getNoiDung();
                intent.putExtra( "tentruyen",tent );
                intent.putExtra( "noidung" ,noidungt);
                startActivity( intent );
            }
        } );
    }

    private void ActionBar() {
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        toolbar.setNavigationIcon( android.R.drawable.ic_menu_sort_by_size );
        toolbar.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer( GravityCompat.START);
            }
        } );
    }

    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add( "https://toplist.vn/images/800px/rua-va-tho-230179.jpg" );
        mangquangcao.add( "https://toplist.vn/images/800px/deo-chuong-cho-meo-230180.jpg" );
        mangquangcao.add( "https://toplist.vn/images/800px/cu-cai-trang-230181.jpg" );
        mangquangcao.add( "https://toplist.vn/images/800px/de-den-va-de-trang-230182.jpg" );


        for (int i=0;i<mangquangcao.size();i++){
            ImageView imageView = new ImageView( getApplicationContext() );
            Picasso.get().load( mangquangcao.get( i ) ).into( imageView );
            imageView.setScaleType( ImageView.ScaleType.FIT_XY );
            viewFlipper.addView( imageView );
        }
        viewFlipper.setFlipInterval( 4000 );

        viewFlipper.setAutoStart( true );
        Animation animation_slide_in = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.slide_in_right );
        Animation animation_slide_out = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.slide_out_right );

        viewFlipper.setInAnimation( animation_slide_in );
        viewFlipper.setOutAnimation(animation_slide_out  );
    }

    private void anhxa() {
        toolbar = (Toolbar) findViewById( R.id.toolbarmanhinhchinh );
        viewFlipper =(ViewFlipper) findViewById( R.id.viewflipper );
        navigationView= (NavigationView) findViewById( R.id.navigationview );
        listView = (ListView) findViewById( R.id.listviewmanhinhchinh );
        listViewNew =(ListView) findViewById( R.id.listviewnew );
        listViewThongTin = (ListView) findViewById( R.id.listviewthongtin );
        drawerLayout = (DrawerLayout) findViewById( R.id.drawerlayout );

        TruyenArraylist = new ArrayList<>();
        Cursor cursor1 = databaseDoctruyen.getData1();
        while (cursor1.moveToNext()){
            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString( 1 );
            String noidung = cursor1.getString( 2 );
            String anh = cursor1.getString( 3 );
            int id_tk = cursor1.getInt( 4 );
            TruyenArraylist.add( new Truyen( id,tentruyen,noidung,anh,id_tk ) );
            adapterTruyen = new adapterTruyen(getApplicationContext(),TruyenArraylist);
            listViewNew.setAdapter( adapterTruyen );
        }
        cursor1.moveToFirst();
        cursor1.close();
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate( R.menu.mymenu,menu );
        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu1:
                Intent intent = new Intent(MainActivity.this,manhinhtimkiem.class);
                startActivity( intent);
                break;
            default:
                break;

        }

        return super.onOptionsItemSelected( item );
    }
}