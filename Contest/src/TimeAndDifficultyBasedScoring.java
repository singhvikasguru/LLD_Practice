class TimeAndDifficultyBasedScoring implements ScoringStrategy {

    @Override
    public int calculateScore(int baseScore, long timeTaken, String difficulty) {
        int timePenalty = (int) (timeTaken / 10);

        // Bonus points based on difficulty level
        int difficultyBonus = 0;
        switch (difficulty.toLowerCase()) {
            case "easy":
                difficultyBonus = 0;
                break;
            case "medium":
                difficultyBonus = 5;
                break;
            case "hard":
                difficultyBonus = 10;
                break;
        }

        int finalScore = baseScore - timePenalty + difficultyBonus;
        return Math.max(finalScore, 0);
    }
}
