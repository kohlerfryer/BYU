package com.demo;
import java.util.*;
import org.junit.* ;
import static com.demo.Poop.*;
import static org.junit.Assert.* ;

public class Driver {

    private Dictionary dictionary;

    @Before
    public void setUp() {
	// dictionary = new Dictionary("words.txt");
    }

    @Test
    public void testFromDriver() {
	assertTrue(false);

    }

    @Test
    public void testInvalidWords() {
	assertFalse(false);
    }

}

