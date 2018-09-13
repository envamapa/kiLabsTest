package com.mx.envamapa.app.wundertest.commons.di.component;

import com.mx.envamapa.app.wundertest.views.view.SplashScreen;

import javax.inject.Singleton;

import dagger.Component;
import smartapp.app.sura.mx.com.smartapp.Commons.Application;
import smartapp.app.sura.mx.com.smartapp.Commons.DI.Module.AppModule;
import smartapp.app.sura.mx.com.smartapp.Views.Presenter.Login.LoginPresenter;
import smartapp.app.sura.mx.com.smartapp.Views.Presenter.MainDashboard.Accompaniment.AccompanimentQuestionsPresenter;
import smartapp.app.sura.mx.com.smartapp.Views.Presenter.MainDashboard.Calendar.CalendarPresenter;
import smartapp.app.sura.mx.com.smartapp.Views.Presenter.MainDashboard.Calendar.PeriodsPresenter;
import smartapp.app.sura.mx.com.smartapp.Views.Presenter.MainDashboard.Checklist.ChecklistQuestionsPresenter;
import smartapp.app.sura.mx.com.smartapp.Views.Presenter.MainDashboard.HomeDashboardPresenter;
import smartapp.app.sura.mx.com.smartapp.Views.Presenter.MainDashboard.Indicators.DependentsPresenter;
import smartapp.app.sura.mx.com.smartapp.Views.Presenter.MainDashboard.Indicators.IndicatorsPresenter;
import smartapp.app.sura.mx.com.smartapp.Views.Presenter.MainDashboard.MainPresenter;
import smartapp.app.sura.mx.com.smartapp.Views.Presenter.MainDashboard.Search.SearchPresenter;
import smartapp.app.sura.mx.com.smartapp.Views.Presenter.MainDashboard.SubPresenters.SendMailPresenter;
import smartapp.app.sura.mx.com.smartapp.Views.Presenter.MainDashboard.SubPresenters.SesionOneNOneDashboardPresenter;
import smartapp.app.sura.mx.com.smartapp.Views.Presenter.MainDashboard.TwelveMonthVision.TwelveMonthVisionPresenter;
import smartapp.app.sura.mx.com.smartapp.Views.Presenter.Splash.SplashPresenter;
import smartapp.app.sura.mx.com.smartapp.Views.Views.Login.Login;
import smartapp.app.sura.mx.com.smartapp.Views.Views.MainDashboard.Accompaniment.AccompanimentQuestions;
import smartapp.app.sura.mx.com.smartapp.Views.Views.MainDashboard.Calendar.Calendar;
import smartapp.app.sura.mx.com.smartapp.Views.Views.MainDashboard.Calendar.Periods;
import smartapp.app.sura.mx.com.smartapp.Views.Views.MainDashboard.Checklist.ChecklistQuestions;
import smartapp.app.sura.mx.com.smartapp.Views.Views.MainDashboard.Checklist_Accompaniment.Beginning;
import smartapp.app.sura.mx.com.smartapp.Views.Views.MainDashboard.HomeDashboard;
import smartapp.app.sura.mx.com.smartapp.Views.Views.MainDashboard.Indicators.Dependents;
import smartapp.app.sura.mx.com.smartapp.Views.Views.MainDashboard.MainActivity;
import smartapp.app.sura.mx.com.smartapp.Views.Views.MainDashboard.Search.Search;
import smartapp.app.sura.mx.com.smartapp.Views.Views.Splash.splash;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(SplashScreen splashScreen);
}