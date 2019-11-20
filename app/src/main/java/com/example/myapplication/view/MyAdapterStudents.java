package com.example.myapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Student;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapterStudents extends RecyclerView.Adapter<MyAdapterStudents.MyViewHolder> {
    private Context context;
    List<Student> students;
    public MyAdapterStudents(Context context , List<Student> students){
        this.students = students;
        this.context = context;

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.studentscardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.userName.setText(students.get(position).getFirstName() + " " + students.get(position).getLastName());
        Picasso.get().load(students.get(position).getProfilePic()).into(holder.profilePic);

        holder.buttonPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                group.selectedFragment = new group_posts();
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView userName,firstName,lastName;
        CircleImageView profilePic;
        Button buttonPosts;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.userName);
            profilePic = (CircleImageView) itemView.findViewById(R.id.profilePicUrl);
            buttonPosts = (Button) itemView.findViewById(R.id.buttonPosts);
        }
    }



}
