import java.util.List;

public class ContestDriver {
    private ContestService contestService;

    public ContestDriver(ScoringStrategy scoringStrategy) {
        this.contestService = new ContestService(scoringStrategy);
    }

    public void setup() {
        contestService.addProblem("bubble sort array", "Description1", "arrays", "easy", 10);
        contestService.addProblem("selection sort array ", "Description1", "arrays", "easy", 10);
        contestService.addProblem("merge sort array", "Description1", "arrays", "medium", 20);
        contestService.addProblem("heap sort", "Description1", "arrays", "medium", 20);
        contestService.addProblem("reverse LL", "Description2", "LinkedList", "medium", 20);
        contestService.addProblem("shortest path", "Description3", "graph", "hard", 30);

        contestService.addUser("p1", "Vikas", "engineering");
        contestService.addUser("p2", "Ravi", "engineering");
        contestService.addUser("p3", "Rohit", "engineering");
        contestService.addUser("p4", "Vishal", "ABC");

        contestService.solveProblem("p1", "bubble sort array", 30);
        contestService.solveProblem("p2", "bubble sort array", 35);
        contestService.solveProblem("p3", "bubble sort array", 25);
        contestService.solveProblem("p1", "heap sort", 15);
        contestService.solveProblem("p1", "merge sort array", 30);
        contestService.solveProblem("p2", "merge sort array", 25);

        contestService.solveProblem("p4", "bubble sort array", 30);
        contestService.solveProblem("p4", "heap sort", 15);
        contestService.solveProblem("p4", "merge sort array", 15);

    }

    public void printLeader() {
        System.out.println("Current leader is: " + contestService.getLeader());
    }

    public void printSolvedProblemsByUser(String userId) {
        System.out.println("Problems solved by " + contestService.getUsers().get(userId) + ": ");
        List<Problem> solvedByUser = contestService.fetchSolvedProblems(userId);
        int counter=0;
        for (Problem p : solvedByUser) {
            System.out.println(counter+": "+p);
            counter++;
        }
    }

    public void printFetchProblems(String tag, String difficulty, String sortBy) {
        System.out.println("########### FETCH PROBLEMS: tag: " + tag + ", difficulty: " + difficulty + ", sorted by: " + sortBy + " ########");
        List<Problem> problems = contestService.fetchProblems(tag, difficulty, sortBy);
        int counter=1;
        for (Problem p : problems) {
            System.out.println(p);
            counter++;
        }
    }

    public void printTopNProblems(String tag, int n) {
        System.out.println("########### FETCH TOP N PROBLEMS: tag: " + tag + ", N: " + n + " ########");
        List<Problem> topN = contestService.getTopNProblems(tag, n);
        int counter=0;
        for (Problem p : topN) {
            System.out.println(counter+": "+p.getName());
            counter++;
        }
    }

    public void testRecommendations() {
        // Assuming a problem is solved and we want to get recommendations for it
        Problem solvedProblem = new Problem("bubble sort array", "Description1", "arrays", "easy", 30);
        List<Problem> recommendedProblems = contestService.getRecommendations(solvedProblem);
        System.out.println("Recommended problems after solving 'bubble sort array':");
        int counter =1;
        for (Problem p : recommendedProblems) {
            System.out.println(counter+": "+p);
            counter++;
        }
    }
}
