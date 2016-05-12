package data.binding.list.activities.four.imageview.data.binding;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nimgade.pk.tutorial101.R;

import com.nimgade.pk.tutorial101.BR;


import java.util.ArrayList;

/**
 * Created by Pankaj Nimgade on 10-05-2016.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.BindingHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<MyUser> users;

    public UserAdapter(Context context, ArrayList<MyUser> users) {
        this.context = context;
        this.users = users;
        layoutInflater = LayoutInflater.from(this.context);
        Log.d("UserAdapter", "UserAdapter:users: " + users.size());
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.single_item_data_binding_four, parent, false);
        BindingHolder bindingHolder = new BindingHolder(view);
        return bindingHolder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        final MyUser user = users.get(position);
        holder.getBinding().setVariable(BR.my_user, user);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return users.size();
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
