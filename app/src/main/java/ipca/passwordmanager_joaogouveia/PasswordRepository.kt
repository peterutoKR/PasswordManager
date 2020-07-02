package ipca.passwordmanager_joaogouveia
import androidx.lifecycle.LiveData

class PasswordRepository(private val passDao: passDao) {


    val allPasswords: LiveData<List<gestaoPassword>> = passDao.getOrderPass()

    suspend fun insert(pass: gestaoPassword) {
        passDao.insert(pass)
    }
}