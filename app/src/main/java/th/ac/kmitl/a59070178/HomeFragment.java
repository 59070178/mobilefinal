package th.ac.kmitl.a59070178;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class HomeFragment extends Fragment {

    SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView
            (@NonNull LayoutInflater inflater,
             @Nullable ViewGroup container,
             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home, container, false);
    }

    @Override
    public void onActivityCreated
            (@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView n = getView().findViewById(R.id.name);
        TextView q = getView().findViewById(R.id.quote);
        sp = getContext().getSharedPreferences("user_login",Context.MODE_PRIVATE);

        String name = "HELLO " + sp.getString("fname","a") + " " +  sp.getString("lname","a");

        n.setText(name);
       q.setText("This is my quote");

        profile();
        singout();
        friend();

    }

    void profile(){
        Button p = getView().findViewById(R.id.pro_btn);
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.main_view, new ProfileFragment())
                        .commit();
            }
        });
    }

    void friend(){
        Button f = getView().findViewById(R.id.friend_btn);
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.main_view, new FriendFragment())
                        .commit();
            }
        });
    }

    void singout(){
        Button s = getView().findViewById(R.id.singout);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor get = getContext().getSharedPreferences("user_login",Context.MODE_PRIVATE).edit();
                get.clear();

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new LoginFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

}
