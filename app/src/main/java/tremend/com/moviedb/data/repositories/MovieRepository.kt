package tremend.com.moviedb.data.repositories

import tremend.com.moviedb.data.api.ApiService
import tremend.com.moviedb.data.db.AppDatabase
import tremend.com.moviedb.utilities.schedulers.IoScheduler
import tremend.com.moviedb.utilities.schedulers.MainScheduler
import tremend.com.moviedb.utilities.schedulers.NetworkScheduler

class MovieRepository constructor(
        private val apiService: ApiService,
        private val database: AppDatabase,
        private val mainScheduler: MainScheduler,
        private val networkScheduler: NetworkScheduler,
        private val ioScheduler: IoScheduler
)