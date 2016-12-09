package com.example.brajeshkumar.demo_fb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by Brajesh Kumar on 11/25/2016.
 */
public class FriendDetails {
    private int id;
    private String Name;
    private String Gender;
    private String Qualification;
    private String Friend_Image;
    private String EmailID;
    private String Ame;

    public FriendDetails(){

    }
    public FriendDetails(int id, String name, String qualification, String gender, String emailid, String ame, String friend_Image) {
        this.id = id;
        Name = name;
        Qualification = qualification;;
        Gender = gender;
        EmailID = emailid;
        Ame = ame;

        Friend_Image = friend_Image;
    }
    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String emailID) {
        EmailID = emailID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAme() {
        return Ame;
    }

    public void setAme(String ame) {
        Ame = ame;
    }


    public String getFriend_Image() {
        return Friend_Image;
    }

    public void setFriend_Image(String warrior_Image) {
        Friend_Image = warrior_Image;
    }

    public boolean hasImage() {

        return getFriend_Image() != null && !getFriend_Image().isEmpty();
    }
    public Drawable getThumbnail(Context context) {

        return getScaledImage(context, 128, 128);
    }
    public Drawable getImage(Context context) {

        return getScaledImage(context, 512, 512);
    }
    private Drawable getScaledImage(Context context, int reqWidth, int reqHeight) {

        // If profile has a Image.
        if (hasImage()) {

            // Decode the input stream into a bitmap.
            Bitmap bitmap = FileUtils.getResizedBitmap(getFriend_Image(), reqWidth, reqHeight);

            // If was successfully created.
            if (bitmap != null) {

                // Return a drawable representation of the bitmap.
                return new BitmapDrawable(context.getResources(), bitmap);
            }
        }

        // Return the default image drawable.
        return context.getResources().getDrawable(R.drawable.download);
    }
}
