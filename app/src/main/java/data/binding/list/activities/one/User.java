package data.binding.list.activities.one;

import android.databinding.BaseObservable;

/**
 * Created by Pankaj Nimgade on 09-05-2016.
 */
public class User extends BaseObservable {

    private String first_name;
    private String last_name;

    public User(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
