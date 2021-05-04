package com.jetbrain;


public class DefaultMember{

    private String memberName;
    private String membershipNumber;
    Date startMembershipDate;



    public DefaultMember(String memberName,String membershipNumber,Date startMembershipDate){
        super();
        setMemberName(memberName);
        setMembershipNumber(membershipNumber);
        setStartMembershipDate(startMembershipDate);


    }

    public void setMemberName(String MemberName){
            this.memberName =MemberName;

    }

    public String getMemberName(){
        return memberName;
    }

    public void setMembershipNumber(String memberShipNumber){
        this.membershipNumber=memberShipNumber;
    }

    public String getMembershipNumber(){
        return membershipNumber;
    }


    public void setStartMembershipDate(Date startMembershipDate){
        this.startMembershipDate=startMembershipDate;
    }

    public Date getStartMembershipDate(){
        return startMembershipDate;
    }

    //details to be printed after questions answered.
    @Override
    public String toString() {
        return "\t\tMember Name: "+getMemberName() +"\n\t\tMember Id No.: "+getMembershipNumber()+"\n\t\tEnrollment Day: "+getStartMembershipDate()+"\n\n\t\tDefault Member Details\n";
    }
}
