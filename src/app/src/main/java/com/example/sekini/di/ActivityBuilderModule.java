package com.example.sekini.di;


import com.example.sekini.service.SyncService;
import com.example.sekini.ui.dictionary.MainActivity;
import com.example.sekini.ui.dictionary.MainModule;
import com.example.sekini.ui.word.generic.GenericActivity;
import com.example.sekini.ui.word.generic.GenericModule;
import com.example.sekini.ui.word.regularverb.RegularVerbActivity;
import com.example.sekini.ui.word.regularverb.RegularVerbModule;
import com.example.sekini.ui.word.possessednoun.PossessedNounActivity;
import com.example.sekini.ui.word.possessednoun.PossessedNounModule;
import com.example.sekini.utils.base.dialog.YesNoDialog.YesNoDialog;
import com.example.sekini.utils.base.dialog.YesNoNeutral.YesNoNeutralDialog;
import com.example.sekini.utils.base.dialog.prompt.PromptDialog;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector()
    abstract YesNoDialog bindYesNoDialog();

    @ContributesAndroidInjector()
    abstract YesNoNeutralDialog bindYesNoNeutralDialog();


    @ContributesAndroidInjector()
    abstract PromptDialog bindPromptDialog();

    @ContributesAndroidInjector()
    abstract SyncService bindSyncService();


    @ContributesAndroidInjector(modules = GenericModule.class)
    abstract GenericActivity bindGenericActivity();

    @ContributesAndroidInjector(modules = PossessedNounModule.class)
    abstract PossessedNounActivity bindPossessedNounActivity();

    @ContributesAndroidInjector(modules = RegularVerbModule.class)
    abstract RegularVerbActivity bindImpersonalVerbActivity();


}
