package tremend.com.moviedb.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import tremend.com.moviedb.ui.activities.MainActivity
import tremend.com.moviedb.ui.fragments.MainFragment

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun bindFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun bindActivity(): MainActivity

}