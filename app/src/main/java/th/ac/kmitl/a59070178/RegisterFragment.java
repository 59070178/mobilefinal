package th.ac.kmitl.a59070178;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterFragment extends Fragment {

    private SQLiteDatabase database;
    private ContentValues cv;
    SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView
            (@NonNull LayoutInflater inflater,
             @Nullable ViewGroup container,
             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register, container, false);
    }

    @Override
    public void onActivityCreated
            (@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cv = new ContentValues();
        initRegisterBtn();

    }

    void initRegisterBtn() {
        Button _regBtn = getView().findViewById(R.id.reg_register);
        _regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int chk = 0;
                EditText id = getView().findViewById(R.id.reg_id);
                EditText name = getView().findViewById(R.id.reg_name);
                EditText age = getView().findViewById(R.id.reg_age);
                EditText psw = getView().findViewById(R.id.reg_password);

                String idStr = id.getText().toString();
                String nameStr = name.getText().toString();
                String ageStr = age.getText().toString();
                String pswStr = psw.getText().toString();

                int cs = 0;

                //                else if (cs == 0){
                    for (int i = 0 ; i < nameStr.length(); i++) {
                        char n = nameStr.charAt(i);
                        if (n == ' ') {
                            cs++;
                        }
                    }
//                }

                for (int i = 0 ; i < ageStr.length(); i++) {
                    char c = ageStr.charAt(i);
                    if (c < '0' || c > '9') {
                        chk = 0;
                    }else chk = 1;
                }
                if (idStr.isEmpty() || nameStr.isEmpty() || ageStr.isEmpty() || pswStr.isEmpty()){
                    Toast.makeText(
                            getActivity(),
                            "Please fill out this form",
                            Toast.LENGTH_SHORT
                    ).show();
                }

                else if (idStr.length() < 6 || idStr.length() > 12){
                    Toast.makeText(
                            getActivity(),
                            "User Id Should Lenge 6 - 12",
                            Toast.LENGTH_SHORT
                    ).show();
                }else if (!nameStr.contains(" ")){

                    Toast.makeText(
                            getActivity(),
                            "Name is in correct",
                            Toast.LENGTH_SHORT
                    ).show();
                }

                else if (pswStr.length() < 6){
                    Toast.makeText(
                            getActivity(),
                            "Password Should Lenge more 6",
                            Toast.LENGTH_SHORT
                    ).show();
                }
                else if (chk == 0) {
                    Toast.makeText(
                            getActivity(),
                            "Age must be int",
                            Toast.LENGTH_SHORT
                    ).show();
                }else if (Integer.parseInt(ageStr)<10 || Integer.parseInt(ageStr)>80){
                    Toast.makeText(
                            getActivity(),
                            "Age  Should Lenge 10 - 80",
                            Toast.LENGTH_SHORT
                    ).show();
                }



                else {

                    if (cs == 1){
                        /// SQL
                        String[] separated = nameStr.split(" ");
                        String n1  = separated[0];
                        String n2 = separated[1];

                        database = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);

                        database.execSQL(
                                "CREATE TABLE IF NOT EXISTS " + idStr+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT, id VARCHAR(10),fName VARCHAR(10), lName VARCHAR(10),age VARCHAR(3), password VARCHAR(20))"
                        );

                        User item = new User();

                        item.setContent(idStr,n1,n2,ageStr,pswStr);

                        cv = item.getContent();

                        database.insert(idStr, null, cv);

                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_view, new LoginFragment())
                                .addToBackStack(null)
                                .commit();

                        Toast.makeText(
                                getActivity(),
                                "Save Success",
                                Toast.LENGTH_SHORT
                        ).show();
                    }else {
                        Toast.makeText(
                                getActivity(),
                                "name must contain one space",
                                Toast.LENGTH_SHORT
                        ).show();
                    }


                }


            }
        });

    }
}
