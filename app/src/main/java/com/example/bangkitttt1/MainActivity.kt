package com.example.bangkitttt1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bangkitttt1.adapter.ListGemAdapter
import com.example.bangkitttt1.model.Gem


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    private val list = ArrayList<Gem>()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvGem)
        recyclerView.setHasFixedSize(true)

        list.addAll(getListHeroes())
        showRecyclerList()
        
    }

    private fun showRecyclerList() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        val listGemAdapter = ListGemAdapter(list)
        recyclerView.adapter = listGemAdapter
        listGemAdapter.setOnItemClickCallBack(object : ListGemAdapter.OnItemClickCallback{
            override fun onItemClicked(data : Gem) {
                showSelectedGem(data)
            }
        })

    }

    private fun showSelectedGem(gem : Gem) {
        val title = gem.title
        val description = gem.description
        val image = gem.imageURL
        val button = gem.gemExternalLink
        val  intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra("titleIntent", title)
        intent.putExtra("descIntent", description)
        intent.putExtra("imgIntent", image)
        intent.putExtra("buttonIntent", button)
        startActivity(intent)
    }

    private fun getListHeroes() : List<Gem> {
        return Gem.loadData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        when(item.itemId) {
            R.id.action_about -> {
                val intent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}