package com.openmobility.bike14;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

public class LoadFragment extends Fragment {

    ProgressBar progressBar;
    String address;

    public void setAddress(String address) {
        this.address = address;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.load_fragment, container, false);
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startLoader();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void startLoader(){
        final int tempoDeEspera = 3000;
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(tempoDeEspera);

                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q="+address));
                getActivity().startActivity(intent);
                getFragmentManager().popBackStack();
            }
        });
    }
}
