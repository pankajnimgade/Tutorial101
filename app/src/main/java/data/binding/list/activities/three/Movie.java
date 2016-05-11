package data.binding.list.activities.three;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;
import com.nimgade.pk.tutorial101.BR;
import com.nimgade.pk.tutorial101.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Pankaj Nimgade on 10-05-2016.
 */
public class Movie extends BaseObservable {

    @SerializedName("title")
    private String title;

    @SerializedName("image")
    private String image;

    @SerializedName("rating")
    private String rating;

    @SerializedName("releaseYear")
    private String releaseYear;

    @SerializedName("genre")
    private ArrayList<String> genre;

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        notifyPropertyChanged(BR.image);
    }

    @Bindable
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
        notifyPropertyChanged(BR.rating);
    }

    @Bindable
    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
        notifyPropertyChanged(BR.releaseYear);
    }

    @Bindable
    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
        notifyPropertyChanged(BR.genre);
    }

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.pic)
                .into(view);
    }
}
