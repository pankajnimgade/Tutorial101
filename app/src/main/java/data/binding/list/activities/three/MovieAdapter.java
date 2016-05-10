package data.binding.list.activities.three;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nimgade.pk.tutorial101.BR;
import com.nimgade.pk.tutorial101.R;

import java.util.ArrayList;

/**
 * Created by Pankaj Nimgade on 10-05-2016.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.BindingHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Movie> movies;

    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
        this.layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.single_item_data_binding_three, parent, false);
        BindingHolder bindingHolder = new BindingHolder(view);
        return bindingHolder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        Movie movie = this.movies.get(position);
        holder.getBinding().setVariable(BR.movie, movie);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return movies.size();
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
