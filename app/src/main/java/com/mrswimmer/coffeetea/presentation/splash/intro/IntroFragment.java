package com.mrswimmer.coffeetea.presentation.splash.intro;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mrswimmer.coffeetea.R;

import agency.tango.materialintroscreen.SlideFragment;

public class IntroFragment extends SlideFragment {
    String text;
    TextView textView;

    public IntroFragment() {
        super();
    }

    @SuppressLint("ValidFragment")
    public IntroFragment(String textView) {
        super();
        this.text = textView;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.intro_slide, container, false);
        textView = view.findViewById(R.id.textintro);
        textView.setText(text);
        return view;
    }

    @Override
    public int backgroundColor() {
        return R.color.black;
    }

    @Override
    public int buttonsColor() {
        return R.color.empty;
    }

    @Override
    public String cantMoveFurtherErrorMessage() {
        return "Ошибка";
    }
}
