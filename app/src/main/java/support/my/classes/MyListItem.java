package support.my.classes;

/**
 * Created by Pankaj Nimgade on 09-05-2016.<p>
 * This class is used in different ListView and show underling data.
 */
public class MyListItem {

    private String activity_Name;

    private Class activity_Class;

    public MyListItem(String class_Name, Class activity_Class) {
        this.activity_Name = class_Name;
        this.activity_Class = activity_Class;
    }

    public String getActivity_Name() {
        return activity_Name;
    }

    public Class getActivity_Class() {
        return activity_Class;
    }

    @Override
    public String toString() {
        return "" + activity_Name;
    }
}
