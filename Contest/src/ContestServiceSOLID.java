import java.util.*;
import java.util.stream.Collectors;

class ContestServiceSOLID {
    private ProblemRepository problemRepository;
    private UserRepository userRepository;
    private ScoringStrategy scoringStrategy;

    public ContestServiceSOLID(ProblemRepository problemRepository, UserRepository userRepository, ScoringStrategy scoringStrategy) {
        this.problemRepository = problemRepository;
        this.userRepository = userRepository;
        this.scoringStrategy = scoringStrategy;
    }

    public void addProblem(String name, String description, String tag, String difficulty, int score) {
        Problem problem = new Problem(name, description, tag, difficulty, score);
        problemRepository.addProblem(problem);
    }

    public void addUser(String id, String name, String department) {
        User user = new User(id, name, department);
        userRepository.addUser(user);
    }

    public List<Problem> fetchProblems(String tag, String difficulty, String sortBy) {

        return problemRepository.getProblems(tag, difficulty, sortBy);
    }

    public void solveProblem(String userId, String problemName, long timeTaken) {
        User user = userRepository.getUser(userId);
        Problem problem = problemRepository.getProblem(problemName);
        if (user != null && problem != null) {
            int score = scoringStrategy.calculateScore(problem.getScore(), timeTaken, problem.getDifficulty());
            user.addSolvedProblem(problem, score);
            problem.solveProblem(timeTaken);
//            userRepository.addUser(user); // Update user data after solving a problem
        } else {
            System.out.println("Invalid user or problem.");
        }
    }

    public List<Problem> fetchSolvedProblems(String userId) {
        User user = userRepository.getUser(userId);
        return user != null ? user.getSolvedProblems() : Collections.emptyList();
    }

    public User getLeader() {
        return userRepository.getAllUsers().stream()
                .max(Comparator.comparingInt(User::getTotalScore))
                .orElse(null);
    }

    public List<Problem> getTopNProblems(String tag, int n) {
        List<Problem> result = problemRepository.getProblems().stream()
                .filter(problem -> problem.getTag().equals(tag))
                .sorted((p1, p2) -> Integer.compare(p2.getUsersSolved(), p1.getUsersSolved()))
                .collect(Collectors.toList());
        return result.subList(0, Math.min(n, result.size()));
    }

    public List<Problem> getRecommendations(Problem solvedProblem) {
        return problemRepository.getProblems().stream()
                .filter(p -> !p.getName().equals(solvedProblem.getName()))
                .filter(p -> p.getTag().equals(solvedProblem.getTag()))
                .sorted((p1, p2) -> Integer.compare(p2.getUsersSolved(), p1.getUsersSolved()))
                .limit(5) // Top 5 recommendations
                .collect(Collectors.toList());
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ProblemRepository getProblemRepository() {
        return problemRepository;
    }

    public void setProblemRepository(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }
}
