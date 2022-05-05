package com.example.fitnessfirst.repository;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.fitnessfirst.home.HomeActivity;
import com.example.fitnessfirst.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FirebaseDatabaseHelper {

    private static final String TAG = "FirebaseDatabaseHelper";

    private static FirebaseAuth firebaseAuth;
    private static DatabaseReference publicUserEndPoint;

    private static ArrayList<User> usersArrayList;
    private static boolean loginSuccessfull =  false;
    private static boolean signupSuccessfull =  false;
    private static boolean passwordResetSuccessfull =  false;


    public FirebaseDatabaseHelper() {

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        publicUserEndPoint = firebaseDatabase.getReference("public_user_table");

        usersArrayList = getAllUsersFromFirebase();
    }

    private static ArrayList<User> getAllUsersFromFirebase() {
        final ArrayList<User> users = new ArrayList<>();
        readData(publicUserEndPoint, dataSnapshot -> {
            for (DataSnapshot s : dataSnapshot.getChildren()) {
                users.add(s.getValue(User.class));
            }
        });
        return users;
    }

    public static ArrayList<User> getAllPublicUsers() {
        return usersArrayList;
    }

    public static ArrayList<User> getUser(String id) {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < usersArrayList.size(); i++) {
            if (usersArrayList.get(i).getUserId().equals(id)) {
                users.add(usersArrayList.get(i));
            }
        }
        return users;
    }

    /* *********************************** PUBLIC USER CRUD ********************************** */
    public static boolean createUser(final User user, Uri uri) {
        firebaseAuth.createUserWithEmailAndPassword(user.getEmailAddress(), user.getPassword())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.e(TAG, "Auth Created");
                        user.setUserId(firebaseAuth.getCurrentUser().getUid());
                        publicUserEndPoint.child(user.getUserId()).
                                setValue(user).addOnCompleteListener(task1 -> {

                            if (task1.isSuccessful()) {
                                signupSuccessfull =  true;
                                if (uri != null) {
                                    Utils.uploadImage(user.getUserId(), uri);
                                    Log.e(TAG, "Database entry created");
                                    usersArrayList.add(user);
                                }
                            } else {
                                Log.e(TAG, task1.getException().toString());
                                Objects.requireNonNull(firebaseAuth.getCurrentUser()).delete();
                            }
                        });
                    } else Log.e(TAG, task.getException().toString());
                });
        return signupSuccessfull;
    }

    public static User getUserByEmail(String email) {
        for (int i = 0; i < usersArrayList.size(); i++) {
            if (usersArrayList.get(i).getEmailAddress().equals(email))
                return usersArrayList.get(i);
        }
        return null;
    }

    public static void updateUser(final User user) {
        publicUserEndPoint.child(user.getUserId()).
                setValue(user).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.e(TAG, "Database entry created");
                updateLocalUsersList(user);
            } else {
                Log.e(TAG, task.getException().toString());
            }
        });
    }

    public static boolean deleteUser(final User user) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(user.getEmailAddress(),
                user.getPassword()).addOnSuccessListener(authResult -> {
            auth.getCurrentUser().delete();
            publicUserEndPoint.child(user.getUserId())
                    .removeValue().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.e(TAG, "Child deleted Successfully");
                    usersArrayList.remove(user);
                } else {
                    Log.e(TAG, "Error Deleting child");
                }
            });
        });
        return true;
    }



    /* *********************************** Firebase authentications ********************************** */


    public static void loginUser(String email, String password, Context context) {


        firebaseAuth.signInWithEmailAndPassword(email, password).
                addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.e(TAG, "Auth Created");

                        Map<String, Object> userMap = new HashMap<String, Object>();
                        userMap.put("password", password);
                        publicUserEndPoint.child(firebaseAuth.getCurrentUser().getUid()).
                       updateChildren(userMap).addOnCompleteListener(task1 -> {
                   if (task1.isSuccessful()) {
                       Intent intent =  new Intent(context, HomeActivity.class);
                       context.startActivity(intent);
                       loginSuccessfull =  true;
                       Log.e(TAG, "User Login entry created");
                   } else {
                       Log.e(TAG, task1.getException().toString());
                       Objects.requireNonNull(firebaseAuth.getCurrentUser()).delete();
                   }
               });
           } else Log.e(TAG, task.getException().toString());
       });
   }

    public static void resetPassword(String email) {
        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        passwordResetSuccessfull = true;
                    }
                });
    }

    ///////////////////////////////////////// HELPER METHODS ///////////////////////////////////////////////////////

    private static void readData(DatabaseReference databaseReference,
                                 final OnGetDataListener listener) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listener.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private static void updateLocalUsersList(User user) {
        for (int i = 0; i < usersArrayList.size(); i++) {
            if (usersArrayList.get(i).equals(user)) {
                usersArrayList.get(i).setEmailAddress(user.getEmailAddress());
                usersArrayList.get(i).setFirstName(user.getFirstName());
                usersArrayList.get(i).setLastName(user.getLastName());
                usersArrayList.get(i).setPassword(user.getPassword());
                break;
            }
        }
    }

    public interface OnGetDataListener {
        void onSuccess(DataSnapshot dataSnapshot);
    }

}
