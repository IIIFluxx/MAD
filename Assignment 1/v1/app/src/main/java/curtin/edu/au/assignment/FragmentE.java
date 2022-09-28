package curtin.edu.au.assignment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 27-9-20
 * PURPOSE : Fragment that is associated with displaying the current questions
 */
public class FragmentE extends Fragment
{
    // Private class fields - ImageViews/TextViews/Buttons etc.
    private View view;
    private Button answerOne, answerTwo, answerThree, answerFour;
    private TextView questionText;
    private int questionIndex;
    private QuestionData questionBank;
    private Question currQuestion;
    private Player player;
    private int solution;
    private FragmentC fc;
    private boolean isTablet, isTabletLand;

    //WE WANT A WAY TO ACCESS EACH QUESTION IN THIS FRAGMENT AND CHECK SIZE OF ANS LIST IN EACH Q


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_e, ui, false);

        questionIndex = FragmentD.getIndex(); // returns qIndex from Fragment D. Assigned to local var.

        // Boolean that indicates if tablet mode is active or not.
        isTabletLand = getResources().getBoolean(R.bool.is_tabletLand);
        isTablet = getResources().getBoolean(R.bool.is_tablet);
        questionBank = QuestionData.get();
        currQuestion = questionBank.get(questionIndex);



        questionText = (TextView) view.findViewById(R.id.questionText);
        answerOne = (Button) view.findViewById(R.id.ansOne);
        answerTwo = (Button) view.findViewById(R.id.ansTwo);
        answerThree = (Button) view.findViewById(R.id.ansThree);
        answerFour = (Button) view.findViewById(R.id.ansFour);

        player = Player.get();

        /** TASK 01: Get the current question you're dealing with. */
        if(currQuestion != null)
        {

            int numButtons = currQuestion.getAnswersList().size();

            switch(numButtons) // Setting visibilities
            {
                case 2: // In the case that there are only two answers provided, hide the other two.
                {
                    answerThree.setVisibility(View.GONE);
                    answerFour.setVisibility(View.GONE);
                    break;
                }
                case 3: // In the case that there are only three answers provided, hide the last button.
                {
                    answerFour.setVisibility(View.GONE);
                    break;
                }
                default: {
                    answerOne.setVisibility(View.VISIBLE);
                    answerTwo.setVisibility(View.VISIBLE);
                    answerThree.setVisibility(View.VISIBLE);
                    answerFour.setVisibility(View.VISIBLE);
                }
            }
            /** TASK 03: Fill the buttons and textView with relevant data -- From Question Object */

            questionText.setText(currQuestion.getQuestion()); // sets Q1 text
            answerOne.setText(currQuestion.getAnswersList(0));
            answerTwo.setText(currQuestion.getAnswersList(1));
            if(answerThree.getVisibility() == View.VISIBLE)
            {
                answerThree.setText(currQuestion.getAnswersList(2));
            }
            if(answerFour.getVisibility() == View.VISIBLE)
            {
                answerFour.setText(currQuestion.getAnswersList(3));
            }

            /*if(numButtons == 3)
            {
                answerThree.setText(currQuestion.getAnswersList(2));
            }
            if(numButtons == 4)
            {
                answerFour.setText(currQuestion.getAnswersList(3));
            }*/
        }


        answerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerOne.setBackgroundColor(Color.parseColor("#FFFF99")); // yellow
                checkAnswer(1);
                fc.setPointsTxt();

                if(solution == 1)
                {
                    answerOne.setTextColor(Color.parseColor("#404040")); // grey
                }
                else
                {
                    answerOne.setTextColor(Color.RED);
                }
                answerTwo.setClickable(false);
                answerThree.setClickable(false);
                answerFour.setClickable(false);
                showAnswer();
            }
        });

        answerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                answerTwo.setBackgroundColor(Color.parseColor("#FFFF99")); // yellow
                checkAnswer(2);
                fc.setPointsTxt();
                if(solution == 2)
                {
                    answerTwo.setTextColor(Color.parseColor("#404040")); // grey
                }
                else
                {
                    answerTwo.setTextColor(Color.RED);
                }
                answerOne.setClickable(false);
                answerThree.setClickable(false);
                answerFour.setClickable(false);
                showAnswer();
            }
        });

        answerThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerThree.setBackgroundColor(Color.parseColor("#FFFF99")); // yellow
                checkAnswer(3);
                fc.setPointsTxt();
                if(solution == 3)
                {
                    answerThree.setTextColor(Color.parseColor("#404040")); // grey
                }
                else
                {
                    answerThree.setTextColor(Color.RED);
                }
                answerOne.setClickable(false);
                answerTwo.setClickable(false);
                answerFour.setClickable(false);
                showAnswer();
            }
        });


        answerFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerFour.setBackgroundColor(Color.parseColor("#FFFF99")); // yellow
                checkAnswer(4);
                fc.setPointsTxt();
                if(solution == 4)
                {
                    answerFour.setTextColor(Color.parseColor("#404040")); // grey
                }
                else
                {
                    answerFour.setTextColor(Color.RED);
                }
                answerOne.setClickable(false);
                answerTwo.setClickable(false);
                answerThree.setClickable(false);
                showAnswer();
            }
        });

        return view;
    }


    public void checkAnswer(int selection)
    {
        if(selection == currQuestion.getAnswerPos()) // If correct answer chosen
        {
            // Update points, update Fragment C.
            player.addPoints(currQuestion.getPoints());
            solution = selection;
            if(currQuestion.isSpecial())
            {
                questionBank.setSpecialAnswered(true);
            }
        }
        else
        {
            player.losePoints(currQuestion.getPenalty());
        }

        currQuestion.setAnswered(1);
    }

    public Question getCurrQuestion()
    {
        return currQuestion;
    }

    public void setFragmentC(FragmentC inFragC)
    {
        fc = inFragC;
    }

    public void showAnswer()
    {
        int ans = currQuestion.getAnswerPos();

        switch(ans)
        {
            case 1:
            {
                answerOne.setBackgroundColor(Color.parseColor("#99FFCC"));
                break;
            }

            case 2:
            {
                answerTwo.setBackgroundColor(Color.parseColor("#99FFCC"));
                break;
            }

            case 3:
            {
                answerThree.setBackgroundColor(Color.parseColor("#99FFCC"));
                break;
            }

            case 4:
            {
                answerFour.setBackgroundColor(Color.parseColor("#99FFCC"));
                break;
            }
        }
        if(isTablet || isTabletLand) // If tablet mode, start the appropriate activities
        {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(currQuestion.isSpecial())
            {
                startActivity(new Intent(getContext(), FlagSelectActivity.class));
            }
            else
            {
                startActivity(new Intent(getContext(), QuestionSelectActivity.class));
            }
        }
    }
}
