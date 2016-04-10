package com.kyanro.recyclerbindingsample;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.simple_rv);
        List<String> words = new ArrayList<>(3);
        words.add("hoge");
        words.add("fuga");
        words.add("piyo");
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new TextRecyclerAdapter(words));

    }


    static class TextRecyclerAdapter extends RecyclerView.Adapter<TextRecyclerAdapter.ViewHolder> {
        static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView title;
            public TextView body;

            public ViewHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.title_text);
                body = (TextView) itemView.findViewById(R.id.body_text);
            }
        }

        List<String> words;

        public TextRecyclerAdapter(@NonNull List<String> words) {
            this.words = words;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_list_row, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.title.setText(String.valueOf(position));
            holder.body.setText(words.get(position));
        }

        @Override
        public int getItemCount() {
            return words.size();
        }
    }
}
