package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class EnglishQuiz extends AppCompatActivity {

    TextView progressView, scoreView, vocaword, vocadef, sel1, sel2, sel3, sel4, textView;
    String answerStr, answerTmp;
    char quizChar;
    int quizInt, answerInt, correctInt, alphabetInt, inputInt, progress, score;
    int[] quizSet;
    String[] ansList;
    String[] vocaList = new String[]{"apple", "banana", "car", "dragon", "ear", "fire", "game", "home", "icecream", "juice",
            "kite", "lemon", "mother", "nose", "orange", "pink", "queen", "rabbit", "sun"};
    String[] defList = new String[]{"사과", "바나나", "차", "용", "귀", "불", "게임", "집", "아이스크림", "주스", "연", "레몬", "엄마",
            "코", "오렌지", "분홍", "여왕", "토끼", "해"};
    String[] alphabet = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "x", "w", "y", "z"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_quiz);
        init();
        createQuiz();
        makeQuiz();
    }

    private void createQuiz() {
        Random random = new Random();
        for (int i=0; i<5; i++) {
            quizSet[i] = random.nextInt(vocaList.length);
            for (int j=0; j<i; j++) {
                if (quizSet[i] == quizSet[j])
                    i--;
            }
        }
    }

    private void checkAnswer() {
        if (inputInt == correctInt) {
            score++;
            Toast.makeText(this, "정답입니다", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "틀렸습니다", Toast.LENGTH_SHORT).show();

        progress++;
        makeQuiz();
    }

    private void checkEnd() {
        if (progress >= 5) {
            progressView.setText("");
            scoreView.setText("");
            sel1.setText("");
            sel2.setText("");
            sel3.setText("");
            sel4.setText("");
            textView.setText("");
            vocadef.setText("얻은 점수");
            vocaword.setText(String.valueOf(score));
            vocaword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("score",score);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
        }
    }

    private void makeQuiz() {

        answerStr = "";
        Random r = new Random();


        String tmpstr = "";
        for (int i=0; i<quizSet.length; i++) {
            tmpstr += quizSet[i];
            tmpstr += " ";
        }
        Log.i("먼데", tmpstr);

        quizInt = quizSet[progress];
        vocadef.setText(defList[quizInt]);
        answerTmp = vocaList[quizInt];



        //문제 답 알파벳
        answerInt = r.nextInt(answerTmp.length());

        //문제 문자열 생성
        for (int i=0; i<answerTmp.length(); i++) {
            if (i == answerInt) {
                answerStr += "_ ";
                quizChar = answerTmp.charAt(i);
            }
            else
                answerStr += answerTmp.charAt(i) + " ";
        }

        for (int i=0; i<alphabet.length; i++) {
            if (alphabet[i].equals(String.valueOf(quizChar)))
                alphabetInt = i;
        }

        vocaword.setText(answerStr);
        //정답 보기 생성
        int[] tmpList = new int[]{0, 0, 0, 0};
        for (int i=0; i<4; i++) {
            tmpList[i] = r.nextInt(alphabet.length);
            if (tmpList[i] == alphabetInt)
                i--;
            else {
                for (int j=0; j<i; j++) {
                    if (tmpList[i] == tmpList[j]) {
                        i--;
                    }
                }
            }
        }


        for (int i=0; i<4; i++) {
            ansList[i] = alphabet[tmpList[i]];
        }

        sel1.setText(ansList[0]);
        sel2.setText(ansList[1]);
        sel3.setText(ansList[2]);
        sel4.setText(ansList[3]);

        //문제의 답을 어디 넣을지 답 숫자
        correctInt = r.nextInt(4);
        switch(correctInt) {
            case 0:
                sel1.setText(String.valueOf(quizChar));
                break;
            case 1:
                sel2.setText(String.valueOf(quizChar));
                break;
            case 2:
                sel3.setText(String.valueOf(quizChar));
                break;
            case 3:
                sel4.setText(String.valueOf(quizChar));
                break;
        }

        progressView.setText(String.valueOf(progress+1)+"/5");
        scoreView.setText(String.valueOf(score));
        checkEnd();
    }

    private void init() {
        progressView = (TextView)findViewById(R.id.vocaprogress);
        scoreView = (TextView)findViewById(R.id.vocascore);
        textView = (TextView)findViewById(R.id.vocascoreText);
        vocaword = (TextView)findViewById(R.id.vocaword);
        vocadef = (TextView)findViewById(R.id.vocadef);
        sel1 = (TextView)findViewById(R.id.vocaselect1);
        sel2 = (TextView)findViewById(R.id.vocaselect2);
        sel3 = (TextView)findViewById(R.id.vocaselect3);
        sel4 = (TextView)findViewById(R.id.vocaselect4);
        ansList = new String[4];
        quizSet = new int[6];

        score = 0;
        progress = 0;

        addListner();

    }

    private void addListner() {
        sel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputInt = 0;
                checkAnswer();
            }
        });
        sel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputInt = 1;
                checkAnswer();
            }
        });
        sel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputInt = 2;
                checkAnswer();
            }
        });
        sel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputInt = 3;
                checkAnswer();
            }
        });
    }


}
