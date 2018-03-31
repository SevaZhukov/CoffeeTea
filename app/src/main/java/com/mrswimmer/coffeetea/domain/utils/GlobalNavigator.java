package com.mrswimmer.coffeetea.domain.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.mrswimmer.coffeetea.data.settings.Screens;
import com.mrswimmer.coffeetea.presentation.main.activity.MainActivity;
import com.mrswimmer.coffeetea.presentation.splash.intro.IntroActivity;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Forward;

public class GlobalNavigator implements Navigator {
    Activity activity;

    public GlobalNavigator(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void applyCommand(Command command) {
        Log.i("code", command.getClass() + "");
        if (command instanceof Forward) {
            switch (((Forward) command).getScreenKey()) {
                case Screens.AUTH_ACTIVITY:
                    activity.overridePendingTransition(0,0);
                    TaskStackBuilder.create(activity)
                            .addNextIntentWithParentStack(new Intent(activity, MainActivity.class))
                            .addNextIntent(new Intent(activity, IntroActivity.class))
                            .startActivities();
                    activity.finish();
                    break;
                case Screens.MAIN_ACTIVITY:
                    activity.overridePendingTransition(0,0);
                    Intent i = new Intent(activity, MainActivity.class);
                    activity.startActivity(i);
                    activity.finish();
                    break;
            }
        }
    }
}
