package nl.rug.ai.oop.rpg.model.npc.battles;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Represents a collection of multiple-choice questions and answers, along with appropriate response texts.
 * These are used within the BattleEvent events as content.
 *
 * @author Kyriakos Hjikakou
 */
public class BattleQuestions implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final HashMap<String, ArrayList<String>> questionsAnswers   = new HashMap<String, ArrayList<String>>();
    private final String correctAnswer;
    private final String question;
    private final String victoryText;
    private final String losingText;

    /**
     * Constructs a new BattleQuestions instance.
     *
     * @param question the multiple-choice question.
     * @param possibleAnswers the list of possible answers for the question.
     * @param correctAnswer the correct answer for the question.
     * @param victoryText the response text when clicking the correct answer.
     * @param losingText the response text when clicking the incorrect answer.
     */
    public BattleQuestions(String question, ArrayList<String> possibleAnswers, String correctAnswer, String victoryText, String losingText){
        questionsAnswers.put(question, possibleAnswers);
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.victoryText = victoryText;
        this.losingText = losingText;
    }

    /**
     * Returns the list of possible answers for the current question.
     *
     * @return the list of possible answers.
     */
    public ArrayList<String> getAnswers(){
        return questionsAnswers.get(question);
    }

    /**
     * Returns the current question.
     *
     * @return the current question.
     */
    public String getQuestion(){
        return question;
    }

    /**
     * Returns the correct answer for the current question.
     *
     * @return the correct answer.
     */
    public String getCorrectAnswer(){
        return  correctAnswer;
    }

    /**
     * Returns the victory text displayed upon answering the question correctly.
     *
     * @return the victory text.
     */
    public String getVictoryText(){
        return victoryText;
    }

    /**
     * Returns the losing text displayed upon answering the question incorrectly.
     *
     * @return the losing text.
     */
    public String getLosingText(){
        return losingText;
    }
}
