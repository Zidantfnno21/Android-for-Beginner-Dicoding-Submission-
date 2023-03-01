package com.example.bangkitttt1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    private lateinit var tvTitle : TextView
    private lateinit var tvDescription : TextView
    private lateinit var imageView : ImageView
    private lateinit var button : Button

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var intent = intent
        val titleIntent = intent.getStringExtra("titleIntent")
        val descIntent = intent.getStringExtra("descIntent")
        val imgIntent = intent.getStringExtra("imgIntent")
        val buttonIntent = intent.getStringExtra("buttonIntent")

        tvTitle = findViewById(R.id.tvDetailTitle)
        tvTitle.text = titleIntent

        tvDescription = findViewById(R.id.tvDetail)
        tvDescription.text = descIntent

        imageView = findViewById(R.id.ivDetail)
        Glide
            .with(this)
            .load(imgIntent)
            .into(imageView)

        button = findViewById(R.id.btExtern)
        button.setOnClickListener {
            val uri = buttonIntent
            val link = Uri.parse(uri)
            val intent = Intent(Intent.ACTION_VIEW, link)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        when(item.itemId) {
            R.id.action_share -> {
                shareDetail()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareDetail() {
        val imgIntent = intent.getStringExtra("imgIntent")
        val titleIntent = intent.getStringExtra("titleIntent")
        val descIntent = intent.getStringExtra("descIntent")
        val imageUrl = imgIntent
        val combine_text = "Title : \n$titleIntent\n\nDescription : \n$descIntent"
        val imageUri = Uri.parse(imageUrl)
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            intent.data = imageUri
            putExtra(Intent.EXTRA_STREAM, imageUri )
            putExtra(Intent.EXTRA_TEXT, combine_text)
            type = "image/*"
        }
        val shareIntent = Intent.createChooser(sendIntent,"Share this to your Bestfriend")
        startActivity(shareIntent)
    }
}