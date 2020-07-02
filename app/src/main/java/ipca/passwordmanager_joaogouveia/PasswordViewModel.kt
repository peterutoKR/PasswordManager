package ipca.passwordmanager_joaogouveia

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PasswordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PasswordRepository

    val allWords: LiveData<List<gestaoPassword>>

    init {
        val passDao = PasswordRoomDatabase.getDatabase(application).passDao()
        repository = PasswordRepository(passDao)
        allWords = repository.allPasswords
    }


    fun insert(pass: gestaoPassword) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(pass)
    }
}