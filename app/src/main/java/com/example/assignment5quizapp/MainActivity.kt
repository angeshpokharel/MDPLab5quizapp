package com.example.assignment5quizapp

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private val quizList = ArrayList<Quiz>()

    private lateinit var radioButton1: RadioButton
    private lateinit var radioButton2: RadioButton
    private lateinit var radioButton3: RadioButton

    private lateinit var checkbox1: CheckBox
    private lateinit var checkbox2: CheckBox
    private lateinit var checkbox3: CheckBox

    private var answer1 = ""
    private var answer2 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initQuizQuestionsAnswers()

        initViews()
    }

    private fun initViews() {
        radioButton1 = findViewById(R.id.radio_option1_q1)
        radioButton2 = findViewById(R.id.radio_option2_q1)
        radioButton3 = findViewById(R.id.radio_option3_q1)

        checkbox1 = findViewById(R.id.checkbox_option1_q2)
        checkbox2 = findViewById(R.id.checkbox_option2_q2)
        checkbox3 = findViewById(R.id.checkbox_option3_q2)
    }

    private fun initQuizQuestionsAnswers() {
        val quiz1 = Quiz(
            "C",
            1
        )

        val quiz2 = Quiz(
            "BC",
            2
        )

        quizList.add(0, quiz1)
        quizList.add(1, quiz2)
    }

    fun onResetClicked(view: View) {

        //reset options for question 1
        radioButton1.isChecked = false
        radioButton2.isChecked = false
        radioButton3.isChecked = false

        //reset options for question 2
        checkbox1.isChecked = false
        checkbox2.isChecked = false
        checkbox3.isChecked = false

        //reset answer variable
        answer1 = ""
        answer2 = ""
    }

    fun onSubmitClicked(view: View) {

        var score = 0

        val current = LocalDateTime.now()

        //get date
        val formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = current.format(formatterDate)

        //get time
        val formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss")
        val time = current.format(formatterTime)

        readAnswer2()

        if (validateAnswer1()) {
            score += 50
        }
        if (validateAnswer2()) {
            score += 50
        }

        val messageToDisplay =
            "Congratulations! You submitted on current Date: $date and Time: $time, You achieved $score %"

        displayDialog(messageToDisplay, "Your Quiz Score is here..")
    }

    private fun displayDialog(message: String, title: String) {
        val builder: AlertDialog.Builder = this.let {
            AlertDialog.Builder(it)
        }

        builder.setMessage(message)!!.setTitle(title)

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun validateAnswer1(): Boolean {
        val quiz = quizList[0] // for first quiz
        if (quiz.answer == answer1) {
            return true
        }
        return false
    }

    private fun validateAnswer2(): Boolean {
        val quiz = quizList[1] // for second quiz
        if (quiz.answer == answer2) {
            return true
        }
        return false
    }

    private fun readAnswer2() {
        answer2 = ""
        if (checkbox1.isChecked) {
            answer2 += "A"
        }
        if (checkbox2.isChecked) {
            answer2 += "B"
        }

        if (checkbox3.isChecked) {
            answer2 += "C"
        }

    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // IsChecked Button
            val checked = view.isChecked

            // To Check the radio button
            when (view.getId()) {
                R.id.radio_option1_q1 ->
                    if (checked) {
                        answer1 = "A"
                    }
                R.id.radio_option2_q1 ->
                    if (checked) {
                        answer1 = "B"
                    }

                R.id.radio_option3_q1 ->
                    if (checked) {
                        answer1 = "C"
                    }
            }
        }
    }
}