package com.example.contactappv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;

    private ArrayList<Contact> contactList;
    private ArrayList<Contact> curentList;
    private CustomAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityDetailBinding.inflate(getLayoutInflater());
        View viewRoot=binding.getRoot();
        setContentView(viewRoot);

        contactList =new ArrayList<Contact>();
        curentList=new ArrayList<Contact>();
        contactAdapter =new CustomAdapter(curentList);
        binding.recyclerview.setAdapter(contactAdapter);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));

        Intent intent=getIntent();
        contactList.addAll((ArrayList<Contact>)intent.getSerializableExtra("listContact"));
        curentList.addAll(contactList);
        contactAdapter.notifyDataSetChanged();


        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        binding.edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                changeRecyclerView(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void changeRecyclerView(String s)
    {
        curentList.clear();
        for(int i=0;i<contactList.size();i++)
        {
            if(contactList.get(i).getName().contains(s))
                curentList.add(contactList.get(i));
        }
        contactAdapter.notifyDataSetChanged();
    }

}