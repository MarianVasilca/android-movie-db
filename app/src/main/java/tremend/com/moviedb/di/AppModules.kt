package tremend.com.moviedb.di

import android.app.Application
import org.koin.dsl.module.applicationContext
import tremend.com.moviedb.data.api.ApiService
import tremend.com.moviedb.data.db.AppDatabase
import tremend.com.moviedb.data.repositories.MovieRepository
import tremend.com.moviedb.utilities.schedulers.IoScheduler
import tremend.com.moviedb.utilities.schedulers.MainScheduler
import tremend.com.moviedb.utilities.schedulers.NetworkScheduler

val appModules = applicationContext {

    bean { it as Application }

    bean { AppDatabase.getInstance(get()) }
    bean { ApiService.create() }

    bean { IoScheduler() }
    bean { MainScheduler() }
    bean { NetworkScheduler() }

    bean { MovieRepository(get(), get(), get(), get(), get()) }
}