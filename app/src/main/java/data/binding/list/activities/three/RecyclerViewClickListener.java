package data.binding.list.activities.three;

import android.view.View;

/**
 * Created by Pankaj Nimgade on 11-05-2016.
 */
public interface RecyclerViewClickListener {

    /**
     * Callback method to be invoked when a item in a
     * RecyclerView is clicked
     *
     * @param v        The view within the RecyclerView.Adapter
     * @param position The position of the view in the adapter
     * @param x
     * @param y
     */
    void onClick(View v, int position, float x, float y);
}
