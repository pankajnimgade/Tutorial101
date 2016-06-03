package realm.list.activities.five.gridview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nimgade.pk.tutorial101.BR;
import com.nimgade.pk.tutorial101.R;

import java.util.ArrayList;

/**
 * Created by Pankaj Nimgade on 17-05-2016.
 */
public class MyCityAdapter extends RecyclerView.Adapter<MyCityAdapter.BindingHolder> {

    private static final String TAG = MyCityAdapter.class.getSimpleName();

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<MyCity> myCities;

    public MyCityAdapter(Context context, ArrayList<MyCity> myCities) {
        Log.d(TAG, "MyCityAdapter: " + myCities.size());
        this.context = context;
        layoutInflater = LayoutInflater.from(this.context);
        this.myCities = myCities;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.single_item_realm_grid_view, parent, false);
        BindingHolder bindingHolder = new BindingHolder(view);
        return bindingHolder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        final MyCity myCity = myCities.get(position);
        holder.getBinding().setVariable(BR.my_city, myCity);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return myCities.size();
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
