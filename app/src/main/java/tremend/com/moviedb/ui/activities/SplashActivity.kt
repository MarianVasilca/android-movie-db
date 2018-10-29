package tremend.com.moviedb.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tremend.com.moviedb.utilities.extensions.startAndFinish

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startAndFinish(MainActivity::class)
    }
}