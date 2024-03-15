package com.example.contactappv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<Contact> contactList;
    private CustomAdapter contactAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot=binding.getRoot();
        setContentView(viewRoot);

        contactList =new ArrayList<Contact>();
        contactAdapter =new CustomAdapter(contactList);
        binding.recyclerview.setAdapter(contactAdapter);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Contact");


        contactList.add(new Contact("Tran A", "0363614059", "a@gmail.com"));
        contactList.add(new Contact("Nguyen B", "0363614059", "b@gmail.com"));
        contactList.add(new Contact("Huynh C", "0363614059", "c@gmail.com"));

        contactAdapter.notifyDataSetChanged();

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                startActivityForResult(intent,1010);
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1010 && resultCode == RESULT_OK) {

            // Xử lý dữ liệu trả về từ DetailActivity
            if (data != null && data.hasExtra("newContact")) {
                Contact newContact=(Contact)data.getSerializableExtra("newContact");
                contactList.add(newContact);
                contactAdapter.notifyDataSetChanged();
            }
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemSearch = R.id.menuItemSearch;
        if(item.getItemId()==menuItemSearch)
        {
            Intent intent=new Intent(MainActivity.this, SearchActivity.class);
            intent.putExtra("listContact",contactList);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

}