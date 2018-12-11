package th.ac.kmitl.a59070178;

import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

public class LoginFragment extends Fragment {

    private SQLiteDatabase database;
    private ContentValues cv;
    SharedPreferences sp;



    @Nullable
    @Override
    public View onCreateView
            (@NonNull LayoutInflater inflater,
             @Nullable ViewGroup container,
             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login, container, false);
    }

    @Override
    public void onActivityCreated
            (@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SharedPreferences get = getContext().getSharedPreferences("user_login",Context.MODE_PRIVATE);
        if (get.getString("id",null) != null){
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_view, new HomeFragment())
                    .commit();
        }

        initLoginBtn();
        initRegisterBtn();
        }


    void initLoginBtn(){
        Button _loginBtn = getView().findViewById(R.id.login_btn);
        _loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText _userId = (EditText) getView().findViewById(R.id.login_userid);
                EditText _password = (EditText) getView().findViewById(R.id.login_password);
                String _userIdStr = _userId.getText().toString();
                String _passwordStr = _password.getText().toString();

                String cPsw;


                if (_userIdStr.isEmpty() || _passwordStr.isEmpty()) {
                    Toast.makeText(
                            getActivity(),
                            "Please fill out this form",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER", "USER OR PASSWORD IS EMPTY");

                }else {
                    //open to use db
                    database = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);


//
                    String sql = "SELECT name FROM sqlite_master WHERE type = 'table'";
                    Cursor db_query = database.rawQuery(sql,null);

//                    Cursor db_query = database.rawQuery("SELECT DISTINCT FROM my.db WHERE password = "+_passwordStr, null);


                    int chk = 0;
                    while (db_query.moveToNext()){
                        if (db_query.getString(0).equals(_userIdStr)) {
                            chk = 1;
                        }


                    }

                    if (chk == 1){
                        Cursor s = database.rawQuery("SELECT * FROM "+_userIdStr, null);

                        while(s.moveToNext()) {
                            String f = s.getString(2);
                            String l = s.getString(3);

                            SharedPreferences.Editor sp = getContext().getSharedPreferences("user_login",Context.MODE_PRIVATE).edit();
                            sp.putString("id",_userIdStr).apply();
                            sp.putString("fname",f).apply();
                            sp.putString("lname",l).apply();
                            sp.commit();
                        }



                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_view, new HomeFragment())
                                .addToBackStack(null)
                                .commit();
                    }else {
                        Toast.makeText(
                                getActivity(),
                                "Invalid user or password”",
                                Toast.LENGTH_SHORT
                        ).show();
                    }


                    db_query.close();

                }


            }
        });
    }
    void initRegisterBtn() {
        TextView _registerBtn = (TextView) getView().findViewById(R.id.login_register);
        _registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LOGIN", "go to register");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
                Toast.makeText(
                        getActivity(),
                        "go to register",
                        Toast.LENGTH_SHORT
                ).show();

            }
        });
    }

}
