package org.adrianonobre.scrabble;

/**
 * Created by adriano on 2017-01-05.
 */
public class PlayOutcome {

    public enum OutcomeType {
        SUCCESS,
        FAILURE
    }

    @Override
    public String toString() {
        if (_msg == null) {
            return _outcomeType.name();
        } else {
            return _outcomeType.name() + " - " + _msg;
        }
    }

    private OutcomeType _outcomeType;

    private String _msg;

    public void addScore(int score) {
        playScore += score;
    }

    public int getPlayScore() {
        return playScore;
    }

    private int playScore = 0;

    public OutcomeType getOutcome() {
        return _outcomeType;
    }

    public void setOutcome(OutcomeType outcomeType) {
        setOutcome(outcomeType, null);
    }

    public void setOutcome(OutcomeType outcomeType, String msg) {
        _outcomeType = outcomeType;
        _msg = msg;
    }
}
