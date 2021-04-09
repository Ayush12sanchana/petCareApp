package com.example.petapp002.Model;

public class PetDetails {

    String Uid;
    String imgURL;
    String petAge;
    String petBreed;
    String petDOB;
    String petName;
    String petWeight;


    public PetDetails() {
    }

    public PetDetails(String uid, String imgURL, String petAge, String petBreed, String petDOB, String petName, String petWeight) {
        Uid = uid;
        this.imgURL = imgURL;
        this.petAge = petAge;
        this.petBreed = petBreed;
        this.petDOB = petDOB;
        this.petName = petName;
        this.petWeight = petWeight;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getPetAge() {
        return petAge;
    }

    public void setPetAge(String petAge) {
        this.petAge = petAge;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getPetDOB() {
        return petDOB;
    }

    public void setPetDOB(String petDOB) {
        this.petDOB = petDOB;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetWeight() {
        return petWeight;
    }

    public void setPetWeight(String petWeight) {
        this.petWeight = petWeight;
    }
}
