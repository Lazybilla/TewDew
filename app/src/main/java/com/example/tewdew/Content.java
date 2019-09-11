package com.example.tewdew;

import java.util.ArrayList;

public class Content {

   public Content()
    {

    }

    private String mHeader ;

    public ArrayList<String> getmComments() {
        return mComments;
    }

    public void setmComments(ArrayList<String> mComments) {
        this.mComments = mComments;
    }

    public String getmHeader() {
        return mHeader;
    }

    public void setmHeader(String mHeader) {
        this.mHeader = mHeader;
    }

    public String getmDetails() {
        return mDetails;
    }

    public void setmDetails(String mDetails) {
        this.mDetails = mDetails;
    }

    public Boolean getmStatus() {
        return mStatus;
    }

    public void setmStatus(Boolean mStatus) {
        this.mStatus = mStatus;
    }

    public Content(String mHeader, String mDetails, Boolean mStatus, ArrayList<String> mComments) {
        this.mHeader = mHeader;
        this.mDetails = mDetails;
        this.mStatus = mStatus;
        this.mComments = mComments;
    }

    private String mDetails ;
    private Boolean mStatus ;
    private ArrayList<String> mComments ;


}
