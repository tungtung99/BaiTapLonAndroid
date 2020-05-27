package com.example.tuvungtienganh;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtTaikhoan,txtMatkhau;
    Button btnLogin;
    TextView txtSignup;
    String ten,mk;
    DBHelperChuDe db;
    DBHelper data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelperChuDe(this);
        data = new DBHelper(this);
        init();
        controlbutton();
    }

    @Override
    protected void onStart() {
        super.onStart();
        db.openDB();
        data.openDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.closeDB();
        data.closeDB();
    }

    private void controlbutton() {
        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.layoutdangky);
                dialog.setTitle("Sign Up");
                final EditText edtaikhoan=dialog.findViewById(R.id.txtTaiKhoan2);
                final EditText edmatkhau=dialog.findViewById(R.id.txtMatKhau2);
                Button btnHuy=dialog.findViewById(R.id.btnHuy);
                Button btnDangky=dialog.findViewById(R.id.btnSignup);
                btnDangky.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ten = edtaikhoan.getText().toString().trim();
                        mk = edmatkhau.getText().toString().trim();

                        txtTaikhoan.setText(ten);
                        txtMatkhau.setText(mk);
                        Toast.makeText(MainActivity.this,"Sign Up Success",Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtTaikhoan.getText().length() != 0 && txtMatkhau.getText().length() !=0) {
                    if(txtTaikhoan.getText().toString().equals(ten) && txtMatkhau.getText().toString().equals(mk)){
                        Toast.makeText(MainActivity.this,"successful login",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,HomeApp.class);
                        startActivity(intent);
                    }
                    else if(txtTaikhoan.getText().toString().equals("tung") && txtMatkhau.getText().toString().equals("123")){
                        Toast.makeText(MainActivity.this,"successful login",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,HomeApp.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"You have entered the wrong account or password",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this,"Please enter the full information",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
        txtTaikhoan=findViewById(R.id.txtTaiKhoan);
        txtMatkhau=findViewById(R.id.txtMatKhau);
        btnLogin=findViewById(R.id.btnLogin);
        txtSignup=findViewById(R.id.txtSignup);
    }
}
