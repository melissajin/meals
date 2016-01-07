package com.meljin.meals;

/**
 * Created by s_jin01 on 11/24/15.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailFragment extends Fragment {
    TextView breakfastText;
    TextView lunchText;
    TextView dinnerText;
    ArrayList<String> breakfastArray;
    ArrayList<String> lunchArray;
    ArrayList<String> dinnerArray;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    private static final int BRE = 0;
    private static final int LUN = 1;
    private static final int DIN = 2;
    private ListView lvItem;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle args) {
        View view = inflater.inflate(R.layout.day, container, false);

        //getActivity().setTitle(getArguments().getString("Menu"));

        breakfastText = (TextView) view.findViewById(R.id.breakfast);
        lunchText = (TextView) view.findViewById(R.id.lunch);
        dinnerText = (TextView) view.findViewById(R.id.dinner);

        breakfastArray = new ArrayList<String>();
        lunchArray = new ArrayList<String>();
        dinnerArray = new ArrayList<String>();

        breakfastText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(BRE).show();
                Toast.makeText(getActivity(), "Breakfast", Toast.LENGTH_SHORT).show();
            }
        });

        lunchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(LUN).show();
                Toast.makeText(getActivity(), "Lunch", Toast.LENGTH_SHORT).show();
            }
        });

        dinnerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(DIN).show();
                Toast.makeText(getActivity(), "Dinner", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private Dialog createDialog(int meal){
        Log.d("Dialog Activity", "Creating Dialog");
        switch(meal) {
            case BRE:
                list = breakfastArray;
                lvItem = (ListView)getView().findViewById(R.id.listView_items_b);
                break;
            case LUN:
                list = lunchArray;
                lvItem = (ListView)getView().findViewById(R.id.listView_items_l);
                break;
            case DIN:
                list = dinnerArray;
                lvItem = (ListView)getView().findViewById(R.id.listView_items_d);
                break;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("What do you plan to eat?");

        final EditText input = new EditText(getActivity());
        builder.setView(input);

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        lvItem.setAdapter(adapter);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String value = input.getText().toString();
                list.add(value);
                adapter.notifyDataSetChanged();

                return;
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });

        return builder.create();
    }

    public void saveInDatabase(Foods food){
        DatabaseHandler db = new DatabaseHandler(getActivity());
        db.addFood(food);
    }

}