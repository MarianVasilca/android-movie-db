package tremend.com.moviedb.utilities.schedulers

import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

class NetworkScheduler : Scheduler {
    override fun asRxScheduler(): io.reactivex.Scheduler = Schedulers.from(executor)

    private val executor = Executors.newFixedThreadPool(3)

    override fun runOnThread(runnable: ExecutionBlock) {
        executor.execute(runnable)
    }
}