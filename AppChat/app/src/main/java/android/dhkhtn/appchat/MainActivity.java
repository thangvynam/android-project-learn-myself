package android.dhkhtn.appchat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import hani.momanii.supernova_emoji_library.Actions.EmojIconActions;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;

import static android.dhkhtn.appchat.R.id.edtNickName;
import static android.dhkhtn.appchat.R.id.edtPassword;
import static android.dhkhtn.appchat.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {

    private static final int SIGN_IN_REQUEST_CODE = 1000;
    private static final int GALLERY_PICK = 1;
    ImageButton btnSend;
    ImageView imgIcon,imgAdd;
    EmojiconEditText edtText;
    EmojIconActions emojIconActions;
    RecyclerView lstMessage;
    LinearLayout activity_main;
    ScrollView scrView;
    MessageAdapter aDapter;
    private DatabaseReference mDatabase;
    ArrayList<Message> messages;
    int notificationId;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    String s="";

    BroadcastReceiver receiverWifi=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager=
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivityManager.getActiveNetworkInfo()!=null){
                edtText.setEnabled(true);
                btnSend.setEnabled(true);
                imgIcon.setEnabled(true);
            }
            else
            {
                edtText.setHint(getResources().getString(R.string.wifi));
                edtText.setEnabled(false);
                btnSend.setEnabled(false);
                imgIcon.setEnabled(false);
            }
        }
    };

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        Global.setNotification(true);
        if(receiverWifi!=null)
            unregisterReceiver(receiverWifi);
    }
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter=new 	IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiverWifi, intentFilter);

    }
    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_PICK && resultCode == RESULT_OK){
            Calendar calendar = Calendar.getInstance();
            final String nameUser =readFile("nameUser.txt");
            final String urlImage =readFile("imageUser.txt");
            final String birthday =readFile("birthday.txt");
            final String phoneNumber =readFile("phoneNumber.txt");
            final String sex =readFile("sex.txt");

            Uri uri = data.getData();
            StorageReference mountainsRef = storageRef.child(nameUser +calendar.getTimeInMillis());
            mountainsRef.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if(task.isSuccessful()){
                        String download_url =task.getResult().getDownloadUrl().toString();
                        String id = mDatabase.push().getKey();
                        mDatabase.child(id).setValue(new Message(id,edtText.getText().toString(), nameUser, urlImage,MessageType.IMAGE,download_url,birthday,sex,phoneNumber));
                        edtText.setText("");
                        edtText.requestFocus();

                    }
                }
            });

        }
    }


    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(s.equals("NOT_BACK")){
            Intent intent = new Intent(this, SignIn.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.mnuMembers) {
            ArrayList<ItemMembers> data = new ArrayList<>();
            for(Message ms : messages){
                data.add(new ItemMembers(ms.getMessageUser(),ms.getUrlImage()));
            }
            Intent intent = new Intent(this,Members.class);
            intent.putExtra(Global.ARRAY_NAME,data);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);

        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Global.showLogo){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    final Intent mainIntent = new Intent(MainActivity.this, BrandLogo.class);
                    if (!MainActivity.this.isFinishing()) {
                        MainActivity.this.startActivity(mainIntent);
                        MainActivity.this.finish(); }
                }
            },0);
        }


        addControls();
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {

            //startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build(),SIGN_IN_REQUEST_CODE);
            Intent intent = new Intent(MainActivity.this, SignIn.class);
            this.startActivityForResult(intent, SIGN_IN_REQUEST_CODE);
            overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
        } else {
            displayChatMessages();
            //lstMessage.setSelection(messages.size());
        }

        addEvents();

        Global.setNotification(false);
        NotificationManager mNotifyMgr1 =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr1.cancel(1);
        Intent intent1 = getIntent();
        s = intent1.getStringExtra("NOT_BACK");
        if(s==null){
            s="";
        }

    }



    private String readFile(String file){
        String text ="";
        try{
            FileInputStream fileInputStream = openFileInput(file);
            int size=fileInputStream.available();
            byte []buffer = new byte[size];
            fileInputStream.read(buffer);
            fileInputStream.close();
            text=new String(buffer);
        }catch (Exception e){
            e.printStackTrace();
        }
        return text;
    }
    private void addEvents() {
        final String nameUser =readFile("nameUser.txt");
        final String urlImage =readFile("imageUser.txt");
        final String birthday =readFile("birthday.txt");
        final String phoneNumber =readFile("phoneNumber.txt");
        final String sex =readFile("sex.txt");
        edtText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(edtText.getText().toString().length()==0){
                    imgAdd.setVisibility(View.VISIBLE);
                }else{
                    imgAdd.setVisibility(View.GONE);
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = mDatabase.push().getKey();

                try {
                    mDatabase.child(id).setValue(new Message(id,Encryption.encrypt(edtText.getText().toString(),Encryption.AES), nameUser, urlImage,MessageType.TEXT,"",birthday,sex,phoneNumber));
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }
                edtText.setText("");
                edtText.requestFocus();

            }
        });
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence options[] = new CharSequence[] {getString(R.string.choosePhoto),getString(R.string.cancel)};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(false);
                builder.setTitle(getString(R.string.options));
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0: chooseImage();break;

                        }
                    }
                });
                builder.show();
            }
        });



    }


    private void chooseImage() {
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(galleryIntent,"SELECT IMAGE"),GALLERY_PICK);
    }

    private void addControls() {
        activity_main = (LinearLayout) findViewById(R.id.activity_main);
        imgIcon = findViewById(R.id.imgIcon);
        imgAdd = findViewById(R.id.imgAdd);
        btnSend = findViewById(R.id.btnSend);
        edtText = findViewById(R.id.editText);
        lstMessage = findViewById(R.id.lstMessage);

        lstMessage.setHasFixedSize(true); // tối ưu hóa
        lstMessage.setItemViewCacheSize(20);
        lstMessage.setDrawingCacheEnabled(true);
        lstMessage.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        scrView = findViewById(R.id.scrView);

        emojIconActions = new EmojIconActions(getApplicationContext(), activity_main,edtText,imgIcon);
        emojIconActions.ShowEmojIcon();;

        LinearLayoutManager linearLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        lstMessage.setLayoutManager(linearLayout);
        lstMessage.setItemAnimator(new DefaultItemAnimator());
        mDatabase = FirebaseDatabase.getInstance().getReference("data");

        storage= FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://app-chat-500be.appspot.com");


    }

    private void displayChatMessages() {
        final String nameUser =readFile("nameUser.txt");
        Global.setNameUser(nameUser);
        messages = new ArrayList<Message>();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                messages.clear();
                try {
                    showData(dataSnapshot);
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                        } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                }
                if(messages.size()!=0){
                    Message pt= messages.get(messages.size()-1);
                    lstMessage.scrollToPosition(messages.size()-1);
                    if(Global.isNotification()) {
                        if (!pt.getMessageUser().equals(nameUser)) {
                            String notify;
                            if (pt.getMessageType().equals(MessageType.IMAGE)) {
                                notify = pt.getMessageUser() + getString(R.string.sendPhoto);
                            } else {
                                notify = pt.getMessageUser() + " : " + pt.getMessageText();
                            }
                            NotificationCompat.Builder mbuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(MainActivity.this).
                                    setSmallIcon(R.drawable.ic_alarm_black_24dp).
                                    setContentTitle(notify).
                                    setContentText(DateFormat.format("dd-MM-yyyy (HH:mm)", pt.getMessageTime()));

                            Intent resultIntent = new Intent(MainActivity.this, MainActivity.class);

                            PendingIntent resultPendingIntent = PendingIntent.getActivity(
                                    MainActivity.this,
                                    0,
                                    resultIntent, PendingIntent.FLAG_UPDATE_CURRENT
                            );

                            mbuilder.setContentIntent(resultPendingIntent);
                            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                            mbuilder.setSound(uri);

                            notificationId = 1;
                            NotificationManager mNotifyMgr =
                                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            // Builds the notification and issues it.
                            mNotifyMgr.notify(notificationId, mbuilder.build());

                        }
                    }
                }


                aDapter = new MessageAdapter(messages, getApplicationContext(), new OnItemClickListener() {
                    @Override
                    public void onItemClick(Message message) {
                        deleteMessage(Global.getId());
                    }
                });
                lstMessage.setAdapter(aDapter);
                aDapter.notifyDataSetChanged();
                lstMessage.scrollToPosition(messages.size()-1);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showData(DataSnapshot dataSnapshot) throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            Message ms=ds.getValue(Message.class);
            if(ms.getMessageType()==MessageType.TEXT) {
                ms.setMessageText(Encryption.decrypt(ms.getMessageText(), Encryption.AES));
            }
            messages.add(ms);
        }
    }

    private void deleteMessage(String id) {
       DatabaseReference mDatabaseTemp = FirebaseDatabase.getInstance().getReference("data").child(id);
        mDatabaseTemp.removeValue();
        Toast.makeText(this,getString(R.string.deleteSuccessfull), Toast.LENGTH_SHORT).show();
    }


}
