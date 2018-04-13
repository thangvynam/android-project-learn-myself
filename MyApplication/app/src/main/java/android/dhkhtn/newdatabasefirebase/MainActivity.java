package android.dhkhtn.newdatabasefirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    DatabaseReference mData;
    Button btn1,btn2;
    TextView txtName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName= (TextView) findViewById(R.id.txtName);
        btn1= (Button) findViewById(R.id.btn1);
        btn2= (Button) findViewById(R.id.btn2);
        mData = FirebaseDatabase.getInstance().getReference();
//        mData.child("KhoaHoc").setValue("ập trình di động");
//        mData.child("Ngôn ngữ lập trình").push().setValue("Android");
//        mData.child("Ngôn ngữ lập trình").push().setValue("iOs");
//        mData.child("Ngôn ngữ lập trình").push().setValue("Javascript");
//        mData.child("Ngôn ngữ lập trình").push().setValue("C#");
//        mData.child("Phương tiện").push().setValue(new PhuongTien("Xe đạp",2));
//        mData.child("Phương tiện").push().setValue(new PhuongTien("Xe hơi",4));
//        mData.child("Phương tiện").push().setValue(new PhuongTien("Xe tải",4));
        mData.child("Phương tiện").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                PhuongTien pt= (PhuongTien) dataSnapshot.getValue(PhuongTien.class);
                Toast.makeText(MainActivity.this, pt.ten, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        mData.child("Ngôn ngữ lập trình").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                Toast.makeText(MainActivity.this, dataSnapshot.getValue().toString()+"\n", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

//        mData.child("KhoaHoc").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                txtName.setText(dataSnapshot.getValue().toString());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mData.child("KhoaHoc").setValue("Android");
//            }
//        });
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mData.child("KhoaHoc").setValue("iOs");
//            }
//        });



        // nhận data object

        /*// TH1
        mData.child("HoTen").setValue("Thang Vỹ Nam", new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError==null){
                    Toast.makeText(MainActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Lưu thất bại", Toast.LENGTH_SHORT).show();
                }


            }
        });
        //TH2
        SinhVien sv= new SinhVien("Sơn tùng",01);
        mData.child("SinhVien").setValue(sv);
        //TH3
        Map<String,Integer> myMap = new HashMap<String,Integer>();
        myMap.put("Xe máy",123456);
        mData.child("Phương tiện").setValue(myMap);
        // TH4
        SinhVien sv2= new SinhVien("Sơn tùng mtp",02);
        SinhVien sv3= new SinhVien("Sơobin hoàng sơn",03);
        mData.child("Học viên").push().setValue(sv2);
        mData.child("Học viên").push().setValue(sv3);

        // bắt sự kiện setvalue
        mData.child("course").setValue("Android Firebasae", new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError==null) {
                    Toast.makeText(MainActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(MainActivity.this, "Lưu thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }
}
