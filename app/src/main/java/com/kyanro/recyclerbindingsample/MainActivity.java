package com.kyanro.recyclerbindingsample;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.simple_rv);
        List<Item> items = new ArrayList<>(3);
        items.add(new Item("hoho", "hoge"));
        items.add(new Item("fufu", "fuga"));
        items.add(new Item("pipi", "piyo"));
        items.add(new Item("hoho", "hoge"));
        items.add(new Item("fufu", "fuga"));
        items.add(new Item("pipi", "piyo"));
        items.add(new Item("hoho", "hoge"));
        items.add(new Item("fufu", "fuga"));
        items.add(new Item("pipi", "piyo"));
        items.add(new Item("hoho", "hoge"));
        items.add(new Item("fufu", "fuga"));
        items.add(new Item("pipi", "piyo"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new TextRecyclerAdapter(items));

    }

    public static class Item {
        public String title;
        public String body;

        public Item(String title, String body) {
            this.title = title;
            this.body = body;
        }
    }

    static class TextRecyclerAdapter extends RecyclerView.Adapter<TextRecyclerAdapter.ViewHolder> {

        static class ViewHolder extends RecyclerView.ViewHolder {
            ViewDataBinding binding;

            public ViewHolder(View itemView) {
                super(itemView);
                binding = DataBindingUtil.bind(itemView);
            }

            ViewDataBinding getBinding() {
                return binding;
            }
        }

        List<Item> items;

        public TextRecyclerAdapter(@NonNull List<Item> items) {
            this.items = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_list_row, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.getBinding().setVariable(com.kyanro.recyclerbindingsample.BR.item, items.get(position));
            holder.getBinding().executePendingBindings();
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }
}
