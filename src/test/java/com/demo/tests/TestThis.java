package com.demo;
import java.util.*;
import org.junit.* ;
import static com.demo.Poop.*;
import static org.junit.Assert.* ;

public class TestThis {

    private Dictionary dictionary;

    @Before
    public void setUp() {
	// dictionary = new Dictionary("words.txt");
    }

    @Test
    public void testValidWords() {
	assertTrue(Poop.returnTrue());

    }

    @Test
    public void testInvalidWords() {
	assertFalse(true);
    }

}

