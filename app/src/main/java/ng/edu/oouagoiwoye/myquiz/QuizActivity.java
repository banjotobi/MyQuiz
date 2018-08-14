package ng.edu.oouagoiwoye.myquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    String uname;
    TextView status;
    TextView questionTextView;
    private RadioGroup ansRadioGroup;
    private RadioButton radioButtonCA;
    private RadioButton radioButton;
    private CheckBox optiona, optionb, optionc, optiond;
    private EditText resp;
    String report = "";
    int score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        uname = getIntent().getStringExtra("uname");
        status =  findViewById(R.id.status);
        status.setText(String.format(getString(R.string.status), uname));
        toastMaker("Dear "+uname+" you can now start your quiz");
    }



    public void toastMaker(CharSequence text)
    {
        Context context = getApplicationContext();

        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void submitAnswer(View view)
    {
        score = 0;

        questionTextView = findViewById(R.id.q1);
        ansRadioGroup = findViewById(R.id.an1);
        checkAnswer(questionTextView, ansRadioGroup);
        questionTextView =  findViewById(R.id.q2);
        ansRadioGroup = findViewById(R.id.an2);
        checkAnswer(questionTextView, ansRadioGroup);
        questionTextView =  findViewById(R.id.q3);
        ansRadioGroup =  findViewById(R.id.an3);
        checkAnswer(questionTextView, ansRadioGroup);
        questionTextView =  findViewById(R.id.q4);
        ansRadioGroup =  findViewById(R.id.an4);
        checkAnswer(questionTextView, ansRadioGroup);

       //work on question 5
        int cor = 0;
        questionTextView = findViewById(R.id.q6);
        optiona = findViewById(R.id.an6a);
        optionb =  findViewById(R.id.an6b);
        optionc = findViewById(R.id.an6c);
        optiond =  findViewById(R.id.an6d);


       if(optiona.isChecked() && !optionb.isChecked() && optionc.isChecked() && !optiond.isChecked())
       {
           ++score;
           report += questionTextView.getText().toString() + " CORRECT: Java & Kotlin \n";

       } else
       {
           report += questionTextView.getText().toString() + " ANS: Java & Kotlin \n";
       }
        report += "\n";
       //work on question 6
        questionTextView = findViewById(R.id.q7);
        resp = findViewById(R.id.a7);
        String ans6 = resp.getText().toString().toUpperCase();

       if (questionTextView.getTag().toString().equals(ans6))
       {
           ++score;
           report += questionTextView.getText().toString() + " CORRECT: "+questionTextView.getTag().toString()+"\n";
       } else {
           report += questionTextView.getText().toString() + " WRONG "+ans6+". ANS("+questionTextView.getTag().toString()+")\n";
       }







        toastMaker(getString(R.string.salute2)+uname+getString(R.string.salute3)+score+"\n REPORT\n"+report);

        Intent reportPage = new Intent(
                this, ReportActivity.class);
        reportPage.putExtra("uname", uname);
        reportPage.putExtra("score", score);
        reportPage.putExtra("report", report);
        startActivity(reportPage);

    }

    public void checkAnswer(TextView quest, RadioGroup options)
    {
        String ans = options.getTag().toString();
        if (options.getCheckedRadioButtonId() == -1) {
            report += quest.getText().toString() + " NOT ATTEMPTED. ANS("+ans+")\n";
        } else {
            int selectedId = options.getCheckedRadioButtonId();
            radioButton = findViewById(selectedId);
            String qResp = radioButton.getTag().toString();
            if (ans.equals(qResp)) {
                ++score;
                report += quest.getText().toString() + " CORRECT: "+qResp+".\n";
            } else {
                report += quest.getText().toString() + " WRONG "+qResp+". ANS("+ans+")\n";
            }
        }
        report += "\n";
    }
}
