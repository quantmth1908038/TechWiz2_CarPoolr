package com.techwiz2.carpoolr.activity.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.techwiz2.carpoolr.R;
import com.techwiz2.carpoolr.OnClickListener.StartonClickListener;


public class FragmentBlank3 extends Fragment {

    public TextView tvGetStart;

    private StartonClickListener startonClickListener;

    public void setStartonClickListener(StartonClickListener startonClickListener) {
        this.startonClickListener = startonClickListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @NonNull ViewGroup container,
                             @Nullable Bundle saveInstanceState){
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_blank3,container,false);

        tvGetStart = rootView.findViewById(R.id.tvGetStart);

        tvGetStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startonClickListener.onClickListenerFragment(v);
            }
        });

        return  rootView;
    }
}
