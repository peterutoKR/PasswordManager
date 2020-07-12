package ipca.passwordmanager_joaogouveia

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Passwords") //Marca a classe como uma entidade. Esta classe vai possuir um mapeamento de tabela SQLite na base de dados
data class gestaoPassword( //Classe com o propósito de armazenar dados
    @PrimaryKey val password : String,
    @ColumnInfo(name = "site") val site: String,
    @ColumnInfo(name = "descricao") val descricao: String,
    @ColumnInfo(name = "data") val data: String)






