package com.example.myapplication.model;




public class Invitation {

    private String firstName ;
    private String lastName  ;

    private String admin ;

    public Invitation(String firstName, String lastName, String admin) {
        this.firstName = firstName;
        this.lastName = lastName;

        this.admin = admin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Invitation{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", admin='" + admin + '\'' +
                '}';
    }
}
