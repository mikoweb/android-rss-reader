package app.rssreader.ui.theme

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.rssreader.application.logic.viewmodel.AppViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class MessageViewModel @Inject constructor() : AppViewModel() {
    private val duration = SnackbarDuration.Long
    private var lastSuccess: Job? = null
    private var lastError: Job? = null
    private var lastWarning: Job? = null
    private var lastInfo: Job? = null

    var scope: CoroutineScope? by mutableStateOf(null)
    var successMessage: SnackbarHostState? by mutableStateOf(null)
    var errorMessage: SnackbarHostState? by mutableStateOf(null)
    var warningMessage: SnackbarHostState? by mutableStateOf(null)
    var infoMessage: SnackbarHostState? by mutableStateOf(null)

    fun createSuccess(message: String, withDismissAction: Boolean = true) {
        lastSuccess?.cancel()
        lastSuccess = scope?.launch {
            successMessage?.showSnackbar(
                message = message,
                withDismissAction = withDismissAction,
                duration = duration
            )
        }
    }

    fun createError(message: String, withDismissAction: Boolean = true) {
        lastError?.cancel()
        lastError = scope?.launch {
            errorMessage?.showSnackbar(
                message = message,
                withDismissAction = withDismissAction,
                duration = duration
            )
        }
    }

    fun createWarning(message: String, withDismissAction: Boolean = true) {
        lastWarning?.cancel()
        lastWarning = scope?.launch {
            warningMessage?.showSnackbar(
                message = message,
                withDismissAction = withDismissAction,
                duration = duration
            )
        }
    }

    fun createInfo(message: String, withDismissAction: Boolean = true) {
        lastInfo?.cancel()
        lastInfo = scope?.launch {
            infoMessage?.showSnackbar(
                message = message,
                withDismissAction = withDismissAction,
                duration = duration
            )
        }
    }
}
