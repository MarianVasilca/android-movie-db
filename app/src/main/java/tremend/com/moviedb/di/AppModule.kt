package tremend.com.moviedb.di

import dagger.Module
import dagger.Provides
import tremend.com.moviedb.AndroidApp
import tremend.com.moviedb.data.api.ApiService
import tremend.com.moviedb.data.db.AppDatabase
import tremend.com.moviedb.data.repositories.MovieRepository
import tremend.com.moviedb.utilities.schedulers.IoScheduler
import tremend.com.moviedb.utilities.schedulers.MainScheduler
import tremend.com.moviedb.utilities.schedulers.NetworkScheduler
import javax.inject.Singleton

@Module(includes = arrayOf(ViewModelModule::class, ContextModule::class))
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(application: AndroidApp): AppDatabase {
        return AppDatabase.getInstance(application)
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiService.create()
    }


    @Provides
    @Singleton
    fun provideIoScheduler(): IoScheduler {
        return IoScheduler()
    }

    @Provides
    @Singleton
    fun provideMainScheduler(): MainScheduler {
        return MainScheduler()
    }

    @Provides
    @Singleton
    fun provideNetworkScheduler(): NetworkScheduler {
        return NetworkScheduler()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(apiService: ApiService,
                               database: AppDatabase,
                               networkScheduler: NetworkScheduler,
                               ioScheduler: IoScheduler): MovieRepository {
        return MovieRepository(apiService, database, networkScheduler, ioScheduler)
    }

}
