package edu.cnm.deepdive.fizzbuzz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import edu.cnm.deepdive.fizzbuzz.R.id.fizz_toggle
import edu.cnm.deepdive.fizzbuzz.R.id.number_view
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivityKt : AppCompatActivity() {
    private val rng : Random = Random()
    private var value: Int = 0
    private var activeCorrect: Int = 0
    private var passiveCorrect: Int = 0
    private var incorrect: Int = 0

    private var test: Int? = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startTimer()

       // val list: List<Int> = listOf(1,2,3,4,5,6,7,8,9,10)
        //list.forEachIndexed{ index, i->
        //list.filter{it:int
        //it >4}


    }

    private fun startTimer(){

        val timer = Timer()
        timer.start()
    }

    private fun updateTally(){

//        val result: String = test?.let{ test ->
//        test.toString()
//        }?: ""
//
//        val result2: Int = test?.apply{this
//
//        }?: 0
//

        val isFizz = (value % 3 == 0)
        val isBuzz = (value % 5 == 0)
        val fizzCorrect = (isFizz == fizz_toggle?.isChecked)
       // if (fizz_toggle?.isChecked == true) {}
        val buzzCorrect = (isBuzz == buzz_toggle?.isChecked)
        if (!(fizzCorrect && buzzCorrect)) {
            incorrect++;
    } else if (isFizz || isBuzz) {
        activeCorrect++;
    } else {
        passiveCorrect++;
    }

    }

    private fun updateTallyDispaly(){

        active_correct_view?.text = getString(R.string.active_correct, activeCorrect)
        passive_correct_view?.text = getString(R.string.passive_correct, passiveCorrect)
        incorrect_view?.text = getString(R.string.incorrect,incorrect)
    }

    private fun updateView(){
        value = 1 + rng.nextInt(UPPER_BOUND)
        runOnUiThread{
            updateTallyDispaly()
            number_view?.text = value.toString();
            fizz_toggle?.isChecked = false
            buzz_toggle?.isChecked = false

        }

    }
    inner class Timer: Thread(){

        override fun run(){
            try {
                updateView()
                Thread.sleep(TIMEOUT_INTERVAL.toLong())
            } catch (e: InterruptedException){

            }finally{
                updateTally()
                startTimer()
            }
        }
    }


    companion object {
        private const val UPPER_BOUND = 99
        private const val TIMEOUT_INTERVAL = 5000


    }
}
