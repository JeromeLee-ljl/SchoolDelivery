package com.steven.schooldelivery.entity;

/**
 * Created by finderlo on 2017/4/7.
 */
 
 
public class ManagerEntity {
    private String managerId;
    private String managerName;
    private String managerIdentity;
    private String managerPhone;

     
     
    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

     
     
    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

     
     
    public String getManagerIdentity() {
        return managerIdentity;
    }

    public void setManagerIdentity(String managerIdentity) {
        this.managerIdentity = managerIdentity;
    }

     
     
    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManagerEntity that = (ManagerEntity) o;

        if (managerId != null ? !managerId.equals(that.managerId) : that.managerId != null) return false;
        if (managerName != null ? !managerName.equals(that.managerName) : that.managerName != null) return false;
        if (managerIdentity != null ? !managerIdentity.equals(that.managerIdentity) : that.managerIdentity != null)
            return false;
        if (managerPhone != null ? !managerPhone.equals(that.managerPhone) : that.managerPhone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = managerId != null ? managerId.hashCode() : 0;
        result = 31 * result + (managerName != null ? managerName.hashCode() : 0);
        result = 31 * result + (managerIdentity != null ? managerIdentity.hashCode() : 0);
        result = 31 * result + (managerPhone != null ? managerPhone.hashCode() : 0);
        return result;
    }
}
