package com.example.ungdungdoctruyen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ungdungdoctruyen.modle.*;
import com.example.ungdungdoctruyen.database.databaseDoctruyen;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class manHinhDangKi extends AppCompatActivity {

    EditText edtDKTaiKhoan,edtDKMatKhau,edtDKEmail;
    Button btnDKDangKi,btnDKDangNhap;

    databaseDoctruyen databaseDoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_dang_ki);

        databaseDoctruyen = new databaseDoctruyen( this );
        anhxa();


        btnDKDangKi.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taikhoan = edtDKTaiKhoan.getText().toString();
                String matkhau = edtDKMatKhau.getText().toString();
                String email = edtDKEmail.getText().toString();

                TaiKhoan taiKhoan1 = CreateTaiKhoan();
                if(taikhoan.equals( "" )|| matkhau.equals( "" )|| email.equals( "" )){
                    Log.e("thông báo:","Chưa nhập đày đủ thông tin");
                }else {
                    databaseDoctruyen.AddTaiKhoan(taiKhoan1);
                    Toast.makeText( manHinhDangKi.this, "thêm tài khoản thành công", Toast.LENGTH_SHORT ).show();
                }
            }
        } );

        btnDKDangNhap.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

    }



    // phương thức tạo tài khoản
    private TaiKhoan CreateTaiKhoan(){
        String taikhoan = edtDKTaiKhoan.getText().toString();
        String matkhau = edtDKMatKhau.getText().toString();
        String email = edtDKEmail.getText().toString();
        int phanpuyen =1;
        TaiKhoan tk = new TaiKhoan( taikhoan,matkhau,email,phanpuyen );

        return tk;
    }

    private void anhxa() {
        edtDKTaiKhoan = (EditText) findViewById( R.id.dkTaiKhoan );
        edtDKMatKhau = (EditText) findViewById( R.id.dkMatKhau );
        edtDKEmail=(EditText) findViewById( R.id.dkEmail );
        btnDKDangNhap= (Button) findViewById( R.id.dkDangNhap );
        btnDKDangKi = (Button) findViewById( R.id.dkDangki );
    }
}