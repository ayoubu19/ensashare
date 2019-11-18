package com.example.myapplication.DAO;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.model.Admin;
import com.example.myapplication.model.Group;
import com.example.myapplication.model.Student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public  class GroupDao {
    private DatabaseReference ref;
    private Group groupdao;
    private Context context;
    private ArrayList<Group> groups = new ArrayList<>();
    private ArrayList<Group> clubs = new ArrayList<>();

    public ArrayList<Group> getClub() {
        return clubs;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public GroupDao() {
    }

    public DatabaseReference getRef() {
        return ref;
    }

    public void setRef(DatabaseReference ref) {
        this.ref = ref;
    }

    public Group getGroupdao() {
        return groupdao;
    }

    public void setGroupdao(Group groupdao) {
        this.groupdao = groupdao;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public GroupDao(final Context context, String groupNameClicked) {
        ref = FirebaseDatabase.getInstance().getReference().child("groups").child("group:" + groupNameClicked);
        this.context = context;
        groupdao = new Group();
    }

    public synchronized Group getGroupDao() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Admin admin;
                groups = new ArrayList<Group>();
                String name = dataSnapshot.child("name").getValue().toString();
                String urlImage = dataSnapshot.child("urlImage").getValue().toString();
                String groupId = dataSnapshot.child("groupId").getValue().toString();
                Object data = (HashMap<String, Object>) dataSnapshot.child("Admin").getValue();
                HashMap<String, Object> dataMap = (HashMap<String, Object>) data;
                admin = new Admin((String) dataMap.get("firstName"), (String) dataMap.get("lastName"), (String) dataMap.get("userName"),
                        (String) dataMap.get("level"), groupdao.getName());
                Toast.makeText(context, "Name : " + admin.getUserName(), Toast.LENGTH_LONG).show();
                GroupDao.this.groupdao.setName(name);
                GroupDao.this.groupdao.setUrlImage(urlImage);
                GroupDao.this.groupdao.setGroupId(groupId);
                GroupDao.this.groupdao.setAdmin(admin);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return this.groupdao;
    }

    public void getListStudentGroup() {
        List<Student> studentList = new ArrayList<Student>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Student> studentList = new ArrayList<Student>();

                HashMap<String, Object> dataMap = (HashMap<String, Object>) dataSnapshot.child("students").getValue();
                for (String key : dataMap.keySet()) {
                    Object data = dataMap.get(key);
                    try {
                        HashMap<String, Object> studentData = (HashMap<String, Object>) data;
                        Student student = new Student((String) studentData.get("firstName"), (String) studentData.get("lastName"), (String) studentData.get("userName"), (String) studentData.get("level"));
                        Toast.makeText(context, "Name : " + student.getUserName(), Toast.LENGTH_LONG).show();
                        studentList.add(student);
                    } catch (Exception e) {
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        this.groupdao.setListStudents(studentList);
    }


    public void getGroups(List<Object> listObjects,final Context context) {


        for (int i = 0; i < listObjects.size(); i++) {

            String level = listObjects.get(i).toString();

            if (!level.equals("entry")) {

                if (!level.startsWith("C"))  {
                ref = FirebaseDatabase.getInstance().getReference().child("groups").child("group:" + level.toLowerCase());

                ref.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Group g = dataSnapshot.getValue(Group.class);

                        groups.add(g);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }

                });

            }else{
                    ref = FirebaseDatabase.getInstance().getReference().child("clubs").child("club:" + level.toLowerCase());

                    ref.addValueEventListener(new ValueEventListener() {
                        @Override

                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Group g = dataSnapshot.getValue(Group.class);

                            groups.add(g);


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }

                    });
                }

            }


        }

    }

    public void getClubs(final List<Object> listObjects , final Context context) {

                ref = FirebaseDatabase.getInstance().getReference().child("clubs");

                ref.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                   for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                       Group g = snapshot.getValue(Group.class);
                       Object data = (HashMap<String, Object>) snapshot.child("Admin").getValue();

                       HashMap<String, Object> dataMap = (HashMap<String, Object>) data;

                       Admin admin = new Admin((String) dataMap.get("userName"), g.getName());

                       g.setAdmin(admin);

                       Toast.makeText(context, g.toString(), Toast.LENGTH_LONG).show();

                      if (!listObjects.contains(g.getName())){

                           clubs.add(g);
                       }

                   }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }

                });


    }
}