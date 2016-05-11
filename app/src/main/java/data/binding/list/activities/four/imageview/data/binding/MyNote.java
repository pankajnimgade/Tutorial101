package data.binding.list.activities.four.imageview.data.binding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.nimgade.pk.tutorial101.BR;

/**
 * Created by Pankaj Nimgade on 10-05-2016.
 */
public class MyNote extends BaseObservable {

    private String image_url;
    private String note_name;

    public MyNote() {
    }

    public MyNote(String image_url, String note_name) {
        this.image_url = image_url;
        this.note_name = note_name;
    }

    @Bindable
    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
        notifyPropertyChanged(BR.image_url);
    }

    @Bindable
    public String getNote_name() {
        return note_name;
    }

    public void setNote_name(String note_name) {
        this.note_name = note_name;
        notifyPropertyChanged(BR.note_name);
    }
}
