package data.binding.list.activities.four.imageview.data.binding;

import android.content.Context;
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
public class MyNoteAdapter extends RecyclerView.Adapter<MyNoteAdapter.BindingHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<MyNote> myNotes;

    public MyNoteAdapter(Context context, ArrayList<MyNote> myNotes) {
        this.context = context;
        this.myNotes = myNotes;
        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.single_item_data_binding_four, parent, false);
        BindingHolder bindingHolder = new BindingHolder(view);
        return bindingHolder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        final MyNote myNote = myNotes.get(position);
        holder.getBinding().setVariable(BR.myNote, myNote);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return myNotes.size();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }
}
