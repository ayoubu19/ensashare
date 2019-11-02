package com.example.myapplication.DAO;

import com.example.myapplication.firebaseHelper.NodeCreator;
import com.example.myapplication.model.Invitation;
import com.google.firebase.database.DatabaseReference;

public class InvitationDao {
    private DatabaseReference databaseReference ;


    public InvitationDao() {
            NodeCreator nodeCreator = new NodeCreator("invitations");
            this.databaseReference=nodeCreator.getDatabaseReference();
    }
    public void stockInvitation(Invitation invitation){
        String InvitationId = databaseReference.push().getKey();


        databaseReference.child(InvitationId).setValue(invitation);

    }
}
