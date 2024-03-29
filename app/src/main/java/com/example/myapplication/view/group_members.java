package com.example.myapplication.view;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.DAO.GroupDao;
import com.example.myapplication.R;
import com.example.myapplication.firebaseHelper.FileUploader;
import com.example.myapplication.model.Admin;
import com.example.myapplication.model.Group;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

import de.hdodenhof.circleimageview.CircleImageView;

public class group_members extends Fragment {
    View view;
    Group group;
    static int x = 0;
    TextView adminNameTextView;
    CircleImageView adminCircleImageView;
    GroupDao groupDaoGI;
    FileUploader fileUploader;
    Bundle b;
    RecyclerView recyclerViewStudents;
    RecyclerView recyclerViewTeachers;
    MyAdapterStudents adapterStudents;
    MyAdapterTeachers adapterTeachers;

    public void init() {
        adminNameTextView = (TextView) this.view.findViewById(R.id.adminNameTextView);
        adminCircleImageView = (CircleImageView) this.view.findViewById(R.id.adminCircleImageView);
        b = getArguments();
        groupDaoGI = new GroupDao(view.getContext(),"gi2");
        group = groupDaoGI.getListsGroupDao();
        recyclerViewStudents = (RecyclerView)  this.view.findViewById(R.id.studentsRecycleView);
        recyclerViewStudents.setLayoutManager( new LinearLayoutManager(this.view.getContext()));
        recyclerViewTeachers = (RecyclerView) this.view.findViewById(R.id.teachersRecycleView);
        recyclerViewTeachers.setLayoutManager(new LinearLayoutManager(this.view.getContext()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.activity_group_members, container, false);
        init();
        load(adminNameTextView, adminCircleImageView);
    return this.view;
    }
    public synchronized void loadImages(String url, final CircleImageView circleImageView) {
        Toast.makeText(this.view.getContext(),"url " +  url , Toast.LENGTH_LONG).show();
        fileUploader = new FileUploader(url);
        fileUploader.getHttpsReference().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

               Glide.with(group_members.this.view).load(uri).into(circleImageView);
            }
        });
    }

    public synchronized void load(final TextView textview, final CircleImageView circleImageView){
        groupDaoGI.getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Admin admin = group.getAdmin();
                textview.setText(admin.getFirstName() + " " + admin.getLastName());
                loadImages(admin.getProfilePic(), circleImageView);
                group_members.this.adapterStudents = new MyAdapterStudents(group_members.this.view.getContext(),
                        group_members.this.group.getListStudents());
                group_members.this.adapterTeachers = new MyAdapterTeachers(group_members.this.view.getContext(),
                        group_members.this.group.getListTeacher());
                recyclerViewStudents.setAdapter(adapterStudents);
                recyclerViewTeachers.setAdapter(adapterTeachers);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



}