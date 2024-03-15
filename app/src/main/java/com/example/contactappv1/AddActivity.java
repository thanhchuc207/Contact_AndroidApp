package com.example.contactappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.contactappv1.databinding.ActivityAddBinding;
import com.example.contactappv1.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    private ActivityAddBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        //thiet lap toolbar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Create new Contact");
        //thiết lập nút back cho toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //add item for spinner phone
        ArrayList<String> listSpinner = new ArrayList<String>();
        listSpinner.add("Mobile");
        listSpinner.add("Community");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listSpinner);
        binding.spinnerPhone.setAdapter(spinnerAdapter);

        //add item for spinner email
        ArrayList<String> listSpinner2 = new ArrayList<String>();
        listSpinner2.add("Home");
        listSpinner2.add("Business");
        ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listSpinner2);
        binding.spinnerEmail.setAdapter(spinnerAdapter2);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Xử lý sự kiện khi người dùng nhấn vào nút "Back"
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); //quay trở lại hoạt động trước đó
            return true;
        }
        else
            // Xử lý sự kiện khi người dùng nhấn vào nút "Save"
            if(item.getItemId()==R.id.menuItemSave)
            {
                Intent intent=new Intent(AddActivity.this,MainActivity.class);
                Contact newContact=new Contact(binding.txtFirstName.getText().toString()+" "+binding.txtLastName.getText().toString(),
                binding.txtPhone.getText().toString(),binding.txtEmail.getText().toString());
                intent.putExtra("newContact",newContact);
                setResult(RESULT_OK, intent);
                finish();
                return true;
            }
        return super.onOptionsItemSelected(item);
    }

}
