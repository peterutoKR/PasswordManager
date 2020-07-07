package ipca.passwordmanager_joaogouveia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PasswordListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<PasswordListAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var passwords = emptyList<gestaoPassword>() // Cached copy of words

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val PasswordItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = passwords[position]
        holder.PasswordItemView.text = current.password
    }

    internal fun setPasswords(passwords: List<gestaoPassword>) {
        this.passwords = passwords
        notifyDataSetChanged()
    }

    override fun getItemCount() = passwords.size
}