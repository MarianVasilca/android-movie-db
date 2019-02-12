package tremend.com.moviedb

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import tremend.com.moviedb.di.DaggerAppComponent


class AndroidApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return DaggerAppComponent
                .builder().create(this)
    }


    override fun onCreate() {
        super.onCreate()

//        // start injecting with koin
//        startKoin(this, listOf(appModules))

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }

}