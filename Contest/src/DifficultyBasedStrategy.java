public class DifficultyBasedStrategy implements ScoringStrategy {
    @Override
    public int calculateScore(int baseScore, long timeTaken, String difficulty)
    {
        if(difficulty.equals("moderate"))
            return 2*baseScore;
        else if(difficulty.equals(("hard")))
            return 4*baseScore;
        else
            return baseScore;
    }
}
