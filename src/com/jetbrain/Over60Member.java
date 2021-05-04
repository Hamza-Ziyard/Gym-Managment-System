package com.jetbrain;

import java.util.Scanner;

public class Over60Member extends DefaultMember {

    private int age;

    public Over60Member(String memberName,String memberId,int age,Date startMembershipDate) {
        super(memberName, memberId,startMembershipDate);
        setAge(age);
    }


    public void setAge(int age){
        this.age=age;

        //validating age
        while (true) {
            if (age <60 || age>100) {
                Scanner reEnter = new Scanner(System.in);
                System.out.print("-----  Invalid Age -----\n");
                System.out.print("\tRe-enter age : ");
                age=reEnter.nextInt();
                setAge(age);
            } else {
                break;
            }
        }

    }
    public int getAge(){
        return age;
    }

    //details to be printed after questions answered.
    @Override
    public String toString() {
        return "\t\tMember Name: "+getMemberName() +"\n\t\tMember Id No.: "+getMembershipNumber()+"\n\t\tMember Age: " + getAge()+"\n\t\tEnrollment Day: "+getStartMembershipDate()+"\n\n\t\tOver 60 Member Details\n";
    }
}
