package tremend.com.moviedb.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import tremend.com.moviedb.AndroidApp
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AppModule::class,
        AndroidInjectionModule::class,
        ActivityBindingModule::class))
interface AppComponent : AndroidInjector<AndroidApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<AndroidApp>()


}