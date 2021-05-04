package com.jetbrain;

public class StudentMember extends DefaultMember {

    private String schoolName;


    public StudentMember(String memberName,String memberId,String schoolName,Date startMembershipDate){
        super(memberName,memberId,startMembershipDate);
        setSchoolName(schoolName);

    }

    public void setSchoolName(String schoolName){
        this.schoolName=schoolName;
    }
    public String getSchoolName(){
        return schoolName;
    }

    //details to be printed after questions answered.
    @Override
    public String toString() {
        return "\t\tMember Name: "+getMemberName() +"\n\t\tMember Id No. : "+getMembershipNumber()+"\n\t\tMember School Name: " + getSchoolName()+"\n\t\tEnrollment Day: "+getStartMembershipDate()+"\n\n\t\tStudent Member Details\n";
    }
}
