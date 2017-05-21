package com.steven.schooldelivery.db;

import android.content.Context;

import com.google.gson.annotations.SerializedName;
import com.steven.schooldelivery.Constant;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by finderlo on 2017/4/7.
 */


public class User extends DataSupport {
    @SerializedName("uid")
    private String uid;
    @SerializedName("name")
    private String name;
    @SerializedName("sex")
    private String sex;
    @SerializedName("phone")
    private String phone;
    @SerializedName("password")
    private String password;
    @SerializedName("identity")
    private int identity;

    @SerializedName("schoolName")
    private String schoolName;
    @SerializedName("schoolAddress")
    private String schoolAddress;
    @SerializedName("schoolCard")
    private String schoolCard;

    @SerializedName("idCard")
    private String idCard;
    @SerializedName("photo")
    private String photo;
    @SerializedName("aliPay")
    private String aliPay;

    public enum UserType {
        RECIPIENT("收件人"), REPLACEMENT("代取人"), ADMINSTARTE("管理员"), SYSTEM("系统");

        private String info;

        UserType(String s) {
            info = s;
        }

        @Override
        public String toString() {
            return info;
        }
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public UserType getIdentity() {
        switch (identity) {
            case 0:
                return UserType.RECIPIENT;
            case 1:
                return UserType.REPLACEMENT;
            case 2:
                return UserType.ADMINSTARTE;
            case 3:
                return UserType.SYSTEM;
            default:
                return UserType.RECIPIENT;
        }
    }

    public String getSchoolCard() {
        return schoolCard;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getPhoto() {
        return photo;
    }

    public String getAliPay() {
        return aliPay;
    }

    public String getSex() {
        return sex;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public void setSchoolCard(String schoolCard) {
        this.schoolCard = schoolCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setAliPay(String aliPay) {
        this.aliPay = aliPay;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }


    public static void setCurrentUser(User user){
        DataSupport.deleteAll(User.class, "");
        user.save();
    }

    public static User getCurrentUser(Context context) {
        if (context == null) return null;
        String uid = context.getSharedPreferences(Constant.SharedPreferences.USERINFO, Context.MODE_PRIVATE).getString(Constant.SharedPreferences.ID, "");
        if ("".equals(uid)) return null;
        List<User> users = DataSupport.where("uid = ?", uid).find(User.class);

        return users.get(0);
    }
}
