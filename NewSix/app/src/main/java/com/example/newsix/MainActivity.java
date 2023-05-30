package com.example.newsix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.AbsListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView re;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        init();
    }

    public void init(){
        re = findViewById(R.id.re);
        re.setLayoutManager(new LinearLayoutManager(this));
        getPermission();
    }
    String[] permissionList;
    public void getPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            permissionList = new String []{"android.permission.READ_CONTACTS"};
            ArrayList<String> list = new ArrayList<String>();
            for ( int i =0; i < permissionList.length; i ++){
                if (ActivityCompat. checkSelfPermission ( this , permissionList [i])
                        != PackageManager.PERMISSION_GRANTED ){
                    list.add(permissionList[i]);
                }
            }
            if (list.size()>0) {
                ActivityCompat.requestPermissions(this, list.toArray(new String[list.size()]), 1);
            }
            else{
                setData ();
            }
        }else {
            setData();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResult){
        super.onRequestPermissionsResult(requestCode,permissions,grantResult);
        if (requestCode == 1){
            for (int i = 0; i < permissions.length; i++) {
                if (permissions[i].equals("android.permission.READ_CONTACTS") && grantResult[i]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "申请成功", Toast.LENGTH_SHORT).show();
                    setData();
                }else {
                    Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private ConAdapter adapter;

    private void setData() {
        List<ContactInfo> contactInfos=getContact();
        adapter = new ConAdapter(MainActivity.this,contactInfos);
        re.setAdapter(adapter);
    }

    public List<ContactInfo> getContact(){
        List<ContactInfo> contactInfos = new ArrayList<>();
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        if (contactInfos!=null)contactInfos.clear();
        while (cursor.moveToNext()){
            @SuppressLint("Range") String id = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts._ID);

            )
        }
    }
}