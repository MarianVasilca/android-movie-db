package tremend.com.moviedb

import android.app.Application
import org.koin.android.ext.android.startKoin
import timber.log.Timber
import tremend.com.moviedb.di.appModules

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // start injecting with koin
        startKoin(this, listOf(appModules))

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }
}