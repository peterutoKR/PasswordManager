package ipca.passwordmanager_joaogouveia

import android.content.Context
import android.provider.SyncStateContract.Helpers.insert
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(gestaoPassword::class), version = 2, exportSchema = false)
public abstract class PasswordRoomDatabase : RoomDatabase() {

    abstract fun passDao(): passDao


    companion object {

        @Volatile
        private var INSTANCE: PasswordRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): PasswordRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PasswordRoomDatabase::class.java,
                    "PasswordDatabase"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
