package com.example.myapplication.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Group;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ClubsAdapter extends RecyclerView.Adapter<ClubsAdapter.MyViewHolder>  {
    Context context;
    ArrayList<Group> groups;



    public ClubsAdapter(Context c, ArrayList<Group> g) {
        context = c;
       groups = g;

    }

    @NonNull
    @Override

    public ClubsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ClubsAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.clubscardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String clubname = groups.get(position).getName();
        holder.clubname.setText(clubname);

        Picasso.get().load(groups.get(position).getUrlImage()).into(holder.clubPic);

        holder.onRequestClick(context,position);

    }


    @Override
    public int getItemCount() {
        return groups.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView clubname;
        CircleImageView clubPic;
        Button request ;


        public MyViewHolder(View itemView) {
            super(itemView);


            clubname = (TextView) itemView.findViewById(R.id.clubname);
            clubPic = (CircleImageView) itemView.findViewById(R.id.imageClub);
            request = (Button) itemView.findViewById(R.id.request);


        }
        public void onRequestClick(final Context context , final int position ) {
            request.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, groups.get(position).toString(), Toast.LENGTH_LONG).show();

                }
            });
        }







    }
}
