package ipca.passwordmanager_joaogouveia

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class PasswordDeleteActivity : AppCompatActivity() {

    private lateinit var editWordView: EditText



    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)
        editWordView = findViewById(R.id.insert_password)




        val button = findViewById<Button>(R.id.button_delete)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editWordView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = editWordView.text.toString()
                val site= ""
                val descricao = ""
                val data= ""

                replyIntent.putExtra(WORD_REPLY, word)
                replyIntent.putExtra(SITE_REPLY, site)
                replyIntent.putExtra(DESC_REPLY, descricao)
                replyIntent.putExtra(DATA_REPLY, data)


                setResult(Activity.RESULT_OK, replyIntent)

            }
            finish()
        }
    }

    companion object {
        const val WORD_REPLY = "word"
        const val  SITE_REPLY = "site"
        const val  DESC_REPLY = "descricao"
        const val  DATA_REPLY = "data"
    }
}