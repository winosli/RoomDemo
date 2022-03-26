package com.test.roomsqlite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.roomsqlite.R;
import com.test.roomsqlite.data.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    List<Book>list;
    Context context;
    onBookListener onBookListener;
    public BookAdapter(Context context, List<Book> list, onBookListener onBookListener){
        this.list = list;
        this.context = context;
        this.onBookListener = onBookListener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_recycler_row, parent, false);
        return new BookAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Book book = list.get(position);
        holder.tvId.setText(book.getBookId() + "");
        holder.tvName.setText(book.getName());
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvId;
        TextView tvName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvBookIdRow);
            tvName = itemView.findViewById(R.id.tvBookNameRow);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBookListener.onBookClick(getAdapterPosition());
                }
            });
        }
    }

    public interface onBookListener{
        void onBookClick(int position);
    }
}
