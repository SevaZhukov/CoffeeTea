package com.mrswimmer.coffeetea.presentation.main.fragment.settings;

import android.support.v7.app.AlertDialog;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.presentation.base.BaseFragment;

import butterknife.OnClick;

public class SettingsFragment extends BaseFragment implements SettingsFragmentView {
    @InjectPresenter
    SettingsFragmentPresenter presenter;

    @ProvidePresenter
    public SettingsFragmentPresenter presenter() {
        return new SettingsFragmentPresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_settings;
    }

    @OnClick(R.id.settings_sign_out)
    void onSignOutClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        presenter.signOut(builder);
    }

    @OnClick(R.id.settings_about_version)
    void onAboutVersionClick() {
        showDialog("О версии", "Что-то");
    }

    @OnClick(R.id.settings_mark)
    void onMarkClick() {
        presenter.setMark();
    }

    @OnClick(R.id.settings_about_company)
    void onAboutCompanyClick() {
        presenter.gotoAboutCompany();
    }
    @OnClick(R.id.settings_instruction)
    void onInstructionClick() {
        presenter.gotoInstruction();
    }
}
