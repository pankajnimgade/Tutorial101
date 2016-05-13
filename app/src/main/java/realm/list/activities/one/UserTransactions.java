package realm.list.activities.one;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Iterator;

import io.realm.Realm;
import io.realm.RealmResults;
import realm.list.activities.support.realm.database.MyRealmHandler;

/**
 * Created by Pankaj Nimgade on 12-05-2016.
 */
public class UserTransactions {

    private static final String TAG = UserTransactions.class.getSimpleName();

    private Context context;
    private Realm userRealm;

    public UserTransactions(Context context) {
        Log.d(TAG, "UserTransactions: Constructor: ");
        this.context = context;
        this.userRealm = (new MyRealmHandler(this.context)).getModelsRealmDatabase();
    }

    public void saveUserJson(String userJson) {
        Log.d(TAG, "saveUserJson: \n" + userJson);
        if (userJson == null)
            return;
        if (userJson.contentEquals(""))
            return;
        try {
            ArrayList<User> users = (new Gson()).fromJson(userJson, new TypeToken<ArrayList<User>>() {
            }.getType());
            if (users != null) {
                Log.d(TAG, "saveUserJson: trying to save json users:" + users.size());
                saveUsersList(users);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void saveUsersList(ArrayList<User> users) {
        Log.d(TAG, "saveUsersList: Saving...");
        for (User user : users) {
            if (readUserId(user.getId())) {
                Log.d(TAG, "saveUsersList: try updateRecord");
                updateRecord(user);
            } else {
                Log.d(TAG, "saveUsersList: try createRecord");
                createRecord(user);
            }
        }
    }

    /**
     * This methods tries to read a User and notifies if it exist in RealmDatabase of not.
     */
    private boolean readUserId(int id) {
        Log.d(TAG, "readUserId: :" + id);
        UserRealm userRealm = this.userRealm
                .where(UserRealm.class)
                .equalTo("id", id)
                .findFirst();
        return userRealm != null ? true : false;
    }

    /**
     * This would create a record in Realm Database
     */
    private void createRecord(User user) {
        Log.d(TAG, "createRecord: ");
        this.userRealm.beginTransaction();
        UserRealm currentUserRealm = this.userRealm.createObject(UserRealm.class);
        currentUserRealm.setId(user.getId());
        currentUserRealm.setUsername(user.getUsername());
        currentUserRealm.setEmail(user.getEmail());
        currentUserRealm.setPassword_hash(user.getPassword_hash());
        currentUserRealm.setAuth_key(user.getAuth_key());
        currentUserRealm.setConfirmed_at(user.getConfirmed_at());
        currentUserRealm.setUnconfirmed_email(user.getUnconfirmed_email());
        currentUserRealm.setBlocked_at(user.getBlocked_at());
        currentUserRealm.setRegistration_ip(user.getRegistration_ip());
        currentUserRealm.setCreated_at(user.getCreated_at());
        currentUserRealm.setUpdated_at(user.getUpdated_at());
        currentUserRealm.setFlags(user.getFlags());
        currentUserRealm.setRabbitmq_routing_key(user.getRabbitmq_routing_key());
        currentUserRealm.setRabbitmq_channel_name(user.getRabbitmq_channel_name());
        this.userRealm.commitTransaction();
        if (currentUserRealm != null) {
            Log.d(TAG, "createRecord: created");
        } else {
            Log.d(TAG, "createRecord: failed");
        }
    }

    /**
     * This would updated a record in Realm DataBase
     */
    private void updateRecord(User user) {
        Log.d(TAG, "updateRecord: ");
        UserRealm currentUserRealm = this.userRealm
                .where(UserRealm.class)
                .equalTo("id", user.getId())
                .findFirst();
        if (currentUserRealm != null) {
            Log.d(TAG, "updateRecord: user exists so update it:");
            this.userRealm.beginTransaction();
            currentUserRealm.setUsername(user.getUsername());
            currentUserRealm.setEmail(user.getEmail());
            currentUserRealm.setPassword_hash(user.getPassword_hash());
            currentUserRealm.setAuth_key(user.getAuth_key());
            currentUserRealm.setConfirmed_at(user.getConfirmed_at());
            currentUserRealm.setUnconfirmed_email(user.getUnconfirmed_email());
            currentUserRealm.setBlocked_at(user.getBlocked_at());
            currentUserRealm.setRegistration_ip(user.getRegistration_ip());
            currentUserRealm.setCreated_at(user.getCreated_at());
            currentUserRealm.setUpdated_at(user.getUpdated_at());
            currentUserRealm.setFlags(user.getFlags());
            currentUserRealm.setRabbitmq_routing_key(user.getRabbitmq_routing_key());
            currentUserRealm.setRabbitmq_channel_name(user.getRabbitmq_channel_name());
            this.userRealm.commitTransaction();
        } else {
            Log.d(TAG, "updateRecord: Record does not exist:");
            return;
        }
    }


    /**
     * This will return the Iterator<User>
     */
    public Iterator<User> readUserList() {
        Log.d(TAG, "readUserList: ");
        ArrayList<User> users = new ArrayList<>();
        RealmResults<UserRealm> usersRealms = this.userRealm.allObjects(UserRealm.class);
        if (usersRealms != null) {
            for (UserRealm userRealm : usersRealms) {
                User user = new User();
                user.setId(userRealm.getId());
                user.setUsername(userRealm.getUsername());
                user.setEmail(userRealm.getEmail());
                user.setPassword_hash(userRealm.getPassword_hash());
                user.setAuth_key(userRealm.getAuth_key());
                user.setConfirmed_at(userRealm.getConfirmed_at());
                user.setUnconfirmed_email(userRealm.getUnconfirmed_email());
                user.setBlocked_at(userRealm.getBlocked_at());
                user.setRegistration_ip(userRealm.getRegistration_ip());
                user.setCreated_at(userRealm.getCreated_at());
                user.setUpdated_at(userRealm.getUpdated_at());
                user.setFlags(userRealm.getFlags());
                user.setRabbitmq_routing_key(userRealm.getRabbitmq_routing_key());
                user.setRabbitmq_channel_name(userRealm.getRabbitmq_channel_name());
                users.add(user);
            }
        }
        return users.iterator();
    }
}
