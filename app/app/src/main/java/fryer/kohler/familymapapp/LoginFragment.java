package fryer.kohler.familymapapp;

import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.function.Consumer;

import familymapapp.Request.LoginRequestBody;
import familymapapp.Request.RegisterRequestBody;
import familymapapp.Service.LoginService;
import familymapapp.Service.RegisterService;
import familymapapp.Util.Util;


public class LoginFragment extends Fragment {

    private RadioGroup genderInputGroup;
    private RadioButton selectedGenderInput;

    private EditText serverHostInput;
    private EditText serverPortInput;
    private EditText usernameInput;
    private EditText passwordInput;
    private EditText firstNameInput;
    private EditText lastNameInput;
    private EditText emailInput;

    private EditText[] nonEmptyFields;

    private Button registerButton;
    private Button loginButton;

    private View.OnFocusChangeListener onBlurValidate;

    private OnClickListener registerClickListener = new OnClickListener() {
        public void onClick(View view) {

            String serverHost = serverHostInput.getText().toString();
            String serverPort = serverPortInput.getText().toString();
            String username = usernameInput.getText().toString();
            String password = passwordInput.getText().toString();
            String email = emailInput.getText().toString();
            String firstName = firstNameInput.getText().toString();
            String lastName = lastNameInput.getText().toString();

            RegisterRequestBody requestBody = new RegisterRequestBody(
                username,
                password,
                email,
                firstName,
                lastName,
                "f"
            );

            Consumer<String> success = (data) -> {
                String authenticationToken = Util.getValueFromJson(data, "authToken");
                String personId = Util.getValueFromJson(data, "personID");
                LoginFragmentHandler loginHandler = (LoginFragmentHandler) getActivity();
                loginHandler.handleLoginSuccess(personId, authenticationToken);
            };

            Consumer<String> failure = (data) -> {
                Toast.makeText(getActivity(), Util.getValueFromJson(data, "message"), 30000).show();
            };

            RegisterService.register(serverHost, serverPort, requestBody, success, failure);
        }
    };

    private OnClickListener loginClickListener = new OnClickListener() {
        public void onClick(View view) {

            String serverHost = serverHostInput.getText().toString();
            String serverPort = serverPortInput.getText().toString();
            String username = usernameInput.getText().toString();
            String password = passwordInput.getText().toString();

            LoginRequestBody requestBody = new LoginRequestBody(
                    username,
                    password
            );

            Consumer<String> success = (data) -> {
                String authenticationToken = Util.getValueFromJson(data, "authToken");

                String personId = Util.getValueFromJson(data, "personID");
                LoginFragmentHandler loginHandler = (LoginFragmentHandler) getActivity();
                loginHandler.handleLoginSuccess(personId, authenticationToken);

            };

            Consumer<String> failure = (data) -> {
                Toast.makeText(getActivity(), Util.getValueFromJson(data, "message"), 30000).show();
            };

            LoginService.login(serverHost, serverPort, requestBody, success, failure);
        }
    };

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        this.serverHostInput =  (EditText) view.findViewById(R.id.server_host_input);
        this.serverPortInput =  (EditText) view.findViewById(R.id.server_port_input);
        this.usernameInput =  (EditText) view.findViewById(R.id.username_input);
        this.passwordInput =  (EditText) view.findViewById(R.id.password_input);
        this.firstNameInput =  (EditText) view.findViewById(R.id.first_name_input);
        this.lastNameInput =  (EditText) view.findViewById(R.id.last_name_input);
        this.emailInput = (EditText) view.findViewById(R.id.email_input);

        this.genderInputGroup = (RadioGroup) view.findViewById(R.id.gender_input_group);
        this.selectedGenderInput = (RadioButton) view.findViewById(genderInputGroup.getCheckedRadioButtonId());

        registerButton = (Button) view.findViewById(R.id.register_button);
        loginButton = (Button) view.findViewById(R.id.login_button);

        EditText[] registerFields = new EditText[]{
            this.serverHostInput,
            this.serverPortInput,
            this.usernameInput,
            this.passwordInput,
            this.firstNameInput,
            this.lastNameInput,
            this.emailInput
        };

        EditText[] loginFields = new EditText[]{
                this.serverHostInput,
                this.serverPortInput,
                this.usernameInput,
                this.passwordInput
        };


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                registerButton.setEnabled(allTextViewInputsNonEmpty(registerFields));
                loginButton.setEnabled(allTextViewInputsNonEmpty(loginFields));
            }
        };

        this.serverHostInput.addTextChangedListener(textWatcher);
        this.serverPortInput.addTextChangedListener(textWatcher);
        this.usernameInput.addTextChangedListener(textWatcher);
        this.passwordInput.addTextChangedListener(textWatcher);
        this.firstNameInput.addTextChangedListener(textWatcher);
        this.lastNameInput.addTextChangedListener(textWatcher);
        this.emailInput.addTextChangedListener(textWatcher);


        registerButton.setOnClickListener(this.registerClickListener);
        loginButton.setOnClickListener(this.loginClickListener);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private boolean allTextViewInputsNonEmpty(TextView[] textViews){
        boolean allNotEmpty = true;
        for(TextView textView : textViews){
            if(this.stringIsEmpty(textView.getText().toString()))
                allNotEmpty = false;
        }
        return allNotEmpty;
    }

    private boolean stringIsEmpty(String string){
        return string.matches("");
    }

    public interface LoginFragmentHandler {
        public void handleLoginSuccess(String personId, String authenticationToken);
    }

}

