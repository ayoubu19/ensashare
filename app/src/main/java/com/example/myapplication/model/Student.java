package com.example.myapplication.model;

import com.example.myapplication.DAO.InvitationDao;

public class Student extends User  {


   private String level ;



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

