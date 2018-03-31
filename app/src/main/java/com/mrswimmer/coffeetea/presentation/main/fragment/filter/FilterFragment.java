package com.mrswimmer.coffeetea.presentation.main.fragment.filter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.mrswimmer.coffeetea.data.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class FilterFragment extends BaseFragment implements FilterFragmentView {

    @InjectPresenter
    FilterFragmentPresenter presenter;
    private boolean[] kinds;

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
    @BindView(R.id.filter_check_in_stock)
    CheckBox inStockCheck;
    @BindView(R.id.filter_show_button)
    Button showResultsButton;

    String[] sorts = {"По цене ↑", "По цене ↓", "По рейтингу ↑", "По рейтингу ↓"};

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SpinnerAdapter adapter = new ArrayAdapter<String>(getActivity(), R.layout.sign_up_spinner_item, sorts);
        presenter.setSortSpinner(spinner, adapter);
        updateFilter();

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_filter;
    }

    @OnClick(R.id.filter_choose_kinds)
    void onChooseKindClick() {
        showKindsDialog();
    }

    @OnClick(R.id.filter_show_button)
    void onShowResultsClick() {

    }

    @OnCheckedChanged(R.id.filter_radio_coffee)
    void onTypeChanged() {
        updateFilter();
    }

    @OnCheckedChanged(R.id.filter_check_in_stock)
    void onInStockChanged() {
        updateFilter();
    }

    @OnClick(R.id.filter_show_button)
    void onShowClick() {
        presenter.backToCatalogWithNewList();
    }
    public void showKindsDialog() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getActivity());
        presenter.setBuilder(builder, radioCoffee.isChecked());
    }

    @Override
    public void setCountKinds(boolean[] checked, int countKinds) {
        countKindsText.setText("Сорта: " + countKinds);
        kinds = checked;
        updateFilter();
    }

    @Override
    public void setResultOfFilter(int results) {
        showResultsButton.setText("Показать " + results);
    }

    @Override
    public void updateFilter() {
        int type = radioCoffee.isChecked() ? 0 : 1;
        int sort = spinner.getSelectedItemPosition();
        boolean inSctock = inStockCheck.isChecked();
        boolean[] kinds = this.kinds;
        presenter.updateData(type, sort, inSctock, kinds);
    }
}
