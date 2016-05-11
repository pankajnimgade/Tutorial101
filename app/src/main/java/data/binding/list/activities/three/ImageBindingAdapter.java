package data.binding.list.activities.three;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Pankaj Nimgade on 10-05-2016.
 */
public class ImageBindingAdapter {

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }

//    @BindingAdapter("bind:visibilityOnIndex")
    public static void toggleVisibility(View view, int index) {
        view.setVisibility(index > 0 ? View.VISIBLE : View.GONE);
    }
}
