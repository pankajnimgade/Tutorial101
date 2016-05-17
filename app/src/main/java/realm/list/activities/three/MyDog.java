package realm.list.activities.three;

import io.realm.RealmObject;

/**
 * Created by Pankaj Nimgade on 16-05-2016.
 */
public class MyDog extends RealmObject {

    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
