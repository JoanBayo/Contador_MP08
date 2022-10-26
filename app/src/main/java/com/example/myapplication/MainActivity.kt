package com.example.myapplication

import android.content.IntentSender.OnFinished
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.os.CountDownTimer
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {

    private  val INITIAL_TIME = 60

    private val TAG = MainActivity::class.java.simpleName

    internal lateinit var tapMeButton : Button
    internal lateinit var timeTextView : TextView
    internal lateinit var counterTextView : TextView
    internal var counter = 0
    internal var time = INITIAL_TIME

    internal var appStarted = false
    internal lateinit var countdownTimer : CountDownTimer
    internal val initialCountDownTimer: Long = 60000
    internal val intervalCountDownTimer: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG,"hola mon! onCreate")
        Log.d(TAG,counter.toString())
        Log.d(TAG,time.toString())


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        initCountdown()

        tapMeButton = findViewById(R.id.tapMeButton)
        timeTextView = findViewById(R.id.timeTextView)
        counterTextView = findViewById(R.id.counterTextView)



        tapMeButton.setOnClickListener{ view ->
            if(!appStarted) {
                startGame()

            }
            val bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce)
            view.startAnimation(bounceAnimation)
            incrementCounter()

        }
        timeTextView.text = getString(R.string.timeText, time)
        counterTextView.text = getString(R.string.Score,counter)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.actionAbout) {
            showInfo()
        }
        return true
    }

    private fun showInfo() {
        val dialogTitle = getString(R.string.aboutTitle, BuildConfig.VERSION_NAME)
        val dialogMessage = getString(R.string.aboutMessage)

        val builder = AlertDialog.Builder(this)

        builder.setTitle(dialogTitle).setMessage(dialogMessage).create().show()
    }

    private fun startGame() {
        countdownTimer.start()
        appStarted = true
    }

    private fun initCountdown(){
        countdownTimer = object : CountDownTimer(initialCountDownTimer, intervalCountDownTimer){
            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished / 1000
                time = timeLeft.toInt()
                timeTextView.text = getString(R.string.timeText,time)
            }

            override fun onFinish() {
                endGame()
            }
        }
    }

    private fun incrementCounter(){
        counter += 1
        counterTextView.text = getString(R.string.Score,counter)

    }



    private fun endGame(){
        Toast.makeText(this,getString(R.string.endGame, counter), Toast.LENGTH_LONG ).show()
        resetGame()
    }
    private fun resetGame() {
        //RESET PUNTUACIÓ A ZERO
        counter = 0
        counterTextView.text = getString(R.string.Score,counter)
        //REINICIALITZAR EL COMPTARDOR
        time = INITIAL_TIME
        timeTextView.text = getString(R.string.timeText, time)
        initCountdown()

        //GAME STARTED A FALSE
        appStarted = false
    }
}