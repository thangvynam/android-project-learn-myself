package android.dhkhtn.appchat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 4/10/2018.
 */

public class MessageAdapter extends  RecyclerView.Adapter<MessageAdapter.ViewHolder>{
    ArrayList<Message> dataMessage;
    private Context context;
    private final OnItemClickListener listener;
    private FirebaseStorage storage;
    private StorageReference storageRef;




    public MessageAdapter(ArrayList<Message> dataMessage, Context context, OnItemClickListener listener) {
        this.dataMessage = dataMessage;
        this.context = context;
        storage= FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://app-chat-500be.appspot.com");
        this.listener=listener;
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * . Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View row=inflater.inflate(R.layout.message,parent,false);
        return new ViewHolder(row);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override  instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Message message = dataMessage.get(position);
        holder.bind(message, listener);
        holder.txtNameUser.setText(message.getMessageUser());
        holder.txtTime.setText(DateFormat.format("dd-MM-yyyy HH:mm",message.getMessageTime()));
        //Picasso.with(context).load(message.getUrlImage()).into(holder.imgUser);
        String url = message.getUrlImage();
        Glide.with(context).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.imgUser) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                holder.imgUser.setImageDrawable(circularBitmapDrawable);
                holder.imgUser.setRotation(holder.imgUser.getRotation()+90);
            }
        });
        if(message.getMessageType().equals(MessageType.TEXT)){
            holder.imgPic.setVisibility(View.GONE);
            holder.txtMessage.setText(message.getMessageText());

        }else{
            holder.txtMessage.setVisibility(View.GONE);
            Picasso.with(context).load(message.getUrl_download()).into(holder.imgPic);

        }
        if (message.getMessageUser().equals(Global.getNameUser())){
            holder.txtNameUser.setTextColor(Color.BLACK);
        }
    }

    public void removeItem(int position){
        dataMessage.remove(position);
        notifyItemRemoved(position);

    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return dataMessage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtNameUser,txtTime,txtMessage;
        ImageView imgUser;
        ImageView imgPic;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNameUser=itemView.findViewById(R.id.txtNameUser);
            txtTime=itemView.findViewById(R.id.txtTime);
            txtMessage=itemView.findViewById(R.id.txtMessage);
            imgUser=itemView.findViewById(R.id.imgUser);
            imgPic=itemView.findViewById(R.id.imgPic);
            imgUser.setRotation(imgUser.getRotation()-90);
            Animation animation = AnimationUtils.loadAnimation(context,R.anim.scale_list);
            itemView.setAnimation(animation);


        }

        public void bind(final Message message, final OnItemClickListener listener) {
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Global.setId(message.getId());
                    removeItem(getAdapterPosition());
                    listener.onItemClick(message);
                    return true;
                }
            });

            imgUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Message ms = dataMessage.get(getAdapterPosition());
                    Intent intent= new Intent(context, DetailPerson.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putString(Global.USER_NAME, ms.getMessageUser());
                    mBundle.putString(Global.USER_IMAGE, ms.getUrlImage());
                    mBundle.putString(Global.BIRTHDAY, ms.getBirthday());
                    mBundle.putString(Global.PHONE_NUMBER, ms.getPhoneNumber());
                    mBundle.putString(Global.SEX, ms.getSex());
                    intent.putExtras(mBundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(intent);
                    //context.startActivity(intent);

                 }
            });
        }
    }


}