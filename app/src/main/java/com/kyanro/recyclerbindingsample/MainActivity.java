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
import android.widget.Toast;

import com.kyanro.recyclerbindingsample.databinding.ActivityMainBinding;
import com.kyanro.recyclerbindingsample.databinding.ActivityMainListHeaderBinding;
import com.kyanro.recyclerbindingsample.databinding.ActivityMainListRowBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

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
        binding.simpleRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.simpleRv.setAdapter(new TextRecyclerAdapter(items, true));
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

        static abstract class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View itemView) {
                super(itemView);
            }

            abstract ViewDataBinding getBinding();
        }

        static class HeaderViewHolder extends ViewHolder {
            ActivityMainListHeaderBinding binding;

            public HeaderViewHolder(View itemView) {
                super(itemView);
                binding = DataBindingUtil.bind(itemView);
            }

            @Override
            ActivityMainListHeaderBinding getBinding() {
                return binding;
            }
        }

        static class DataViewHolder extends ViewHolder {
            ActivityMainListRowBinding binding;

            public DataViewHolder(View itemView) {
                super(itemView);
                binding = DataBindingUtil.bind(itemView);
            }

            ActivityMainListRowBinding getBinding() {
                return binding;
            }
        }

        List<Item> items;
        boolean showHeader;

        public TextRecyclerAdapter(@NonNull List<Item> items, boolean showHeader) {
            this.items = items;
            this.showHeader = showHeader;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_HEADER) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_list_header, parent, false);
                HeaderViewHolder holder = new HeaderViewHolder(view);
                holder.getBinding().imageIcon.setOnClickListener(v -> {
                    Toast.makeText(parent.getContext(), "header clicked", Toast.LENGTH_LONG).show();
                });
                return holder;
            } else if (viewType == VIEW_TYPE_DATA) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_list_row, parent, false);
                return new DataViewHolder(view);
            } else {
                // 来てはいけない
                throw new RuntimeException("unknown viewType:" + viewType);
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            if (viewHolder.getItemViewType() == VIEW_TYPE_HEADER) {
                // createViewHolder のタイミングで必要なものはセット済み
            } else if (viewHolder.getItemViewType() == VIEW_TYPE_DATA) {
                DataViewHolder holder = (DataViewHolder) viewHolder;
                holder.getBinding().setVariable(com.kyanro.recyclerbindingsample.BR.item, items.get(position));
                holder.getBinding().executePendingBindings();
            }
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        static final int VIEW_TYPE_HEADER = 0;
        static final int VIEW_TYPE_DATA = 1;

        @Override
        public int getItemViewType(int position) {
            if (!showHeader) {
                return VIEW_TYPE_DATA;
            }

            if (position == 0) {
                return VIEW_TYPE_HEADER;
            } else {
                return VIEW_TYPE_DATA;
            }
        }
    }
}
