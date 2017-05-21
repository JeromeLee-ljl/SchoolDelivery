package com.steven.schooldelivery.entity;

import java.sql.Timestamp;

/**
 * Created by finderlo on 2017/4/7.
 */

public class ComplaintEntity {
    private String complaintId;
    private String userId;
    private String managerId;
    private String complaintType;
    private Timestamp complaintTime;
    private String complaintState;
    private String complaintInformation;
    private String complaintResult;


    public String getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }


    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }


    public Timestamp getComplaintTime() {
        return complaintTime;
    }

    public void setComplaintTime(Timestamp complaintTime) {
        this.complaintTime = complaintTime;
    }


    public String getComplaintState() {
        return complaintState;
    }

    public void setComplaintState(String complaintState) {
        this.complaintState = complaintState;
    }

      
      
    public String getComplaintInformation() {
        return complaintInformation;
    }

    public void setComplaintInformation(String complaintInformation) {
        this.complaintInformation = complaintInformation;
    }

      
      
    public String getComplaintResult() {
        return complaintResult;
    }

    public void setComplaintResult(String complaintResult) {
        this.complaintResult = complaintResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComplaintEntity that = (ComplaintEntity) o;

        if (complaintId != null ? !complaintId.equals(that.complaintId) : that.complaintId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (managerId != null ? !managerId.equals(that.managerId) : that.managerId != null) return false;
        if (complaintType != null ? !complaintType.equals(that.complaintType) : that.complaintType != null)
            return false;
        if (complaintTime != null ? !complaintTime.equals(that.complaintTime) : that.complaintTime != null)
            return false;
        if (complaintState != null ? !complaintState.equals(that.complaintState) : that.complaintState != null)
            return false;
        if (complaintInformation != null ? !complaintInformation.equals(that.complaintInformation) : that.complaintInformation != null)
            return false;
        if (complaintResult != null ? !complaintResult.equals(that.complaintResult) : that.complaintResult != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = complaintId != null ? complaintId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (managerId != null ? managerId.hashCode() : 0);
        result = 31 * result + (complaintType != null ? complaintType.hashCode() : 0);
        result = 31 * result + (complaintTime != null ? complaintTime.hashCode() : 0);
        result = 31 * result + (complaintState != null ? complaintState.hashCode() : 0);
        result = 31 * result + (complaintInformation != null ? complaintInformation.hashCode() : 0);
        result = 31 * result + (complaintResult != null ? complaintResult.hashCode() : 0);
        return result;
    }
}
