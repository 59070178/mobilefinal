package th.ac.kmitl.a59070178;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class FriendAdapter extends ArrayAdapter {

    private ArrayList<JSONObject> postList;
    private Context context;

    public FriendAdapter(Context context, int resource, ArrayList<JSONObject> objects)
    {
        super(context, resource, objects);
        this.postList = objects;
        this.context = context;
    }

    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {


        View postItem  = LayoutInflater.from(context)
                .inflate(R.layout.friend_item,parent,false);

        JSONObject postObj = postList.get(position);

        TextView id_title = (TextView) postItem.findViewById(R.id.post_id_title);
        TextView email = (TextView) postItem.findViewById(R.id.f_email);
        TextView p = (TextView) postItem.findViewById(R.id.f_phone);
        TextView w = (TextView) postItem.findViewById(R.id.f_web);


        try
        {
            id_title.setText(postObj.getString("id") + " : " + postObj.getString("name"));
            email.setText(postObj.getString("email"));
            p.setText(postObj.getString("phone"));
            w.setText(postObj.getString("website"));
        }
        catch (JSONException e)
        {
            Log.d("Posts", "catch JSONException : " + e.getMessage());
        }
        return postItem;

    }
}
