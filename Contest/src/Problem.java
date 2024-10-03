class Problem {
    private String name;
    private String description;
    private String tag;
    private String difficulty;
    private int score;
    private int usersSolved;
    private long totalTimeTaken;

    public Problem(String name, String description, String tag, String difficulty, int score) {
        this.name = name;
        this.description = description;
        this.tag = tag;
        this.difficulty = difficulty;
        this.score = score;
        this.usersSolved = 0;
        this.totalTimeTaken = 0;
    }

    public void solveProblem(long timeTaken) {
        this.usersSolved++;
        this.totalTimeTaken += timeTaken;
    }

    public double getAverageTime() {
        return usersSolved > 0 ? (double) totalTimeTaken / usersSolved : 0.0;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getScore() {
        return score;
    }

    public int getUsersSolved() {
        return usersSolved;
    }

    @Override
    public String toString() {
        return String.format("Problem[name=%s, tag=%s, difficulty=%s, score=%d, usersSolved=%d, avgTime=%.2f mins]",
                name, tag, difficulty, score, usersSolved, getAverageTime());
    }
}
