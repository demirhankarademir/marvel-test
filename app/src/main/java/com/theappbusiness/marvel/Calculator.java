package com.theappbusiness.marvel;

import com.theappbusiness.marvel.database.Comic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by demir on 9.04.2017.
 */

public class Calculator
{
    List<Comic> affordableList;
    int totalPages = 0;
    int numberOfComics = 0;
    public Calculator()
    {
    }

    public void calculate(List<Comic> comicList, float budget)
    {
        affordableList = new ArrayList<>();

        double totalPrice = 0;
        totalPages = 0;
        numberOfComics = 0;

        for (Comic c : comicList)
        {
            if (budget >= totalPrice+c.getPrice())
            {
                totalPrice = totalPrice + c.getPrice();
                affordableList.add(c);
                totalPages = totalPages + c.getPageCount();
                numberOfComics++;
            }
        }
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getNumberOfComics() {
        return numberOfComics;
    }

    public List<Comic> getAffordableList() {
        return affordableList;
    }
}
