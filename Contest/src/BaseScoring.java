public class BaseScoring implements ScoringStrategy{
    @Override
    public int calculateScore(int baseScore, long timeTaken, String difficulty) {
        return baseScore;
    }
}
