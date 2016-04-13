package com.kyanro.recyclerbindingsample;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kyanro.recyclerbindingsample.databinding.ActivityNextBinding;

import java.util.ArrayList;
import java.util.List;

public class NextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNextBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_next);
        List<Item> items = new ArrayList<>();
        items.add(new Item("hoge", "fuga"));
        items.add(new Item("piyo", "piyopiyo"));
        binding.setItems(items);
        binding.executePendingBindings();
    }


}
