package tremend.com.moviedb.di


//val appModules = module {
//
//
//    /**
//     * Declaring a singleton component means that Koin container will keep a unique instance of your declared component.
//     * Use the single function in a module to declare a singleton.
//     * single & factory keywords help you declare your components through a lambda expression.
//     * this lambda describe the way that you build your component.
//     * Usually we instantiate components via their constructors, but you can also use any expression.
//     * factory or single { Class constructor / expression }
//     * The result type of your lambda is the main type of your component
//     */
//
//    single { AppDatabase.getInstance(get()) }
//    single { ApiService.create() }
//
//    single { IoScheduler() }
//    single { MainScheduler() }
//    single { NetworkScheduler() }
//
//    /**
//     * the adapter is tied with the scope lifetime
//     */
//    scope("sessionMovieAdapter") { MovieAdapter() }
//
//    single { MovieRepository(get(), get(), get(), get()) }
//
//   viewModel { MovieViewModel(get(), get(), get()) }
//}