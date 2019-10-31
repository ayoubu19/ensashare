package com.example.myapplication.DAO;

import com.example.myapplication.firebaseHelper.NodeCreator;
import com.example.myapplication.model.Student;
import com.google.firebase.database.DatabaseReference;

public class StudentDao {
    private DatabaseReference databaseReference ;
    public StudentDao() {
        NodeCreator nodeCreator = new NodeCreator("students");
        this.databaseReference=nodeCreator.getDatabaseReference();
    }

    public void registerStudent(Student s){


         databaseReference.child(s.getUserName()).setValue(s);

    }

}
