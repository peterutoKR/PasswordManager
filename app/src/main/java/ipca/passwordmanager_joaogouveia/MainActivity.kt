package ipca.passwordmanager_joaogouveia

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var passwordViewModel: PasswordViewModel
    private val newPasswordActivityRequestCode = 1
    private val deletePasswordActivityRequestCode = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = PasswordListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        passwordViewModel = ViewModelProvider(this).get(PasswordViewModel::class.java)
        passwordViewModel.allPasswords.observe(this, Observer { words ->
            words?.let { adapter.setPasswords(it) }
        })

        val button_delete = findViewById<Button>(R.id.button_delete)
        button_delete.setOnClickListener {
            val intent = Intent(this@MainActivity, PasswordDeleteActivity::class.java)
            startActivity(intent)
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewPasswordActivity::class.java)
            startActivityForResult(intent, newPasswordActivityRequestCode)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newPasswordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewPasswordActivity.WORD_REPLY)?.let {
                val Password = gestaoPassword(data.getStringExtra(NewPasswordActivity.WORD_REPLY),data.getStringExtra(NewPasswordActivity.SITE_REPLY), data.getStringExtra(NewPasswordActivity.DESC_REPLY), data.getStringExtra(NewPasswordActivity.DATA_REPLY))
                passwordViewModel.insert(Password)
            }
        }
        if (requestCode == deletePasswordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(PasswordDeleteActivity.WORD_REPLY)?.let {
                val Password = gestaoPassword(data.getStringExtra(PasswordDeleteActivity.WORD_REPLY),data.getStringExtra(PasswordDeleteActivity.SITE_REPLY), data.getStringExtra(PasswordDeleteActivity.DESC_REPLY), data.getStringExtra(PasswordDeleteActivity.DATA_REPLY))
                passwordViewModel.delete(Password)

            }
        }

    }
}




