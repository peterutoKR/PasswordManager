package ipca.passwordmanager_joaogouveia

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PasswordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PasswordRepository

    val allPasswords: LiveData<List<gestaoPassword>>

    init {
        val passDao = PasswordRoomDatabase.getDatabase(application, viewModelScope).passDao()
        repository = PasswordRepository(passDao)
        allPasswords = repository.allPasswords
    }


    fun insert(pass: gestaoPassword) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(pass)
    }

    fun delete(pass: gestaoPassword) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(pass)
    }
}