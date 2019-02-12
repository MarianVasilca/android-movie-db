package tremend.com.moviedb.di

import dagger.Module
import dagger.Provides
import org.koin.dsl.module.applicationContext
import tremend.com.moviedb.AndroidApp
import tremend.com.moviedb.data.api.ApiService
import tremend.com.moviedb.data.db.AppDatabase
import tremend.com.moviedb.data.repositories.MovieRepository
import tremend.com.moviedb.utilities.schedulers.IoScheduler
import tremend.com.moviedb.utilities.schedulers.MainScheduler
import tremend.com.moviedb.utilities.schedulers.NetworkScheduler
import javax.inject.Singleton

val appModules = applicationContext {

    //    bean { it as Application }
//
//    bean { AppDatabase.getInstance(get()) }
//    bean { ApiService.create() }
//
//    bean { IoScheduler() }
//    bean { MainScheduler() }
//    bean { NetworkScheduler() }
//
//    bean { MovieRepository(get(), get(), get(), get()) }
//
//    viewModel { MovieViewModel(get(), get(), get()) }
}

@Module(includes = arrayOf(ViewModelModule::class, ContextModule::class))
public class AppModule {

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
