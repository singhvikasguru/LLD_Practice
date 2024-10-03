// Interface for scoring strategy
interface ScoringStrategy {
    int calculateScore(int baseScore, long timeTaken, String difficulty);
}
