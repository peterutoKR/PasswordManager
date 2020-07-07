package ipca.passwordmanager_joaogouveia

import androidx.lifecycle.LiveData
import androidx.room.*

data class gestaoPassword(val password: String) //Classe com o propósito de armazenar dados

@Entity(tableName = "Passwords") //Marca a classe como uma entidade. Esta classe vai possuir um mapeamento de tabela SQLite na base de dados



class Password( //criação da classe Password

    @PrimaryKey(autoGenerate = true) val id: Int, //Atribuição da chave primária que é gerada automaticamente e vai ser atribuída ao ID.

    @ColumnInfo(name = "Pass") var PalavraPasse : String, var Site: String, var Descriacao: String, var Data: String //Atribuição do nome da tabela e dos respetivos campos e os seus tipos

)

@Dao //Marca a classe como Data Access Object. Data Access Object são as main classes onde defini-mos as interações da nossa base de dados.
interface passDao { //Definição de uma interface

    @Query("SELECT * from Passwords ORDER BY id ASC") //Query que vai selecionar tudo da tabela Passwords e vai pôr por ordem ascendente pelo id
    fun getOrderPass(): LiveData<List<gestaoPassword>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(palavrapasse: gestaoPassword)

    @Query("DELETE FROM Passwords") //Vai eliminar dados da tabela Passwords
    suspend fun deleteAll()
}
