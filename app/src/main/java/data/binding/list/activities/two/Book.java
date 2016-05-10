package data.binding.list.activities.two;

import android.databinding.BaseObservable;

/**
 * Created by Pankaj Nimgade on 09-05-2016.
 */
public class Book extends BaseObservable {

    private String title;

    private String autor;

    public Book(String title, String autor) {
        this.title = title;
        this.autor = autor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
