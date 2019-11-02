package com.example.myapplication.model;

import com.example.myapplication.DAO.InvitationDao;

import java.util.ArrayList;

public class Student extends User  {


   private String level ;

   private ArrayList<Group> groups;



   public Student() {
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

                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public void sendInvitation(Invitation invitation){

        InvitationDao inv = new InvitationDao();
        inv.stockInvitation(invitation);

    }
}

