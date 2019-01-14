package com.example.sekini.di;


import com.example.sekini.service.SyncService;
import com.example.sekini.ui.dialog.correct.CorrectDialog;
import com.example.sekini.ui.dialog.correct.CorrectDialogModule;
import com.example.sekini.ui.dialog.image.ImageDialog;
import com.example.sekini.ui.dialog.image.ImageDialogModule;
import com.example.sekini.ui.dialog.incorrect.IncorrectDialog;
import com.example.sekini.ui.dialog.incorrect.IncorrectDialogModule;
import com.example.sekini.ui.dialog.loading.LoadingDialog;
import com.example.sekini.ui.dialog.loading.LoadingDialogModule;
import com.example.sekini.ui.dictionary.DictionaryFragment;
import com.example.sekini.ui.dictionary.DictionaryModule;
import com.example.sekini.ui.games.game1.Game1Fragment;
import com.example.sekini.ui.games.game1.Game1Module;
import com.example.sekini.ui.games.game1.fragment.Game1ItemFragment;
import com.example.sekini.ui.games.game1.fragment.Game1ItemModule;
import com.example.sekini.ui.games.game2.Game2Fragment;
import com.example.sekini.ui.games.game2.Game2Module;
import com.example.sekini.ui.games.game2.fragment.Game2ItemFragment;
import com.example.sekini.ui.games.game2.fragment.Game2ItemModule;
import com.example.sekini.ui.login.LoginFragment;
import com.example.sekini.ui.login.LoginModule;
import com.example.sekini.ui.main.MainActivity;
import com.example.sekini.ui.main.MainModule;
import com.example.sekini.ui.main.fragment.MainFragment;
import com.example.sekini.ui.main.fragment.MainFragmentModule;
import com.example.sekini.ui.main.fragment.MainViewModel;
import com.example.sekini.ui.mainbottom.MainBottomActivity;
import com.example.sekini.ui.mainbottom.MainBottomModule;
import com.example.sekini.ui.mainbottom.MainBottomViewModel;
import com.example.sekini.ui.word.generic.GenericFragment;
import com.example.sekini.ui.word.generic.GenericModule;
import com.example.sekini.ui.word.possessednoun.PossessedNounFragment;
import com.example.sekini.ui.word.regularverb.RegularVerbFragment;
import com.example.sekini.ui.word.regularverb.RegularVerbModule;
import com.example.sekini.ui.word.possessednoun.PossessedNounModule;
import com.example.sekini.ui.word.regularverb.fragment.TenseFragment;
import com.example.sekini.ui.word.regularverb.fragment.TenseModule;
import com.example.sekini.utils.base.dialog.YesNoDialog.YesNoDialog;
import com.example.sekini.utils.base.dialog.YesNoNeutral.YesNoNeutralDialog;
import com.example.sekini.utils.base.dialog.prompt.PromptDialog;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {


    @ContributesAndroidInjector(modules = MainFragmentModule.class)
    abstract MainFragment bindMainFragment();

    @ContributesAndroidInjector(modules = ImageDialogModule.class)
    abstract ImageDialog bindImageDialog();

    @ContributesAndroidInjector(modules = LoadingDialogModule.class)
    abstract LoadingDialog bindLoadingDialog();


    @ContributesAndroidInjector(modules = Game2Module.class)
    abstract Game2Fragment bindGame2Fragment();

    @ContributesAndroidInjector(modules = Game2ItemModule.class)
    abstract Game2ItemFragment bindGame2ItemFragment();

    @ContributesAndroidInjector(modules = Game1ItemModule.class)
    abstract Game1ItemFragment bindGame1ItemFragment();


    @ContributesAndroidInjector(modules = CorrectDialogModule.class)
    abstract CorrectDialog bindCorrectDialog();


    @ContributesAndroidInjector(modules = IncorrectDialogModule.class)
    abstract IncorrectDialog bindIncorrectDialog();

    @ContributesAndroidInjector(modules = Game1Module.class)
    abstract Game1Fragment bindGame1Fragment();

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginFragment bindLoginActivity();

    @ContributesAndroidInjector(modules = DictionaryModule.class)
    abstract DictionaryFragment bindDictionaryActivity();

    @ContributesAndroidInjector()
    abstract YesNoDialog bindYesNoDialog();

    @ContributesAndroidInjector()
    abstract YesNoNeutralDialog bindYesNoNeutralDialog();


    @ContributesAndroidInjector()
    abstract PromptDialog bindPromptDialog();

    @ContributesAndroidInjector()
    abstract SyncService bindSyncService();


    @ContributesAndroidInjector(modules = GenericModule.class)
    abstract GenericFragment bindGenericActivity();

    @ContributesAndroidInjector(modules = PossessedNounModule.class)
    abstract PossessedNounFragment bindPossessedNounActivity();

    @ContributesAndroidInjector(modules = RegularVerbModule.class)
    abstract RegularVerbFragment bindImpersonalVerbActivity();

    @ContributesAndroidInjector(modules = TenseModule.class)
    abstract TenseFragment bindTenseFragment();

    @ContributesAndroidInjector(modules = MainBottomModule.class)
    abstract MainBottomActivity bindMainBottomActivity();
}
