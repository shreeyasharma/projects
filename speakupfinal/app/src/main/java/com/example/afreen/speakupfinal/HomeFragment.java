package com.example.afreen.speakupfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    private ImageView profile_pic = null;
    private TextView tv = null;
    private Button logoutButton = null;
    private Button button = null;
    private Profile profile = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);
        profile_pic = (ImageView) view.findViewById(R.id.profile_pic);
        tv = (TextView) view.findViewById(R.id.tv);
        logoutButton = (Button) view.findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager
                        .beginTransaction();
                fragmentTransaction.replace(R.id.mainContainer, new LoginFragment());
                fragmentTransaction.commit();

            }
        });
        button=(Button)view.findViewById(R.id.press2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),ph.class);
  startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();

        if (bundle != null) {
            profile = (Profile) bundle.getParcelable(LoginFragment.PARCEL_KEY);
        } else {
            profile = Profile.getCurrentProfile();
        }


        tv.setText("Welcome \n" + profile.getName());

        Picasso.with(getActivity())
                .load(profile.getProfilePictureUri(400, 400).toString())
                .into(profile_pic);

    SessionManager sessionManager=new SessionManager(getContext());

        sessionManager.createLoginSession(profile.getName(),profile.getProfilePictureUri(400, 400).toString());


    }

    private void logout() {
        LoginManager.getInstance().logOut();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(R.id.mainContainer, new LoginFragment());
        fragmentTransaction.commit();
    }


}
