package ipca.passwordmanager_joaogouveia

import androidx.lifecycle.LiveData
import androidx.room.*

data class gestaoPassword(val password: String)

@Entity(tableName = "Passwords")

class Password(

    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(name = "Pass") var PalavraPasse : String, var Site: String, var Descriacao: String, var Data: String

)

@Dao
interface passDao {

    @Query("SELECT * from Passwords ORDER BY id ASC")
    fun getOrderPass(): LiveData<List<gestaoPassword>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(palavrapasse: gestaoPassword)

    @Query("DELETE FROM Passwords")
    suspend fun deleteAll()
}
