package com.mrswimmer.coffeetea.presentation.main.fragment.filter;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.coffeetea.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilterFragment extends MvpAppCompatFragment implements FilterFragmentView {

    @InjectPresenter
    FilterFragmentPresenter presenter;

    @ProvidePresenter
    public FilterFragmentPresenter presenter() {
        return new FilterFragmentPresenter();
    }

    @BindView(R.id.filter_radio_coffee)
    RadioButton radioCoffee;
    @BindView(R.id.filter_radio_tea)
    RadioButton radioTea;
    @BindView(R.id.filter_spinner_sort)
    Spinner spinner;
    @BindView(R.id.filter_choose_kinds)
    Button chooseKindButton;
    @BindView(R.id.filter_count_kinds_text)
    TextView countKindsText;
    @BindView(R.id.filter_check_in_my_city)
    CheckBox inMyCityCheck;
    @BindView(R.id.filter_check_in_stock)
    Button inStockCheck;

    String[] sorts = {"По цене ↑", "По цене ↓", "По весу ↑", "По весу ↓", "По рейтингу ↑", "По рейтингу ↓"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_filter, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        SpinnerAdapter adapter = new ArrayAdapter<String>(getActivity(), R.layout.sign_up_spinner_item, sorts);
        presenter.setSortSpinner(spinner, adapter);
    }

    @OnClick(R.id.filter_choose_kinds)
    void onChooseKindClick() {
        showDialog();
        //onCreateDialog(0);
    }

    @OnClick(R.id.filter_show_button)
    void onShowResultsClick() {

    }

    public void showDialog() {

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getActivity());
        presenter.setBuilder(builder, radioCoffee.isChecked());

    }

    @Override
    public void setCountKinds(int countKinds) {
        countKindsText.setText(countKinds + "");
    }
}
