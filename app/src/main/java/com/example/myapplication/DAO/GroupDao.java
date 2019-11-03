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
                String name = dataSnapshot.child("name").getValue().toString();
                String urlImage = dataSnapshot.child("urlImage").getValue().toString();
                String groupId = dataSnapshot.child("groupId").getValue().toString();
                Object data = (HashMap<String, Object>) dataSnapshot.child("Admin").getValue();
                HashMap<String, Object> dataMap = (HashMap<String, Object>) data;
                admin = new Admin((String) dataMap.get("firstName"), (String) dataMap.get("lastName"), (String) dataMap.get("userName"),
                       (String) dataMap.get("level"));
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
                    } catch (Exception e) {  }
                }
            }
            
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        this.groupdao.setListStudents(studentList);
    }









    }
