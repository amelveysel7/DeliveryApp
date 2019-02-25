package com.deliveryapp.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.deliveryapp.FragmentNavigation;
import com.deliveryapp.R;

public class LoginFragment extends Fragment {

    private EditText mail;
    private EditText password;
    private Button login;
    private TextView register;
    private FragmentNavigation fragmentNavigation;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentNavigation = (FragmentNavigation)context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.login_fragment, null);

        mail = view.findViewById(R.id.email_et);
        password = view.findViewById(R.id.password_et);
        login = view.findViewById(R.id.login_btn);
        register = view.findViewById(R.id.register_tv);
        configureEditTexts();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkMandatoryEditTexts()){
                    //TODO: Login User
                    Toast.makeText(getContext(), "Login okay", Toast.LENGTH_SHORT).show();
                }
                else{
                    //TODO: Show Error
                    Toast.makeText(getContext(), "Login error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentNavigation.addFragment(new RegisterFragment());
            }
        });

        return view;
    }

    private void configureEditTexts(){
        mail.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        password.setImeOptions(EditorInfo.IME_ACTION_DONE);
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    login.performClick();
                    return true;
                }
                return false;
            }
        });
    }

    private boolean checkMandatoryEditTexts(){
        return !(mail.getText().toString().isEmpty() || password.getText().toString().isEmpty());
    }
}
