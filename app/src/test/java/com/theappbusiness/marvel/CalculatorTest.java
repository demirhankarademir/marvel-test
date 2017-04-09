package com.theappbusiness.marvel;

import com.theappbusiness.marvel.database.Comic;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by demir on 9.04.2017.
 */
public class CalculatorTest {
    Calculator calculator;
    List<Comic> comicList;
    float budget;
    @Before
    public void setUp() throws Exception {
        calculator = new Calculator();
        comicList = new ArrayList<>();


    }

    @Test
    public void calculate() throws Exception
    {
        //Input data
        Comic c = new Comic();
        c.setPrice(2.55);
        c.setPageCount(100);

        Comic c1 = new Comic();
        c1.setPrice(120.0);
        c1.setPageCount(120);

        comicList.add(c);
        comicList.add(c1);
        budget = 120;

        //Call calculate method

        calculator.calculate(comicList, budget);
        int numberOfComics = calculator.getNumberOfComics();
        int totalPages = calculator.getTotalPages();

        //Expected Data
        int expNumberOfComics = 1;
        int expTotalPages = 100;

        //Verify Data
        assertEquals(expNumberOfComics, numberOfComics);
        assertEquals(expTotalPages, totalPages);
    }

}