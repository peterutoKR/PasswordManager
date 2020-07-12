package ipca.passwordmanager_joaogouveia

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao //Marca a classe como Data Access Object. Data Access Object são as main classes onde defini-mos as interações da nossa base de dados.
interface passDao { //Definição de uma interface

    @Query("SELECT * from Passwords ORDER BY password ASC") //Query que vai selecionar tudo da tabela Passwords e vai pôr por ordem ascendente pelo id
    fun getOrderPass(): LiveData<List<gestaoPassword>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(palavrapasse: gestaoPassword)

    @Query("DELETE FROM Passwords") //Vai eliminar dados da tabela Passwords
    suspend fun deleteAll()

    @Delete
    fun delete(password: gestaoPassword)
}