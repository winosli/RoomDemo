package com.test.roomsqlite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.test.roomsqlite.R;
import com.test.roomsqlite.data.AppDatabase;
import com.test.roomsqlite.data.User;
import com.test.roomsqlite.data.UsersBookDao;
import com.test.roomsqlite.relations.UsersBooksRef;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private List<User>list;
    private Context context;
    private onUserListener onUserListener;

    public UserAdapter(Context context, List<User> list, onUserListener onUserListener) {
        this.list = list;
        this.context = context;
        this.onUserListener = onUserListener;
    }

    @NonNull
    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    public void setUserList(List<User> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = list.get(position);
        holder.tvFirstName.setText(user.getFirstName());
        holder.tvLastName.setText(user.getLastName());

        AppDatabase db  = AppDatabase.getDbInstance(context, AppDatabase.USERS_BOOK_REF);
        UsersBookDao usersBookDao = db.usersBookDao();
        List<UsersBooksRef> listRef = usersBookDao.getAllByUserId(user.getUid());
        String allBooksIds = "";
        for(UsersBooksRef item: listRef){
            allBooksIds += item.getBookId() + " | ";
        }

        if(allBooksIds.length() > 0){
            holder.tvBooks.setText(allBooksIds);
        }else{
            holder.tvBooks.setText("No Books");
        }
    }


    @Override
    public int getItemCount() {
        return this.list.size();
    }

    // -----------------------------------

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvFirstName;
        TextView tvLastName;
        TextView tvBooks;

        public MyViewHolder(View view){
            super(view);
            tvFirstName = view.findViewById(R.id.tvFirstNameRow);
            tvLastName = view.findViewById(R.id.tvLastNameRow);
            tvBooks = view.findViewById(R.id.tvBooksRow);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onUserListener.onUserClick(getAdapterPosition());
                }
            });
        }
    }

    public interface onUserListener{
        void onUserClick(int position);
    }

    public void updateList(List<User> list){
        this.list = list;
        notifyDataSetChanged();
    }
}
