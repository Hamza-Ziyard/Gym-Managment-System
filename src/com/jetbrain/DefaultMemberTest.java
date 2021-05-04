package com.jetbrain;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class DefaultMemberTest {

    @Test
    public void getMemberName() {
        String chckname="Hamza";
        DefaultMember defaultMemberTest = new DefaultMember("Hamza","10",new Date(10,10,2020));
        defaultMemberTest.setMemberName(chckname);
        assertEquals(chckname,defaultMemberTest.getMemberName());
    }

    @Test
    public void getMembershipNumber() {
        String chckid="10";
        DefaultMember defaultMemberTest = new DefaultMember("Hamza","10",new Date(10,10,2020));
        defaultMemberTest.setMembershipNumber(chckid);
        assertEquals(chckid,defaultMemberTest.getMembershipNumber());
    }

    @Test
    void getStartMembershipDate() {
        Date chckdate=new Date(10,10,2020);
        DefaultMember defaultMemberTest = new DefaultMember("Hamza","10",new Date(10,10,2020));
        defaultMemberTest.setStartMembershipDate(chckdate);
        assertEquals(chckdate,defaultMemberTest.getStartMembershipDate());
    }
}