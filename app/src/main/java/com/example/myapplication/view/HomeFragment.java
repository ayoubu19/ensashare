package com.example.myapplication.view;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.Student;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
private TextView nomComplet ;
private CircleImageView imageProfile ;
private FirebaseAuth userAuth ;
    private FirebaseUser currentUser ;

public void init(){
    userAuth = FirebaseAuth.getInstance();
    currentUser = userAuth.getCurrentUser();

}

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
       Context context= getActivity().getApplicationContext();
        nomComplet = (TextView) view.findViewById(R.id.nomComplet);
        imageProfile = (CircleImageView) view.findViewById(R.id.imageProfile);



        init();
        admin_Profile activity = (admin_Profile) getActivity();
        Student student = activity.getMyData();
        String nomprenom =student.getFirstName()+" "+student.getLastName();

        nomComplet.setText(nomprenom);

      // Picasso.get().load(currentUser.getPhotoUrl()).into( imageProfile );

        Glide.with(context).load(student.getProfilePic()).into(imageProfile);

       // Toast.makeText(context,currentUser.getPhotoUrl().toString(),Toast.LENGTH_SHORT).show();




        // Inflate the layout for this fragment
        return view;
    }

}
