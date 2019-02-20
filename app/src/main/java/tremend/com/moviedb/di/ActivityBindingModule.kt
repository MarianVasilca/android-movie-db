package tremend.com.moviedb.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import tremend.com.moviedb.ui.activities.MainActivity
import tremend.com.moviedb.ui.fragments.MainFragment
import tremend.com.moviedb.ui.fragments.ReviewFragment

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = arrayOf(AdapterModule::class))
    @FragmentScope
    abstract fun bindMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun bindActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindReviewFragment(): ReviewFragment

}