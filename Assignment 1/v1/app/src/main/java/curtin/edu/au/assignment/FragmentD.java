package curtin.edu.au.assignment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 27-9-20
 * PURPOSE : Fragment that is associated with displaying all questions
 */

public class FragmentD extends Fragment
{
    private View view;
    private QGridAdapter adapter;
    private QuestionData question;
    private GridLayoutManager glm;
    private FlagData flagdata;
    private Flag currFlag;
    private int flagIndex;
    public static int qIndex = 0;
    private static int numberOfQns;
    private static int numberOfAnswered;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_d, ui, false);

        // Have your data ready.
        question = QuestionData.get();

        //flagIndex = FragmentB.getIndex();
        // We have the index of the flag we are in, in here. So from that we can get the Flag
        flagdata = FlagData.get();
        currFlag = flagdata.get(FragmentB.fIndex);

            /*  Now we have the Flag we clicked on.
            So NOW we can load Questions SPECIFIC to the Flag.
            As in, load ONLY if question.getID = currFlag.getDrawableID
        */

        glm = new GridLayoutManager(
                getActivity(), question.getCols(),
                flagdata.getOrientation(),
                //GridLayoutManager.VERTICAL,
                false);


        // Obtain the RecyclerView UI element
        RecyclerView recycView = (RecyclerView)view.findViewById(R.id.questionRecyclerView);
        // Specify how it should be laid out

        recycView.setLayoutManager(glm);
        adapter = new QGridAdapter(question);
        recycView.setAdapter(adapter);
        return view;
    }


    public void setColumns(int ii)
    {
        question.setCols(ii);
        adapter.notifyDataSetChanged();
    }

    public void setOrientation(int ii) {
        flagdata.setOrientation(ii);
        adapter.notifyDataSetChanged();
    }

    public void refresh()
    {
        adapter.notifyDataSetChanged();
    }

    public GridLayoutManager getGlm()
    {
        return glm;
    }

    public QGridAdapter getAdapter() {
        return adapter;
    }

    // ========== ADAPTER ===============

    private class QGridAdapter extends RecyclerView.Adapter<QGridViewHolder>
    {
        private QuestionData qd;

        public QGridAdapter(QuestionData qd)
        {
            this.qd = qd; //Store reference to QuestionData (which will contain Question objects)
        }


        @Override
        public QGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater li = LayoutInflater.from(getActivity());
            return new QGridViewHolder(li, parent);
        } // Done.

        @Override
        public void onBindViewHolder(QGridViewHolder vh, int position)
        {
            vh.bind(qd.get(position));
        }

        @Override
        public int getItemCount()
        {
            return (qd.size());
        }

    }

    // ----------------------
    // ========== VIEW HOLDER ===============
    private class QGridViewHolder extends RecyclerView.ViewHolder
    {
        // Declaration of variables
        TextView questionText, pointsText,penaltyText;
        Question currQuestion;
        LinearLayout lLayout;


        public QGridViewHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.list_question, parent, false));
            questionText = (TextView) itemView.findViewById(R.id.questionText);
            pointsText = (TextView) itemView.findViewById(R.id.pointsText);
            penaltyText = (TextView) itemView.findViewById(R.id.penaltyText);
            lLayout = (LinearLayout)itemView.findViewById(R.id.textBox);
        }

        public void bind(Question data)
        {

            currQuestion = data; // Dealing with ONE QUESTION AT A TIME - e.g. Question 1
            currFlag = flagdata.get(FragmentB.fIndex);

            numberOfQns = getVisibleNumQns();
            if(currQuestion != null && currFlag.getDrawableId() == currQuestion.getId()) // Gets all Questions
            {
                //numberOfQns = getVisibleNumQns();
                numberOfQns = 1;
                // Append special tag if the question is Special.
                if(currQuestion.isSpecial() == true)
                {
                    questionText.setText("<Special>\n" + currQuestion.getLabel()); // sets Q1 text
                }
                else
                {
                    questionText.setText(data.getLabel()); // sets Q1 text
                }

                //questionText.setText("Num of qns: " + numberOfQns); // test code, comment out

                penaltyText.setText("Penalty: " + String.valueOf(currQuestion.getPenalty()));
                pointsText.setText("Points: " + String.valueOf(currQuestion.getPoints()));


                if(question.getSpecialAnswered()) // Currently applies to ALL questions.
                {
                    currQuestion.specialBonus();
                    pointsText.setText("Points: " + String.valueOf(data.getPoints()));
                    pointsText.setTextColor(Color.GREEN);
                    question.setSpecialAnswered(false); // This line resets the special Ans -- should only apply to questions of ONE FLAG
                }

                if(currQuestion.getAnswered() == 1)
                {
                    numberOfAnswered++; // Keeps track of how many questions have been answered.
                    lLayout.setEnabled(false);
                    lLayout.setBackgroundColor(Color.LTGRAY);
                    if(currQuestion.getFilled() == false)
                    {
                        currQuestion.setFilled(true);
                        //lLayout.setBackgroundColor(Color.GREEN); // test -- delete
                    }

                    // Increments a tally here. All FragmentD cares about is tally. <------------------->
                    // This tally affects the current Flag, which is dealt with in FragmentB

                    //TODO: If tally = num of questions, return button to send back
                    // to FlagSelectActivity. Then in FragmentB.java, if tally is reached, setEnabled(false).
                }

                else
                {
                    // We want to pass over the index of the Question, so that we display the right Question.
                    lLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            boolean isTabletLand = getResources().getBoolean(R.bool.is_tabletLand);
                            qIndex = getAbsoluteAdapterPosition();
                            if(isTabletLand) // WE DON'T WANT TO DO ANYTHING
                            {// TODO: Make it refresh fragment instead of starting a new activity
                                startActivity(new Intent(getContext(), QuestionSelectActivity.class)); // Uncomment if refreshing doesn't work.
                            }
                            else // if it's a phone
                            {
                                startActivity(new Intent(getContext(), AnswerSelectActivity.class));
                            }

                        }
                    });
                }
                if(numberOfAnswered == numberOfQns && numberOfQns != 0 && numberOfAnswered != 0)
                {
                    //lLayout.setBackgroundColor(Color.GREEN); // Test code: Sets BG for each Qn done -- not locked out.
                    currFlag.setFilled();
                    // TODO: Is there where you reset tally?.
                    numberOfAnswered = 0;
                    numberOfQns = 0;
                }
            } // Exit from big if statement (now dealing with all flags)
            else // All questions belonging to flags other than the current one.
            {
                lLayout.setVisibility(View.GONE);
                questionText.setVisibility(View.GONE);
                pointsText.setVisibility(View.GONE);
                penaltyText.setVisibility(View.GONE);
            }


            // Check if this is breaking program or not.
            //resetAnsweredQnsTally();
            //resetNumberOfQnsTally();
            numberOfQns = 0;

            //questionText.setText("Num of qns: " + numberOfQns); // comment out pls
        }
    }

    public static int getIndex() {
        return qIndex;
    }

    // =========== Tally methods

/*    public int getNumberOfQnsTally() {
        return numberOfQnsTally;
    }

    public int getAnsweredQnsTally() {
        return answeredQnsTally;
    }*/

/*    public void incAnsweredQnsTally() {
        this.answeredQnsTally = this.answeredQnsTally + 1;
    }

    public void incNumberOfQnsTally( ) {
        this.numberOfQnsTally = this.numberOfQnsTally + 1;
    }

    public void resetAnsweredQnsTally() {
        this.answeredQnsTally = 0;
    }

    public void resetNumberOfQnsTally( ) {
        this.numberOfQnsTally = 0;
    }*/
    // Gets the current number of visible questions.
    public int getVisibleNumQns()
    {
        int vis = 0;
        for(vis = glm.findFirstVisibleItemPosition(); vis <= glm.findLastVisibleItemPosition(); vis++)
        {
            View view = glm.getChildAt(vis);
            if (glm.getChildAt(vis) != null)
            {
                vis++;
            }
        }
        //vis = (glm.findLastVisibleItemPosition() - glm.findFirstVisibleItemPosition()) + 1;
        return vis;
    }


}
