package com.example.guessthecharacterv2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    DatabaseHelper dbHelper;
    TextView tvRegister;
    EditText etName;
    EditText etPassword;
    Button loginToMainScreen;
    Button loginButton;
    View view;

    //comes with fragment
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //comes with fragment
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */

    //comes with fragment
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    //comes with fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);
        dbHelper = new DatabaseHelper(this.getContext());
        tvRegister = view.findViewById(R.id.tvRegister);
        etName = view.findViewById(R.id.etName);
        etPassword = view.findViewById(R.id.etPassword);
        loginButton = view.findViewById(R.id.loginButton);

        //login button to enter your user to and play the game
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check if user and password entered are correct
                String userName = etName.getText().toString().trim();
                String userPsd = etPassword.getText().toString().trim();
                // Validate login and password
                if (dbHelper.validateLogin(userName, userPsd)) {
                    Toast.makeText(getContext(), "Login successful!", Toast.LENGTH_SHORT).show();
                    //Call PlayGame activity
                    Intent intent = new Intent(getActivity(), ChooseLevel.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "Invalid username or password!", Toast.LENGTH_SHORT).show();
                    etName.setError("Invalid username or password!");
                    etPassword.setError("Invalid username or password!");
                }

            }
        });
        //A button to go to register fragment
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the RegisterFragment
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new RegisterFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }






}
