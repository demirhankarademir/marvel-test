package com.theappbusiness.marvel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.theappbusiness.marvel.database.Comic;
import com.theappbusiness.marvel.database.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by demir on 6.04.2017.
 */

public class CalculatorFragment extends Fragment implements CalculatorComicAdapter.OnItemClickListener
{
    List<Comic> comicList, affordableList;
    RecyclerView lvComics;
    CalculatorComicAdapter comicAdapter;
    Button bSubmit;
    EditText etBudget;
    TextView tvNumberOfComics, tvMaxNumberOfPages, tvCurrentBudget;

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

        comicAdapter = new CalculatorComicAdapter(getActivity(), this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        lvComics.setLayoutManager(mLayoutManager);
        lvComics.setItemAnimator(new DefaultItemAnimator());
        lvComics.setAdapter(comicAdapter);

        tvCurrentBudget.setText(Prefs.getBudget()+"");


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
                    Prefs.setBudget(enteredBudget);
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
        affordableList = new ArrayList<>();

        double totalPrice = 0;
        double budget = Prefs.getBudget();
        int totalPages = 0;

        for (Comic c : comicList)
        {
            if (budget >= totalPrice+c.getPrice())
            {
                totalPrice = totalPrice + c.getPrice();
                affordableList.add(c);
                totalPages = totalPages + c.getPageCount();
            }
        }

        tvNumberOfComics.setText(affordableList.size()+"");
        tvMaxNumberOfPages.setText(totalPages+"");

        comicAdapter.updateList(affordableList);
    }

    @Override
    public void onItemClick(Comic comic) {

    }
}
