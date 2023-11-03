package app.rssreader;

import android.app.Application;

import dagger.Component;

@Component
interface ApplicationComponent {
    void inject(MainActivity mainActivity);
}

public class MainApplication extends Application {
    ApplicationComponent appComponent = DaggerApplicationComponent.create();
}
