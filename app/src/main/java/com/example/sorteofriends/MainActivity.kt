package com.example.sorteofriends

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sorteofriends.databinding.ActivityMainBinding
import kotlin.random.Random
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val listaPlayers = ArrayList<ItemsPlayers>()
    private val adapter = CustomAdapter(listaPlayers)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.fabPlayGame.setOnClickListener { view ->
            //Thread.sleep(5000)
            if (adapter.itemCount==0){
                Toast.makeText(this, "Debe agregar un participante", Toast.LENGTH_LONG).show()
            }
            if(adapter.itemCount>0){
                var positRandom =Random.nextInt(0, adapter.itemCount)
                Toast.makeText(this, "Sorteando.... :)", Toast.LENGTH_LONG).show()
                var userPlayer= listaPlayers[positRandom].text1
                Thread.sleep(3000L)
                val alert: AlertDialog.Builder = AlertDialog.Builder(this)
                alert.setTitle("Sorteo Terminado")
                alert.setMessage("Felicidades Gano: "+userPlayer)
                alert.setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, whichButton ->
                    dialog.cancel()
                })
                alert.show()
            }

        }
        val recyclerview=findViewById<RecyclerView>(R.id.recyclerView)
        recyclerview.layoutManager=LinearLayoutManager(this)
        recyclerview.adapter=adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_exit -> {
                moveTaskToBack(true);
                exitProcess(-1);
                //true
            }
            R.id.action_about -> {
                val intent = Intent(this, AboutApp::class.java)
                startActivity(intent)
                true
            }
            R.id.action_addplayer->{
                val alert: AlertDialog.Builder = AlertDialog.Builder(this)
                alert.setTitle("Agregar Participante")
                alert.setMessage("Ingrese nombre:")
                val input = EditText(this)
                alert.setView(input)
                alert.setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, whichButton ->
                   val valueName = input.text.toString()
                   if(valueName.isNotEmpty() && norepeatlist(valueName))
                        insertItem(valueName)
                })
                alert.setNegativeButton("Cancelar",
                    DialogInterface.OnClickListener { dialog, whichButton ->
                        dialog.cancel()
                    })
                alert.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    fun insertItem(strname:String) {
        //val recyclerviewnew=findViewById<RecyclerView>(R.id.recyclerView)
        //val selectedItemPosition: Int = recyclerviewnew.getChildAdapterPosition(view)
        val index = listaPlayers.size
        val newItem = ItemsPlayers(
            R.drawable.ic_userslist_foreground,
            strname.uppercase().trim()
        )
        listaPlayers.add(index, newItem)
        adapter.notifyItemInserted(index)
    }
    fun removeItem(view: View) {
        val selectedItemPosition: Int = adapter.getPositionremoved()
        //listaPlayers.removeAt(selectedItemPosition)
        adapter.notifyItemRemoved(selectedItemPosition)
    }
    fun norepeatlist(strname:String):Boolean{
        val listdistint= listaPlayers.filter { s->s.text1==strname.uppercase().trim() }
        return listdistint.size==0
    }

}