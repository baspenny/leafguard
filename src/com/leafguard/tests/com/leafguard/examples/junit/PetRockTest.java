package com.leafguard.examples.junit;

import org.junit.Test;

import static org.junit.Assert.*;

public class PetRockTest {

    PetRock rocky = new PetRock("Rocky");

    @Test
    public void getName() throws Exception {
        assertEquals("Rocky", rocky.getName());
    }


    @Test
    public void testUnHappyToStart() throws Exception {
        assertFalse(rocky.isHappy());
    }


    @Test (expected = IllegalStateException.class)
    public void TestPrintHappyMessage() throws Exception {
        rocky.printHappyMessage();
        assertTrue(rocky.isHappy());
    }

    @Test
    public void nameFail() throws Exception {
        rocky.playWithRock();
        rocky.printHappyMessage();
    }


}

// JUnit 4 with IntelliJ: A quick introduction
// https://www.youtube.com/watch?v=Bld3644bIAo

// JUnit 4 with IntelliJ: Exceptions, Ignore, ...
// https://www.youtube.com/watch?v=xHk9yGZ1z3k


