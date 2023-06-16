package nl.rug.ai.oop.rpg.model.NPC;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
public class BattleQuestions implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    HashMap<String, ArrayList<String>> questionsAnswers   = new HashMap<String, ArrayList<String>>();
    String correctAnswer;
    String question;

    String victoryText;
    String losingText;
    public BattleQuestions(String question, ArrayList<String> possibleAnswers, String correctAnswer, String victoryText, String losingText){
        questionsAnswers.put(question, possibleAnswers);
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.victoryText = victoryText;
        this.losingText = losingText;
    }

    public ArrayList<String> getAnswers(){
        return questionsAnswers.get(question);
    }

    public String getQuestion(){
        return question;
    }

    public String getVictoryText(){
        return victoryText;
    }

    public String getLosingText(){
        return losingText;
    }
}
