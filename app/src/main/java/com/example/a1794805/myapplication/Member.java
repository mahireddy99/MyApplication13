package com.example.a1794805.myapplication;


public class Member {
    String id;
    String memberName;
    String email;
    String profession;
    String phonenumber;


    public Member(String id, String memberName,String email,String profession,String phonenumber) {
        super();
        this.id = id;
        this.memberName = memberName;
        this.email=email;
        this.profession=profession;
        this.phonenumber=phonenumber;

    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getProfession(){return profession;}
    public void setprofession(String profession){this.profession=profession;}
    public String getPhonenumber(){return phonenumber;}
    public void setPhonenumber(String phonenumber){this.phonenumber=phonenumber;}



}
