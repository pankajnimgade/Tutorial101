package realm.list.activities.five.gridview;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by Pankaj Nimgade on 17-05-2016.
 */
public class MyCity extends BaseObservable {

    private String cityName;
    private String vote;

    public MyCity(String cityName, String vote) {
        this.cityName = cityName;
        this.vote = vote;
    }

    @Bindable
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Bindable
    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;

    }
}
