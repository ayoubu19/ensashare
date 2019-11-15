package com.example.myapplication.view;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.DAO.GroupDao;
import com.example.myapplication.R;
import com.example.myapplication.model.Group;
import com.example.myapplication.model.Student;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragmentStudent extends Fragment {
private TextView nomComplet ;
private CircleImageView imageProfile ;
private GroupDao groupDao;
private Group group;
private FirebaseAuth userAuth ;
    private FirebaseUser currentUser ;
    RecyclerView recyclerView;
    List<Group> listGroup;
    GroupsAdapter adapter;

public void init(){
    userAuth = FirebaseAuth.getInstance();
    currentUser = userAuth.getCurrentUser();

}

    public HomeFragmentStudent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

       Context context= getActivity().getApplicationContext();
        nomComplet = (TextView) view.findViewById(R.id.nomComplet);
        imageProfile = (CircleImageView) view.findViewById(R.id.imageGroup);
        recyclerView = (RecyclerView) view.findViewById(R.id.groupRecycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);



        init();

        student_profile activity = (student_profile) getActivity();


        Student student = activity.getMyData();
        String nomprenom =student.getFirstName()+" "+student.getLastName();
        groupDao = new GroupDao();
        nomComplet.setText(nomprenom);

      // Picasso.get().load(currentUser.getPhotoUrl()).into( imageProfile );

        Glide.with(context).load(student.getProfilePic()).into(imageProfile);


       // Toast.makeText(context,currentUser.getPhotoUrl().toString(),Toast.LENGTH_SHORT).show();

        HashMap<String,Object> groups = student.getGroups();

        List <Object> list =  Collections.list(Collections.enumeration(groups.values()));

        groupDao.getGroups(list , context);


     /*   Iterator iterator = list.iterator();
        iterator.next();
        while(iterator.hasNext()) {
            if (!iterator.next().toString().equals("entry")){

                  String level =  iterator.next().toString();
                 groupDao = new GroupDao(context ,level.toLowerCase());
                 Toast.makeText(getContext(), level, Toast.LENGTH_LONG).show();
                 HomeFragment.this.group = groupDao.getGroupDao();
                 load();

              //  listGroup.add(group);



            }


        }*/

          // String nameGroup = groupName.toString().toLowerCase();



       /* if (!nameGroup.equals("entry")){

                GroupDao groupDao = new GroupDao(context ,nameGroup);
                Group group = groupDao.getGroupDao();
                listGroup.add(group);

                Toast.makeText(context,listGroup.toString(), Toast.LENGTH_SHORT).show();

            }*/




     //   adapter = new GroupsAdapter(context,listGroup);
       // recyclerView.setAdapter(adapter);


        // Inflate the layout for this fragment

        return view;

    }

    public  void load(){
       groupDao.getRef().addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {
           }
       });


    }

}
