package com.example.android_nc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class MainAdmin extends AppCompatActivity {
    private final int PICK_IMAGE_REQUEST = 22;
    Timestamp timestamp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        DanhSachLoaiTruyenAdmin themloai = new DanhSachLoaiTruyenAdmin();
        fragmentTransaction.replace(R.id.container, themloai);
        fragmentTransaction.commit();

        BottomNavigationView navigation = findViewById(R.id.button_nav);
        navigation.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home: {
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        DanhSachLoaiTruyenAdmin themloai = new DanhSachLoaiTruyenAdmin();
                        fragmentTransaction.replace(R.id.container, themloai);
                        fragmentTransaction.commit();
                    }
                    break;
                    case R.id.navigation_sms: {
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        DanhSachTruyenAdmin themloai = new DanhSachTruyenAdmin();
                        fragmentTransaction.replace(R.id.container, themloai);
                        fragmentTransaction.commit();
                    }
                    break;
                    case R.id.navigation_notifications:
                        startActivity(new Intent(MainAdmin.this, themloaitruyen.class));
                        break;
                }
            }
        });
    }

    public void ThemTruyen(LoaiTruyen loaiTruyen,String click)
    {
        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
        if(click.equals("themtruyen"))
        {
            ThemTruyenFragment themtruyen = new ThemTruyenFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("loaitruyen",loaiTruyen);
            themtruyen.setArguments(bundle);
            fragmentTransaction1.replace(R.id.container,themtruyen);
            fragmentTransaction1.commit();
        }
        if(click.equals("danhsach"))
        {
            DanhSachTruyenTheoLoaiAdmin loaitruyen = new DanhSachTruyenTheoLoaiAdmin();
            Bundle bundle = new Bundle();
            bundle.putSerializable("loaitruyen",loaiTruyen);
            loaitruyen.setArguments(bundle);
            fragmentTransaction1.replace(R.id.container,loaitruyen);
            fragmentTransaction1.commit();
        }
    }

    public void goChitiet(Truyen truyen,String trangthai)
    {
        FragmentTransaction  fragmentTransaction=getSupportFragmentManager().beginTransaction();
        if(trangthai.equals("chitiet"))
        {
            Truyenchitiet updateuser = new Truyenchitiet();
            Bundle bundle = new Bundle();
            bundle.putSerializable("object",truyen);
            updateuser.setArguments(bundle);
            fragmentTransaction.replace(R.id.container,updateuser);
            fragmentTransaction.commit();
        }
        if(trangthai.equals("edit"))
        {
            UpdatetruyenFragment updateuser = new UpdatetruyenFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("object",truyen);
            updateuser.setArguments(bundle);
            fragmentTransaction.replace(R.id.container,updateuser);
            fragmentTransaction.commit();
        }
        if(trangthai.equals("xoa"))
        {
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("tbl_truyen");
            mDatabase.child(String.valueOf(truyen.getMa())).removeValue();

            DanhSachLoaiTruyenAdmin themloai = new DanhSachLoaiTruyenAdmin();
            fragmentTransaction.replace(R.id.container, themloai);
            fragmentTransaction.commit();
        }
    }
}