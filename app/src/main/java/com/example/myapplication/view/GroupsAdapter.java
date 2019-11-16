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

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.MyViewHolder>  {
    Context context;
    ArrayList<Group> groups;



    public GroupsAdapter(Context c, ArrayList<Group> g) {
        context = c;
       groups = g;

    }

    @NonNull
    @Override

    public GroupsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GroupsAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.groupscardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String groupname = groups.get(position).getName();
        holder.groupname.setText(groupname);

        Picasso.get().load(groups.get(position).getUrlImage()).into(holder.groupPic);

        holder.onAccepterClick(context);

    }


    @Override
    public int getItemCount() {
        return groups.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView groupname;
        CircleImageView groupPic;
        Button acceder ;


        public MyViewHolder(View itemView) {
            super(itemView);


            groupname = (TextView) itemView.findViewById(R.id.groupname);
            groupPic = (CircleImageView) itemView.findViewById(R.id.imageGroup);
            acceder = (Button) itemView.findViewById(R.id.acceder);


        }
        public void onAccepterClick(final Context context ) {
            acceder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, groupname.getText(), Toast.LENGTH_LONG).show();

                }
            });
        }







    }
}
