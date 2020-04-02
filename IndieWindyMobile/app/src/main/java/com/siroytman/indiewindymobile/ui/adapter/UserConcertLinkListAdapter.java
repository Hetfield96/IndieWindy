package com.siroytman.indiewindymobile.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.ConcertController;
import com.siroytman.indiewindymobile.controller.AppController;
import com.siroytman.indiewindymobile.interfaces.ILinkAdd;
import com.siroytman.indiewindymobile.model.Concert;
import com.siroytman.indiewindymobile.model.UserConcertLink;
import com.siroytman.indiewindymobile.services.IconChanger;
import com.siroytman.indiewindymobile.ui.activity.ConcertActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UserConcertLinkListAdapter extends ArrayAdapter<UserConcertLink> {
    public static final String TAG = "UserConcertLinkListAdapter";
    private ConcertController concertController;
    private Context context;
    private List<UserConcertLink> linksList;

    public UserConcertLinkListAdapter(@NonNull Context context, int resource, @NonNull List<UserConcertLink> linksList) {
        super(context, resource, linksList);
        this.context = context;
        this.linksList = linksList;
        this.concertController = ConcertController.getInstance();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        final UserConcertLink concertLink = linksList.get(position);

        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.concert_list_item, null);

            viewHolder = new ViewHolder(convertView, concertLink);

            convertView.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        return convertView;
    }


    // Single element
    public class ViewHolder  implements View.OnClickListener, ILinkAdd<Concert> {
        public static final String TAG = "UserConcertLinkAdapt.VH";
        private UserConcertLink concertLink;

        private ImageView concertPhotoView;
        private TextView concertNameView;
        private TextView concertDateView;
        private TextView concertDescriptionView;
        private ImageView concertAddButton;

        public ViewHolder(View convertView, final UserConcertLink concertLink) {
            this.concertLink = concertLink;

            concertPhotoView = convertView.findViewById(R.id.concert_list_item__photo);
            concertNameView = convertView.findViewById(R.id.concert_list_item__name);
            concertDateView = convertView.findViewById(R.id.concert_list_item__date);
            concertDescriptionView = convertView.findViewById(R.id.concert_list_item__description);
            concertAddButton = convertView.findViewById(R.id.concert_list_item___add_button);

            Concert concert = concertLink.getConcert();
            Glide.with(convertView).load(concert.getImageUrl()).into(concertPhotoView);
            concertNameView.setText(concert.getName());
            concertDateView.setText(concert.getDateString());
            concertDescriptionView.setText(concert.getDescription());
            IconChanger.setAddStateIcon(concertLink, concertAddButton);

            convertView.setOnClickListener(this);
            concertAddButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Concert concert = concertLink.getConcert();

            Log.d(TAG, "onClick concert: " + concert.getName());

            switch (v.getId()) {
                case R.id.concert_list_item___add_button:
                    Log.d(TAG, "onClick concert_add_button: " + concert.getName());
                    if (concertLink.isEmpty()) {
                        concertController.addUserConcertLink(this);
                    } else{
                        concertController.removeUserConcertLink(this);
                    }
                    return;
            }

            Log.d(TAG, "to page of concert: " + concert.getName());
            Intent intent = new Intent(context, ConcertActivity.class);
            intent.putExtra(UserConcertLink.class.getSimpleName(), concertLink);
            context.startActivity(intent);
        }

        @Override
        public Concert getItem() {
            return concertLink.getConcert();
        }

        @Override
        public void removed() {
            concertLink.makeEmpty();

            IconChanger.setIconAdd(concertAddButton);
        }

        @Override
        public void added() {
            concertLink.setAppUserId(AppController.user.getId());
            concertLink.setConcertId(concertLink.getConcert().getId());

            IconChanger.setIconCheck(concertAddButton);
        }
    }
}
