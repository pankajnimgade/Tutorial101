package realm.list.activities.five.gridview;

import io.realm.RealmObject;

/**
 * Created by Pankaj Nimgade on 18-05-2016.
 */
public class City extends RealmObject {

    private String name;
    private String votes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }
}
