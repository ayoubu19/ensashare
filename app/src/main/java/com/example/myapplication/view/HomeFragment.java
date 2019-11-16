package com.example.myapplication.view;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.DAO.GroupDao;
import com.example.myapplication.DAO.QuoteDao;
import com.example.myapplication.R;
import com.example.myapplication.model.Group;
import com.example.myapplication.model.Quote;
import com.example.myapplication.model.Student;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
private TextView nomComplet ;
private CircleImageView imageProfile ;
private GroupDao groupDao;
private Group group;
private FirebaseAuth userAuth ;
private FirebaseUser currentUser ;
private Quote quot ;
private QuoteDao quoteDao ;
Context context ;
View view;
TextView quote ;
RecyclerView recyclerView;
ArrayList<Group> listGroup;
GroupsAdapter adapter;


public void init(){
    userAuth = FirebaseAuth.getInstance();
    currentUser = userAuth.getCurrentUser();
    quoteDao = new QuoteDao(view.getContext());
    quoteDao.getQuote();
    listGroup = new ArrayList<>() ;

}

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         this.view = inflater.inflate(R.layout.fragment_home, container, false);
        this.context = getActivity().getApplicationContext();
        quote = (TextView)view.findViewById(R.id.quote);
        nomComplet = (TextView) view.findViewById(R.id.nomComplet);
        imageProfile = (CircleImageView) view.findViewById(R.id.imageGroup);
        recyclerView = (RecyclerView) view.findViewById(R.id.groupRecycle);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        init();
        load();

        admin_Profile activity = (admin_Profile) getActivity();
        Student student = activity.getMyData();
        String nomprenom =student.getFirstName()+" "+student.getLastName();

        groupDao = new GroupDao();
        nomComplet.setText(nomprenom);



        Glide.with(context).load(student.getProfilePic()).into(imageProfile);




        HashMap<String,Object> groups = student.getGroups();

        List <Object> list =  Collections.list(Collections.enumeration(groups.values()));
        Toast.makeText(context,list.toString(), Toast.LENGTH_SHORT).show();

       groupDao.getGroups(list , context);
       listGroup= groupDao.getGroups();
       loadGroups();


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







     //   adapter = new GroupsAdapter(context,listGroup);
       // recyclerView.setAdapter(adapter);




        return this.view;

    }

    public  void load(){
       quoteDao.getRef().addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

              HomeFragment.this.quot=  HomeFragment.this.quoteDao.getQuoteDao();

              HomeFragment.this.quote.setText(quot.getQuote());
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {
           }
       });


    }
    public  void loadGroups(){
        groupDao.getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                adapter = new GroupsAdapter(HomeFragment.this.context, HomeFragment.this.listGroup);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


    }

}
