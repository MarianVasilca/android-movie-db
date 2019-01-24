package tremend.com.moviedb.di

import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import tremend.com.moviedb.data.api.ApiService
import tremend.com.moviedb.data.db.AppDatabase
import tremend.com.moviedb.data.repositories.MovieRepository
import tremend.com.moviedb.ui.adapters.MovieAdapter
import tremend.com.moviedb.utilities.schedulers.IoScheduler
import tremend.com.moviedb.utilities.schedulers.MainScheduler
import tremend.com.moviedb.utilities.schedulers.NetworkScheduler
import tremend.com.moviedb.viewmodels.MovieViewModel

val appModules = module {


    /**
     * Declaring a singleton component means that Koin container will keep a unique instance of your declared component.
     * Use the single function in a module to declare a singleton.
     * single & factory keywords help you declare your components through a lambda expression.
     * this lambda describe the way that you build your component.
     * Usually we instantiate components via their constructors, but you can also use any expression.
     * factory or single { Class constructor / expression }
     * The result type of your lambda is the main type of your component
     */

    single { AppDatabase.getInstance(get()) }
    single { ApiService.create() }

    single { IoScheduler() }
    single { MainScheduler() }
    single { NetworkScheduler() }

    /**
     * in case you are using the same adapter simultaneously in 2 fragments and want different
     * callback actions, change from 'single' to 'factory' to give each fragment a different
     * instance of MovieAdapter
     */
    single { MovieAdapter() }

    single { MovieRepository(get(), get(), get(), get()) }

    viewModel { MovieViewModel(get(), get(), get()) }
}