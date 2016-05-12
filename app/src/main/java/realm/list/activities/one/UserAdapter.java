package realm.list.activities.one;

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
 * Created by Pankaj Nimgade on 12-05-2016.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.BindingHolder> {

    private Context context;
    private ArrayList<User> users;
    private LayoutInflater layoutInflater;

    public UserAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
        this.layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.single_item_realm_binding_one, parent, false);
        BindingHolder bindingHolder = new BindingHolder(view);
        return bindingHolder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        final User user = users.get(position);
        holder.getBinding().setVariable(BR.one_user, user);
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
