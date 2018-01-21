package com.example.android.courtcounter;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA = 0;
    int scoreTeamB = 0;
    int aceTeamA = 0;
    int aceTeamB = 0;
    int challengeTeamA = 0;
    int challengeTeamB = 0;

    // For simplicity - array index is set index
    int[] setScoreA = new int[6];
    int[] setScoreB = new int[6];

    boolean[] setWonTeamA = new boolean[6];
    boolean[] setWonTeamB = new boolean[6];

    ArrayList<TextView> tvSetTeamA = new ArrayList<TextView>();
    ArrayList<TextView> tvSetTeamB = new ArrayList<TextView>();

    TextView tvTeamA;
    TextView tvTeamB;

    int wonSetsA = 0;
    int wonSetsB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // For simplicity - array list index is set index
        tvSetTeamA.add((TextView) findViewById(R.id.set_1_a));
        tvSetTeamA.add((TextView) findViewById(R.id.set_1_a));
        tvSetTeamA.add((TextView) findViewById(R.id.set_2_a));
        tvSetTeamA.add((TextView) findViewById(R.id.set_3_a));
        tvSetTeamA.add((TextView) findViewById(R.id.set_4_a));
        tvSetTeamA.add((TextView) findViewById(R.id.set_5_a));

        tvSetTeamB.add((TextView) findViewById(R.id.set_1_b));
        tvSetTeamB.add((TextView) findViewById(R.id.set_1_b));
        tvSetTeamB.add((TextView) findViewById(R.id.set_2_b));
        tvSetTeamB.add((TextView) findViewById(R.id.set_3_b));
        tvSetTeamB.add((TextView) findViewById(R.id.set_4_b));
        tvSetTeamB.add((TextView) findViewById(R.id.set_5_b));

        tvTeamA = (TextView) findViewById(R.id.team_a);
        tvTeamB = (TextView) findViewById(R.id.team_b);
    }

    /**
     * Add point for Team A. If set is finished, move score to set points table.
     */
    public void addPointTeamA(View view) {
        if (setScoreA[5] == 0 && setScoreB[5] == 0 && wonSetsA < 3 && wonSetsB < 3) {
            scoreTeamA = scoreTeamA + 1;
            // Set 1-4: 25 points
            if ((scoreTeamA >= 25 || scoreTeamB >= 25) && Math.abs(scoreTeamA - scoreTeamB) >= 2) {
                for (int i = 1; i <= 4; i++) {
                    if (setScoreA[i] == 0 && setScoreB[i] == 0) {
                        setScoreA[i] = scoreTeamA;
                        setScoreB[i] = scoreTeamB;
                        if (scoreTeamA > scoreTeamB) {
                            wonSetsA++;
                            setWonTeamA[i] = true;
                        }
                        scoreTeamA = 0;
                        scoreTeamB = 0;
                        break;
                    }
                }
            }
            // Set 5: 15 points
            if ((scoreTeamA >= 15 || scoreTeamB >= 15) && Math.abs(scoreTeamA - scoreTeamB) >= 2 && (setScoreA[4] > 0 || setScoreB[4] > 0)) {
                setScoreA[5] = scoreTeamA;
                setScoreB[5] = scoreTeamB;
                if (scoreTeamA > scoreTeamB) {
                    wonSetsA++;
                    setWonTeamA[5] = true;
                }
                scoreTeamA = 0;
                scoreTeamB = 0;
            }
        }

        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);

        displaySetForTeamA();
        displaySetForTeamB();

        if (wonSetsA == 3){
            Toast.makeText(getApplicationContext(), "Team A wins!", Toast.LENGTH_SHORT).show();
            tvTeamA.setTextColor(getResources().getColor(R.color.table_text_won));
        }
    }

    /**
     * Add ace for Team A.
     */
    public void addAceTeamA(View view) {
        if (wonSetsA < 3 && wonSetsB < 3) {
            aceTeamA = aceTeamA + 1;
            displayAceTeamA(aceTeamA);
        }
    }

    /**
     * Add challenge for Team A.
     */
    public void addChallengeTeamA(View view) {
        if (wonSetsA < 3 && wonSetsB < 3) {
            challengeTeamA = challengeTeamA + 1;
            displayChallengeTeamA(challengeTeamA);
        }
    }

    /**
     * Add point for Team B. If set is finished, move score to set points table.
     */
    public void addPointTeamB(View view) {
        if (setScoreA[5] == 0 && setScoreB[5] == 0 && wonSetsA < 3 && wonSetsB < 3) {
            scoreTeamB = scoreTeamB + 1;
            // Set 1-4: 25 points
            if ((scoreTeamA >= 25 || scoreTeamB >= 25) && Math.abs(scoreTeamB - scoreTeamA) >= 2) {
                for (int i = 1; i <= 4; i++) {
                    if (setScoreA[i] == 0 && setScoreB[i] == 0) {
                        setScoreA[i] = scoreTeamA;
                        setScoreB[i] = scoreTeamB;
                        if (scoreTeamB > scoreTeamA) {
                            wonSetsB++;
                            setWonTeamB[i] = true;
                        }
                        scoreTeamA = 0;
                        scoreTeamB = 0;
                        break;
                    }
                }
            }
            // Set 5: 15 points
            if ((scoreTeamA >= 15 || scoreTeamB >= 15) && Math.abs(scoreTeamA - scoreTeamB) >= 2 && (setScoreA[4] > 0 || setScoreB[4] > 0)) {
                setScoreA[5] = scoreTeamA;
                setScoreB[5] = scoreTeamB;
                if (scoreTeamB > scoreTeamA) {
                    wonSetsB++;
                    setWonTeamB[5] = true;
                }
                scoreTeamA = 0;
                scoreTeamB = 0;
            }

            displayForTeamA(scoreTeamA);
            displayForTeamB(scoreTeamB);
            displaySetForTeamA();
            displaySetForTeamB();
            if (wonSetsB == 3) {
                Toast.makeText(getApplicationContext(), "Team B wins!", Toast.LENGTH_SHORT).show();
                tvTeamB.setTextColor(getResources().getColor(R.color.table_text_won));
            }
        }
    }

    /**
     * Add ace for Team B.
     */
    public void addAceTeamB(View view) {
        if (wonSetsA < 3 && wonSetsB < 3) {
            aceTeamB = aceTeamB + 1;
            displayAceTeamB(aceTeamB);
        }
    }
    /**
     * Add challenge for Team B.
     */
    public void addChallengeTeamB(View view) {
        if (wonSetsA < 3 && wonSetsB < 3) {
            challengeTeamB = challengeTeamB + 1;
            displayChallengeTeamB(challengeTeamB);
        }
    }

    /**
     * Reset all scores. Reset set table colors.
     */
    public void resetScore(View view) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        for (int i = 1; i <=5; i++){
            setScoreA[i] = 0;
            setScoreB[i] = 0;
            setWonTeamA[i] = false;
            setWonTeamB[i] = false;
        }
        aceTeamA = 0;
        aceTeamB = 0;
        challengeTeamA = 0;
        challengeTeamB = 0;
        wonSetsA = 0;
        wonSetsB = 0;

        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        displaySetForTeamA();
        displaySetForTeamB();
        displayAceTeamA(aceTeamA);
        displayAceTeamB(aceTeamB);
        displayChallengeTeamA(challengeTeamA);
        displayChallengeTeamB(challengeTeamB);
        resetColor();
    }

    /**
     * Reset set table colors to default.
     */
    public void resetColor() {
        for (int i = 1; i <= 5; i++) {
            tvSetTeamA.get(i).setTextColor(getResources().getColor(R.color.table_text_default));
            tvSetTeamB.get(i).setTextColor(getResources().getColor(R.color.table_text_default));
        }
        tvTeamA.setTextColor(getResources().getColor(R.color.table_text_default));
        tvTeamB.setTextColor(getResources().getColor(R.color.table_text_default));
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int scoreTeamA) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(scoreTeamA));
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int scoreTeamB) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(scoreTeamB));
    }

    /**
     * Displays the given set score for Team A. Adjust text view color.
     */
    public void displaySetForTeamA() {
        for (int i = 1; i <=5; i++){
            tvSetTeamA.get(i).setText(String.valueOf(setScoreA[i]));
            if (setWonTeamA[i]) {
                tvSetTeamA.get(i).setTextColor(getResources().getColor(R.color.table_text_won));
                tvSetTeamB.get(i).setTextColor(Color.parseColor("#000000"));
            }
        }
    }

    /**
     * Displays the given set score for Team B. Adjust text view color.
     */
    public void displaySetForTeamB() {
        for (int i = 1; i <=5; i++){
            tvSetTeamB.get(i).setText(String.valueOf(setScoreB[i]));
            if (setWonTeamB[i]) {
                tvSetTeamB.get(i).setTextColor(getResources().getColor(R.color.table_text_won));
                tvSetTeamA.get(i).setTextColor(Color.parseColor("#000000"));
            }
        }
    }

    /**
     * Displays aces number for Team A.
     */
    public void displayAceTeamA(int aceTeamA) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_ace);
        scoreView.setText(String.valueOf(aceTeamA));
    }

    /**
     * Displays aces number for Team B.
     */
    public void displayAceTeamB(int aceTeamB) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_ace);
        scoreView.setText(String.valueOf(aceTeamB));
    }

    /**
     * Displays challenges number for Team A.
     */
    public void displayChallengeTeamA(int challengeTeamA) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_challenge);
        scoreView.setText(String.valueOf(challengeTeamA));
    }

    /**
     * Displays challenges number for Team B.
     */
    public void displayChallengeTeamB(int challengeTeamB) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_challenge);
        scoreView.setText(String.valueOf(challengeTeamB));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("scoreTeamA", scoreTeamA);
        outState.putInt("scoreTeamB", scoreTeamB);
        outState.putInt("aceTeamA", aceTeamA);
        outState.putInt("aceTeamB", aceTeamB);
        outState.putInt("challengeTeamA", challengeTeamA);
        outState.putInt("challengeTeamB", challengeTeamB);
        outState.putInt("score1TeamA", setScoreA[1]);
        outState.putInt("score1TeamB", setScoreB[1]);
        outState.putInt("score2TeamA", setScoreA[2]);
        outState.putInt("score2TeamB", setScoreB[2]);
        outState.putInt("score3TeamA", setScoreA[3]);
        outState.putInt("score3TeamB", setScoreB[3]);
        outState.putInt("score4TeamA", setScoreA[4]);
        outState.putInt("score4TeamB", setScoreB[4]);
        outState.putInt("score5TeamA", setScoreA[5]);
        outState.putInt("score5TeamB", setScoreB[5]);
        outState.putInt("winSetsA", wonSetsA);
        outState.putInt("winSetsB", wonSetsB);

        outState.putBoolean("set1TeamA", setWonTeamA[1]);
        outState.putBoolean("set1TeamB", setWonTeamB[1]);
        outState.putBoolean("set2TeamA", setWonTeamA[2]);
        outState.putBoolean("set2TeamB", setWonTeamB[2]);
        outState.putBoolean("set3TeamA", setWonTeamA[3]);
        outState.putBoolean("set3TeamB", setWonTeamB[3]);
        outState.putBoolean("set4TeamA", setWonTeamA[4]);
        outState.putBoolean("set4TeamB", setWonTeamB[4]);
        outState.putBoolean("set5TeamA", setWonTeamA[5]);
        outState.putBoolean("set5TeamB", setWonTeamB[5]);

    }
    /**this method will restore all the data saves in the previous method, when rotating the device*/
    /**
     * for this to work you need to give every item that you want to be saves when rotating the device - a specific id on the xml file
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        scoreTeamA = savedInstanceState.getInt("scoreTeamA");
        scoreTeamB = savedInstanceState.getInt("scoreTeamB");
        aceTeamA = savedInstanceState.getInt("aceTeamA");
        aceTeamB = savedInstanceState.getInt("aceTeamB");
        challengeTeamA = savedInstanceState.getInt("challengeTeamA");
        challengeTeamB = savedInstanceState.getInt("challengeTeamB");
        setScoreA[1] = savedInstanceState.getInt("score1TeamA");
        setScoreB[1] = savedInstanceState.getInt("score1TeamB");
        setScoreA[2] = savedInstanceState.getInt("score2TeamA");
        setScoreB[2] = savedInstanceState.getInt("score2TeamB");
        setScoreA[3] = savedInstanceState.getInt("score3TeamA");
        setScoreB[3] = savedInstanceState.getInt("score3TeamB");
        setScoreA[4] = savedInstanceState.getInt("score4TeamA");
        setScoreB[4] = savedInstanceState.getInt("score4TeamB");
        setScoreA[5] = savedInstanceState.getInt("score5TeamA");
        setScoreB[5] = savedInstanceState.getInt("score5TeamB");
        wonSetsA = savedInstanceState.getInt("winSetsA");
        wonSetsB = savedInstanceState.getInt("winSetsB");

        setWonTeamA[1] = savedInstanceState.getBoolean("set1TeamA");
        setWonTeamB[1] = savedInstanceState.getBoolean("set1TeamB");
        setWonTeamA[2] = savedInstanceState.getBoolean("set2TeamA");
        setWonTeamB[2] = savedInstanceState.getBoolean("set2TeamB");
        setWonTeamA[3] = savedInstanceState.getBoolean("set3TeamA");
        setWonTeamB[3] = savedInstanceState.getBoolean("set3TeamB");
        setWonTeamA[4] = savedInstanceState.getBoolean("set4TeamA");
        setWonTeamB[4] = savedInstanceState.getBoolean("set4TeamB");
        setWonTeamA[5] = savedInstanceState.getBoolean("set5TeamA");
        setWonTeamB[5] = savedInstanceState.getBoolean("set5TeamB");

        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        displaySetForTeamA();
        displaySetForTeamB();
        displayAceTeamA(aceTeamA);
        displayAceTeamB(aceTeamB);
        displayChallengeTeamA(challengeTeamA);
        displayChallengeTeamB(challengeTeamB);
    }
}