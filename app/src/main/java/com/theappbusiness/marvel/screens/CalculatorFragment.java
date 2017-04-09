package com.theappbusiness.marvel.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.theappbusiness.marvel.BudgetManager;
import com.theappbusiness.marvel.Calculator;
import com.theappbusiness.marvel.R;
import com.theappbusiness.marvel.Utils;
import com.theappbusiness.marvel.database.Comic;
import com.theappbusiness.marvel.database.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by demir on 6.04.2017.
 */

public class CalculatorFragment extends Fragment implements CalculatorComicAdapter.OnItemClickListener
{
    List<Comic> comicList;
    RecyclerView lvComics;
    CalculatorComicAdapter comicAdapter;
    Button bSubmit;
    EditText etBudget;
    TextView tvNumberOfComics, tvMaxNumberOfPages, tvCurrentBudget;
    BudgetManager budgetManager;
    Calculator calculator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        lvComics = (RecyclerView) view.findViewById(R.id.lvComics);
        bSubmit = (Button) view.findViewById(R.id.bSubmit);
        etBudget = (EditText) view.findViewById(R.id.etBudget);
        tvMaxNumberOfPages = (TextView) view.findViewById(R.id.tvMaxNumberOfPages);
        tvNumberOfComics = (TextView) view.findViewById(R.id.tvNumberOfComics);
        tvCurrentBudget = (TextView) view.findViewById(R.id.tvCurrentBudget);
        budgetManager = BudgetManager.getInstance();

        comicAdapter = new CalculatorComicAdapter(getActivity(), this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        lvComics.setLayoutManager(mLayoutManager);
        lvComics.setItemAnimator(new DefaultItemAnimator());
        lvComics.setAdapter(comicAdapter);

        tvCurrentBudget.setText(budgetManager.getBudget()+"");


        bSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Utils.hideKeyboard(getActivity(), etBudget);
                String enteredString = etBudget.getText().toString();
                if (!enteredString.equals(""))
                {
                    float enteredBudget = Float.valueOf(etBudget.getText().toString());
                    budgetManager.setBudget(enteredBudget);
                    tvCurrentBudget.setText(enteredBudget+"");
                    showCommics();
                }
                else
                {
                    Toast.makeText(getActivity(), getText(R.string.please_enter_a_budget), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void showCommics()
    {

        comicList = DatabaseManager.getComics();
        float budget = budgetManager.getBudget();

        calculator = new Calculator();
        calculator.calculate(comicList, budget);

        tvNumberOfComics.setText(calculator.getNumberOfComics()+"");
        tvMaxNumberOfPages.setText(calculator.getTotalPages()+"");

        comicAdapter.updateList(calculator.getAffordableList());
    }

    @Override
    public void onItemClick(Comic comic) {

    }
}
