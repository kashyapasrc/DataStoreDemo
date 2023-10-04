package com.kashyap.datastoredemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.lifecycle.asLiveData

class MainActivity : AppCompatActivity() {

    private val userNameTextView: TextView by lazy {
        findViewById(R.id.username_textview)
    }
    private val ageTextView: TextView by lazy {
        findViewById(R.id.age_textview)
    }

    private val userNameEditText: EditText by lazy {
        findViewById(R.id.username_edittext)
    }
    private val ageEditText: EditText by lazy {
        findViewById(R.id.age_edittext)
    }
    private lateinit var userDataStore: UserDataStore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userDataStore = UserDataStore(context = this)

        findViewById<Button>(R.id.save_button).apply {
            setOnClickListener {
                GlobalScope.launch {
                    userDataStore.storeUser(
                        userNameEditText.text.toString(),
                        ageEditText.text.toString().toInt()
                    )
                }
            }
        }

        this.userDataStore.userNameFlow.asLiveData().observe(this){
            userNameTextView.text=it
        }
        this.userDataStore.userAgeFlow.asLiveData().observe(this){
            ageTextView.text= it.toString()
        }


    }
}