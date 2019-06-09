package tremend.com.moviedb

import android.app.Application
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // start injecting with koin


        /**
         * The startKoin() function is the main entry point to launch Koin container.
         * It need a list of Koin modules to run.
         * Modules are loaded and definitions are ready to be resolved by the Koin container.
         * Once startKoin has been called, Koin will read all your modules & definitions.
         * Koin is then ready for any get() or by inject() call to retrieve the needed instance.
         *
         * You can’t call the startKoin() function more than once.
         */
//        startKoin(this, listOf(appModules))

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }
}