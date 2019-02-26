package com.deliveryapp.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.deliveryapp.FragmentNavigation;
import com.deliveryapp.R;
import com.deliveryapp.login.services.AuthenticationListener;
import com.deliveryapp.login.services.AuthenticationService;

public class RegisterFragment extends Fragment {

    private EditText fname;
    private EditText lname;
    private EditText mail;
    private EditText password;
    private EditText confirmPassword;
    private Button register;
    private TextView error;
    private TextView login;
    private FragmentNavigation fragmentNavigation;
    private AuthenticationService authenticationService = AuthenticationService.getInstance();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentNavigation = (FragmentNavigation)context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.register_fragment, null);

        fname = view.findViewById(R.id.name_et);
        lname = view.findViewById(R.id.lname_et);
        mail = view.findViewById(R.id.mail_et_register);
        password = view.findViewById(R.id.password_et_register);
        confirmPassword = view.findViewById(R.id.conf_password_et_register);
        //        supplier = view.findViewById(R.id.checkbox_supplier);
        //        user = view.findViewById(R.id.checkbox_user);
        register = view.findViewById(R.id.register_btn);
        error = view.findViewById(R.id.error_tv);
        login = view.findViewById(R.id.login_tv);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                error.setVisibility(View.INVISIBLE);
                if(checkMandatoryEditTexts()){
                    if(password.getText().toString().equals(confirmPassword.getText().toString())){
                        authenticationService.register(mail.getText().toString(), password.getText().toString(), new AuthenticationListener<Void>() {
                            @Override
                            public void onSuccess(Void result) {
                                //TODO: Back to LOGIN Fragment
                            }

                            @Override
                            public void onFailure(String message) {
                                error.setText(message);
                                error.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                    else{
                        error.setText(getString(R.string.pas_error));
                        error.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    error.setText(R.string.fields_required);
                    error.setVisibility(View.VISIBLE);
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentNavigation.addFragment(new LoginFragment());
            }
        });
        return view;
    }


    private boolean checkMandatoryEditTexts() {
        return !(lname.getText().toString().isEmpty()
          || fname.getText().toString().isEmpty()
          || mail.getText().toString().isEmpty()
          || password.getText().toString().isEmpty()
          || confirmPassword.getText().toString().isEmpty());

    }
}
