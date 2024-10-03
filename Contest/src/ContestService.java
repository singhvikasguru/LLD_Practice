import java.util.*;
import java.util.stream.Collectors;

class ContestService {
    private Map<String, Problem> problemList;
    private Map<String, User> users;
    private ScoringStrategy scoringStrategy;

    public ContestService(ScoringStrategy scoringStrategy) {
        this.problemList = new HashMap<>();
        this.users = new HashMap<>();
        this.scoringStrategy = scoringStrategy;
    }

    public void addProblem(String name, String description, String tag, String difficulty, int score) {
        Problem problem = new Problem(name, description, tag, difficulty, score);
        problemList.put(name, problem);
    }

    public void addUser(String id, String name, String department) {
        User user = new User(id, name, department);
        users.put(id, user);
    }

    public List<Problem> fetchProblems(String tag, String difficulty, String sortBy) {
        List<Problem> filteredProblems = problemList.values().stream()
                .filter(p -> (tag == null || p.getTag().equals(tag)) &&
                        (difficulty == null || p.getDifficulty().equals(difficulty)))
                .collect(Collectors.toList());

        switch (sortBy.toLowerCase()) {
            case "score":
                filteredProblems.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));
                break;
            case "userssolved":
                filteredProblems.sort((p1, p2) -> Integer.compare(p2.getUsersSolved(), p1.getUsersSolved()));
                break;
            case "averagetime":
                filteredProblems.sort((p1, p2)->Double.compare(p2.getAverageTime(), p1.getAverageTime()));
            default:
                System.out.println("Invalid sort criteria. Defaulting to score.");
                filteredProblems.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));
        }

        return filteredProblems;
    }

    public synchronized void solveProblem(String userId, String problemName, long timeTaken) {
        User user = users.get(userId);
        Problem problem = problemList.get(problemName);
        if (user != null && problem != null) {
            int score = scoringStrategy.calculateScore(problem.getScore(), timeTaken, problem.getDifficulty());
            user.addSolvedProblem(problem, score);
            problem.solveProblem(timeTaken);
            // update users solved
        } else {
            System.out.println("Invalid user or problem.");
        }
    }

    public List<Problem> fetchSolvedProblems(String userId) {
        User user = users.get(userId);
        return user != null ? user.getSolvedProblems() : Collections.emptyList();
    }

    public User getLeader() {
        return users.values().stream().max(Comparator.comparingInt(User::getTotalScore)).orElse(null);
    }

    public List<Problem> getTopNProblems(String tag, int n) {
        List<Problem> result = new ArrayList<>();
        for (Problem problem : problemList.values()) {
            if (problem.getTag().equals(tag)) {
                result.add(problem);
            }
        }
        result.sort((p1, p2) -> Integer.compare(p2.getUsersSolved(), p1.getUsersSolved()));
        return result.subList(0, Math.min(n, result.size()));
    }

    List<Problem> getRecommendations(Problem solvedProblem) {
        List<Problem> recommendedProblems = problemList.values().stream()
                .filter(p -> !p.getName().equals(solvedProblem.getName()))
                .filter(p -> p.getTag().equals(solvedProblem.getTag()))
                .sorted((p1, p2) -> Integer.compare(p2.getUsersSolved(), p1.getUsersSolved()))
                .limit(5) // Top 5 recommendations
                .collect(Collectors.toList());

        return recommendedProblems;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    public Map<String, Problem> getProblemList() {
        return problemList;
    }

    public void setProblemList(Map<String, Problem> problemList) {
        this.problemList = problemList;
    }
}
