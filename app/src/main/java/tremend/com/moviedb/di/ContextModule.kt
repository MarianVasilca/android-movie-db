package tremend.com.moviedb.di

import android.content.Context
import dagger.Binds
import dagger.Module
import tremend.com.moviedb.AndroidApp

@Module
abstract class ContextModule {
    @Binds
    internal abstract fun provideContext(application: AndroidApp): Context
}