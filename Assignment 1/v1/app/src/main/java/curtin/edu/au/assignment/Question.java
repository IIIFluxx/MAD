package curtin.edu.au.assignment;

import java.util.List;
/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 27-9-20
 * PURPOSE : Object that stores information relevant to a specific question.
 */
public class Question
{
    private String label, question;
    private int id, penalty, points, answerPos, answered;
    private List<String> answersList;
    private boolean isSpecial, filled;

    public Question(int id, String label, int points, int penalty, String question, List<String> answersList, int answerPos, boolean isSpecial, int answered, boolean filled)
    {
        this.id = id;
        this.label = label; // This would be Q1, Q2, Q3 etc.
        this.points = points; // Points added if you get the question correct.
        this.penalty = penalty; // Penalty of points deducted if you get the question wrong.
        this.question = question;
        this.answersList = answersList;
        this.answerPos = answerPos;
        this.isSpecial = isSpecial;
        this.answered = answered;
        this.filled = filled;
    }

    public int getId() {
        return id;
    }

    public String getLabel()
    {
        return label;
    }

    public int getPenalty() {
        return penalty;
    }

    public int getPoints() {
        return points;
    }

    public String getQuestion()
    {
        return question;
    }

    public List<String> getAnswersList() {
        return answersList;
    }

    public String getAnswersList(int i)
    {
        return answersList.get(i);
    }

    public int getAnswerPos() {
        return answerPos;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public int getAnswered() {
        return answered;
    }

    public void setAnswered(int answered) {
        this.answered = answered;
    } // 0 = Unanswered, 1 = Answered.

    public void specialBonus()
    {
        //if(isSpecial == true)
        //{
            points = points + 10;
        //}
    }

    public boolean getFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}

