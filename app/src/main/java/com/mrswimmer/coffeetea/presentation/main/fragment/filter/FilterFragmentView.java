package com.mrswimmer.coffeetea.presentation.main.fragment.filter;

import com.arellomobile.mvp.MvpView;

interface FilterFragmentView extends MvpView {
    void setCountKinds(boolean[] checked, int countKinds);

    void setResultOfFilter(int results);

    void updateFilter();
    //Dialog onCreateDialog(int id);
}
