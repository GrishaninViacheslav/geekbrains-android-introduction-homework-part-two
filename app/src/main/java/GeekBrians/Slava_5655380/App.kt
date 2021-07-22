package GeekBrians.Slava_5655380

import android.app.Application

class App : Application() {
    companion object {
        private var _instance: App? = null
        val instance: App
            get() = _instance ?: throw Exception("Can not provide application instance")
    }

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }
}