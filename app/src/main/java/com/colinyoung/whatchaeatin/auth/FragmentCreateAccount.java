package com.colinyoung.whatchaeatin.auth;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.colinyoung.whatchaeatin.R;

public class FragmentCreateAccount extends Fragment {

    private UserCreator userCreator;
    private TextView textMessage;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof UserCreator) {
            userCreator = (UserCreator)getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_account, container, false);

        final EditText fieldEmail = view.findViewById(R.id.createacct_edittext_email);
        final EditText fieldPassword = view.findViewById(R.id.createacct_edittext_password);
        final Button buttonCreate = view.findViewById(R.id.createacct_button_create);
        textMessage = view.findViewById(R.id.createacct_text_message);

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (userCreator != null) {
                    userCreator.createUser(fieldEmail.getText().toString(), fieldPassword.getText().toString());
                }
            }
        });

        return view;
    }

    public void setErrorState(String message) {

        textMessage.setText(message);
    }

    public void clearErrorState() {

        textMessage.setText(null);
    }

    interface UserCreator {
        void createUser(String email, String password);
    }
}
