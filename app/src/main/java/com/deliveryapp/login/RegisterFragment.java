package com.deliveryapp.login;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.deliveryapp.R;

public class RegisterFragment extends Fragment {
    @Nullable
    @Override
    public View getView() {
        View view = getLayoutInflater().inflate(R.layout.register_fragment, null);


        return view;
    }
}
