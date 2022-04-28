package sv.edu.udb.guia6ejercicio1.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import sv.edu.udb.guia6ejercicio1.R;

public class UsersListViewModel extends ArrayAdapter<GitUser> {
    private int resource;
    public UsersListViewModel(@NonNull Context context, int resource, List<GitUser> data) {
        super(context, resource, data);
        this.resource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listViewItem = convertView;
        if(listViewItem == null)
            listViewItem = LayoutInflater.from(getContext()).inflate(resource, parent,false );

        CircleImageView imageViewUser = listViewItem.findViewById(R.id.imageViewAvatar);
        TextView textViewUser = listViewItem.findViewById(R.id.textViewUserName);
        textViewUser.setText(getItem(position).username);

        try {
            URL url = new URL(getItem(position).avatarUrl);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
            imageViewUser.setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return listViewItem;
    }
}

