package com.example.myapplication.model;

import com.example.myapplication.DAO.InvitationDao;

import java.util.HashMap;

public class Student extends User  {


   private String level ;

   private HashMap<String,Object> groups ;



    public HashMap<String, Object> getGroups() {
        return groups;
    }

    public void setGroups(HashMap<String, Object> groups) {
        this.groups = groups;
    }

    public Student() {
    }

    public Student(String firstName, String lastName, String userName, String level) {
        super();
    }


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public void partagerInformaion() {

    }


    @Override
    public String toString() {
        return "Student{" +
                "level='" + level + '\'' +
                ", groups=" + groups.toString() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", ProfilePic='" + ProfilePic + '\'' +
                '}';
    }

    public void sendInvitation(Invitation invitation){

        InvitationDao inv = new InvitationDao();

        inv.stockInvitation(invitation);

    }
}

