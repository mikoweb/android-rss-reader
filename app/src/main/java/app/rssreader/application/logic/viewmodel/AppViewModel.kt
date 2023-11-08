package app.rssreader.application.logic.viewmodel

import androidx.lifecycle.ViewModel

open class AppViewModel : ViewModel() {
    init {
        ViewModelMap.init(this);
    }
}
