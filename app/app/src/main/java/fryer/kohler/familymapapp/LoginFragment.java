package fryer.kohler.familymapapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import familymapapp.Request.PersonRequestBody;
import familymapapp.Request.RegisterRequestBody;
import familymapapp.Service.LoginService;
import familymapapp.Service.PersonService;
import familymapapp.Service.RegisterService;
import familymapapp.UTIL.Util;


public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//    // The request code must be 0 or greater.
//    private static final int PLUS_ONE_REQUEST_CODE = 0;
//    // The URL to +1.  Must be a valid URL.
//    private final String PLUS_ONE_URL = "http://developer.android.com";
//    // TODO: Rename and change types of parameters


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
                //TODO use response object
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
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
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

        registerButton.setEnabled(false);
        loginButton.setEnabled(false);

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

        // Refresh the state of the +1 button each time the activity receives focus.
        //mPlusOneButton.initialize(PLUS_ONE_URL, PLUS_ONE_REQUEST_CODE);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
////        if (context instanceof OnFragmentInteractionListener) {
////            mListener = (OnFragmentInteractionListener) context;
////        } else {
////            throw new RuntimeException(context.toString()
////                    + " must implement OnFragmentInteractionListener");
////        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }



    private boolean allTextViewInputsNonEmpty(TextView[] textViews){
        boolean allNotEmpty = true;
        for(TextView textView : textViews){
            if(this.stringIsEmpty(textView.getText().toString()))
                allNotEmpty = false;
        }
        return allNotEmpty;
    }

    //TODO PUT THIS IN A UTIL CLASS
    private boolean stringIsEmpty(String string){
        return string.matches("");
    }

    public interface LoginFragmentHandler {
        public void handleLoginSuccess(String personId, String authenticationToken);
    }

}


/**
 * Use this factory method to create a new instance of
 * this fragment using the provided parameters.
 *
 * @param param1 Parameter 1.
 * @param param2 Parameter 2.
 * @return A new instance of fragment LoginFragment.
 */
// TODO: Rename and change types and number of parameters
//    public static LoginFragment newInstance() {
//        LoginFragment fragment = new LoginFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//   }


/**
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 * <p>
 * See the Android Training lesson <a href=
 * "http://developer.android.com/training/basics/fragments/communicating.html"
 * >Communicating with Other Fragments</a> for more information.
 */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
