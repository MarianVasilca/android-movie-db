package tremend.com.moviedb.di

import dagger.Module
import dagger.Provides
import tremend.com.moviedb.ui.adapters.MovieAdapter


@Module
class AdapterModule {
    @Provides
    @FragmentScope
    fun provideAdapter(): MovieAdapter {
        return MovieAdapter()
    }
}