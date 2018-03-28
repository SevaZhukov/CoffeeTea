package com.mrswimmer.coffeetea.presentation.main.fragment.filter;

import com.arellomobile.mvp.MvpView;

interface FilterFragmentView extends MvpView {
    void setCountKinds(boolean[] checked, int countKinds);
    //Dialog onCreateDialog(int id);
}
