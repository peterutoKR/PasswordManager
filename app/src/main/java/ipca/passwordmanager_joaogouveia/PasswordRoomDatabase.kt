package ipca.passwordmanager_joaogouveia

import android.content.Context
import android.provider.SyncStateContract.Helpers.insert
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(gestaoPassword::class), version = 1, exportSchema = false)
public abstract class PasswordRoomDatabase : RoomDatabase() {

    abstract fun passDao(): passDao

    private class PasswordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.passDao())
                }
            }
        }

        suspend fun populateDatabase(passDao: passDao) {
            // Delete all content here.
            passDao.deleteAll()

            // Add sample words.
            var password = gestaoPassword("Tunds")
            passDao.insert(password)
            password = gestaoPassword("Tunds!")
            passDao.insert(password)

        }
    }

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
                    .addCallback(PasswordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
