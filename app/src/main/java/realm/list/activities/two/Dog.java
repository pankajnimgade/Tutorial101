package realm.list.activities.two;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by Pankaj Nimgade on 14-05-2016.
 * // Define you model class by extending the RealmObject
 */
public class Dog extends RealmObject {
    @Required // Name cannot be null
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
