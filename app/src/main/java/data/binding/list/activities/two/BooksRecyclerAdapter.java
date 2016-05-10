package data.binding.list.activities.two;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nimgade.pk.tutorial101.R;
import com.nimgade.pk.tutorial101.BR;

import java.util.ArrayList;

/**
 * Created by Pankaj Nimgade on 09-05-2016.
 */
public class BooksRecyclerAdapter extends RecyclerView.Adapter<BooksRecyclerAdapter.BindingHolder> {

    private ArrayList<Book> books;
    private LayoutInflater layoutInflater;
    private Context context;

    public BooksRecyclerAdapter(Context context, ArrayList<Book> books) {
        this.context = context;
        this.books = books;
        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.single_item_data_binding_two, parent, false);
        BindingHolder holder = new BindingHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        final Book book = books.get(position);
        holder.getBinding().setVariable(BR.book,book);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }
}
