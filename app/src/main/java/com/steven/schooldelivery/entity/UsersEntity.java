package com.steven.schooldelivery.entity;

import org.litepal.crud.DataSupport;

/**
 * Created by finderlo on 2017/4/7.
 */
 
 
public class UsersEntity extends DataSupport{
    private String userId;
    private String userName;
    private String userPhone;
    private String userPassword;
    private UserType userIdentity;
    private String userSchoolcard;
    private String userIdcard;
    private String userPhoto;
    private String userAlipay;
    private String userSex;
    private String userSchoolname;
    private String userSchooladdress;

    public enum UserType{
        SYSTEM,RECIPIENT,REPLACEMENT,ADMINSTARTE
    }

     
     
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

     
     
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

     
     
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

     
     
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

     
     
    public UserType getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(UserType userIdentity) {
        this.userIdentity = userIdentity;
    }

     
     
    public String getUserSchoolcard() {
        return userSchoolcard;
    }

    public void setUserSchoolcard(String userSchoolcard) {
        this.userSchoolcard = userSchoolcard;
    }

     
     
    public String getUserIdcard() {
        return userIdcard;
    }

    public void setUserIdcard(String userIdcard) {
        this.userIdcard = userIdcard;
    }

     
     
    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

     
     
    public String getUserAlipay() {
        return userAlipay;
    }

    public void setUserAlipay(String userAlipay) {
        this.userAlipay = userAlipay;
    }

     
     
    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

     
     
    public String getUserSchoolname() {
        return userSchoolname;
    }

    public void setUserSchoolname(String userSchoolname) {
        this.userSchoolname = userSchoolname;
    }

     
     
    public String getUserSchooladdress() {
        return userSchooladdress;
    }

    public void setUserSchooladdress(String userSchooladdress) {
        this.userSchooladdress = userSchooladdress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userPhone != null ? !userPhone.equals(that.userPhone) : that.userPhone != null) return false;
        if (userPassword != null ? !userPassword.equals(that.userPassword) : that.userPassword != null) return false;
        if (userIdentity != null ? !userIdentity.equals(that.userIdentity) : that.userIdentity != null) return false;
        if (userSchoolcard != null ? !userSchoolcard.equals(that.userSchoolcard) : that.userSchoolcard != null)
            return false;
        if (userIdcard != null ? !userIdcard.equals(that.userIdcard) : that.userIdcard != null) return false;
        if (userPhoto != null ? !userPhoto.equals(that.userPhoto) : that.userPhoto != null) return false;
        if (userAlipay != null ? !userAlipay.equals(that.userAlipay) : that.userAlipay != null) return false;
        if (userSex != null ? !userSex.equals(that.userSex) : that.userSex != null) return false;
        if (userSchoolname != null ? !userSchoolname.equals(that.userSchoolname) : that.userSchoolname != null)
            return false;
        if (userSchooladdress != null ? !userSchooladdress.equals(that.userSchooladdress) : that.userSchooladdress != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userPhone != null ? userPhone.hashCode() : 0);
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        result = 31 * result + (userIdentity != null ? userIdentity.hashCode() : 0);
        result = 31 * result + (userSchoolcard != null ? userSchoolcard.hashCode() : 0);
        result = 31 * result + (userIdcard != null ? userIdcard.hashCode() : 0);
        result = 31 * result + (userPhoto != null ? userPhoto.hashCode() : 0);
        result = 31 * result + (userAlipay != null ? userAlipay.hashCode() : 0);
        result = 31 * result + (userSex != null ? userSex.hashCode() : 0);
        result = 31 * result + (userSchoolname != null ? userSchoolname.hashCode() : 0);
        result = 31 * result + (userSchooladdress != null ? userSchooladdress.hashCode() : 0);
        return result;
    }
}
