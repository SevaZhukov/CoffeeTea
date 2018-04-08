package com.mrswimmer.coffeetea.presentation.main.fragment.settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.data.settings.Screens;
import com.mrswimmer.coffeetea.data.settings.Settings;
import com.mrswimmer.coffeetea.di.qualifier.Global;
import com.mrswimmer.coffeetea.di.qualifier.Local;
import com.mrswimmer.coffeetea.domain.service.FireService;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class SettingsFragmentPresenter extends MvpPresenter<SettingsFragmentView> {
    @Inject
    @Local
    Router router;

    @Inject
    @Global
    Router globalRouter;

    @Inject
    @Local
    Router localRouter;

    @Inject
    FireService fireService;

    @Inject
    SharedPreferences settings;
    public SettingsFragmentPresenter() {
        App.getComponent().inject(this);
    }

    public void setMark() {
        globalRouter.navigateTo(Screens.SET_MARK_IN_GOOGLE_PLAY);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=com.mrswimmer.coffeetea"));
    }

    public void clearPrefs() {
        fireService.signOut();
        globalRouter.navigateTo(Screens.AUTH_ACTIVITY);
    }

    public void signOut(AlertDialog.Builder builder) {
        builder.setTitle("Выход из аккаунта")
                .setMessage("Вы действительно хотите выйти из аккаунта?")
                .setPositiveButton("Да", (dialog, which) -> {
                    clearPrefs();
                }).setNegativeButton("Нет", (dialog, which) -> dialog.cancel());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void gotoAboutCompany() {
        localRouter.navigateTo(Screens.ABOUT_COMPANY, R.string.about_company);
    }

    public void gotoInstruction() {
        localRouter.navigateTo(Screens.INSTRUCTION, R.string.instruction);
    }
}
