package com.example.ungdungdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.ungdungdoctruyen.database.*;

public class manHinhDangNhap extends AppCompatActivity {
    // tạo biến cho màn hình đăng nhập
    EditText edtTaiKhoan,edtMatKhau;
    Button btnDangNhap,btnDangKi;

    databaseDoctruyen databaseDoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_man_hinh_dang_nhap );

        anhxa();

        databaseDoctruyen = new databaseDoctruyen( this );

        btnDangKi.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(manHinhDangNhap.this,manHinhDangKi.class);
                startActivity( intent );

            }
        } );
        btnDangNhap.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tentaikhoan = edtTaiKhoan.getText().toString();
                String matkhau = edtMatKhau.getText().toString();
                // sử dụng con trỏ để lấy dữ liệu
                Cursor cursor = databaseDoctruyen.getData();

                // thực hiện vòng lặp lấy dữ liệu từ cursor với moviTonext() di chuyển tiếp
                while(cursor.moveToNext()){
                    //lấy dữ liệu và gắn vào biến, dữ liệu tài khoản ở ô 1 và mật khẩu ở ô 2
                    // ô 0 là idtaikhoan, ô 3 là email, ô 4 là phân quyền
                    String datatentaikhoan = cursor.getString( 1 );
                    String datamatkhau = cursor.getString( 2 );

                    // nếu tài khoản và mật khẩu nhập vào tuef bàn phím khớp với database
                    if(datatentaikhoan.equals( tentaikhoan )&& datamatkhau.equals( matkhau )){
                        // lấy dữ liệu phân quyền và id
                        int phanquyen = cursor.getInt( 4 );
                        int idd= cursor.getInt( 0 );
                        String email = cursor.getString( 3 );
                        String tentk = cursor.getString( 1 );

                        // gửi dữ liệu qua activity
                        Intent intent = new Intent(manHinhDangNhap.this,MainActivity.class);
                        intent.putExtra( "phanp" , phanquyen );
                        intent.putExtra( "idd",idd );
                        intent.putExtra( "email",email );
                        intent.putExtra( "tentaikhoan",tentk );

                        startActivity( intent );
                    }

                }
                // thực hiện trả cursor về đầu
                cursor.moveToFirst();
                // đóng khi không dùng
                cursor.close();
            }
        } );

        //

    }

    private void anhxa() {
        edtMatKhau = (EditText) findViewById( R.id.edittextMatKhau );
        edtTaiKhoan =(EditText) findViewById( R.id.edittextTaiKhoan );
        btnDangKi = (Button) findViewById( R.id.buttonDangKi );
        btnDangNhap =(Button) findViewById( R.id.buttonDangNhap );
    }


}