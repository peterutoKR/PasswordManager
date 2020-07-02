package ipca.passwordmanager_joaogouveia

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(gestaoPassword::class), version = 1, exportSchema = false)
public abstract class PasswordRoomDatabase : RoomDatabase() {

    abstract fun passDao(): passDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PasswordRoomDatabase? = null

        fun getDatabase(context: Context): PasswordRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PasswordRoomDatabase::class.java,
                    "PasswordDatabase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
