package greedy;

import org.junit.Before;
import org.junit.Test;
import utils.Permutation;

import java.util.*;

/**
 * https://www.geeksforgeeks.org/job-sequencing-problem-loss-minimization/
 */
public class JobSequencingProblem {

    private Permutation perm;

    @Before
    public void setUp() throws Exception {
        perm = new Permutation();
    }

    @Test
    public void ExaustiveSearch() {
        List<Job> jobList = new ArrayList<Job>();

        jobList.add(new Job(1, 1, 2));
        jobList.add(new Job(2, 2, 4));
        jobList.add(new Job(3, 3, 1));
        jobList.add(new Job(4, 5, 3));
        jobList.add(new Job(5, 6, 2));


        List<List<Job>> permuations = new LinkedList<List<Job>>();
        perm.permute(jobList, 0, jobList.size()-1, permuations);

        List<Job> winner = null;
        int minCost = Integer.MAX_VALUE;
        for(List<Job> l: permuations) {
            int lossCounter = evaluateLoss(l);
            if(lossCounter<minCost) {
                winner = l;
                minCost = lossCounter;
            }
            //System.out.println(l + "\t " + lossCounter);
        }
        System.out.println(winner + "\t " + minCost);
    }

    private int evaluateLoss(Collection<Job> l) {
        int lossCounter =0;
        int timeCounter =0;
        for (Job j :l) {
            timeCounter += j.Time;
            lossCounter += (timeCounter-j.Time)*j.Loss;
        }
        return lossCounter;
    }

    @Test
    public void GreedySelection() {
        Queue<Job> jobList = new PriorityQueue<Job>();
        jobList.add(new Job(1, 1, 2));
        jobList.add(new Job(2, 2, 4));
        jobList.add(new Job(3, 3, 1));
        jobList.add(new Job(4, 5, 3));
        jobList.add(new Job(5, 6, 2));

        while(!jobList.isEmpty()) {
            Job job = jobList.remove();
            System.out.print(job + " ");
        }
        System.out.println();
    }

    @Test
    public void ExaustiveSearchSecondExample() {
        List<Job> jobList = new ArrayList<Job>();
        jobList.add(new Job(1, 3, 4));
        jobList.add(new Job(2, 1, 1000));
        jobList.add(new Job(3, 2, 2));
        jobList.add(new Job(4, 4, 5));

        List<List<Job>> permuations = new LinkedList<List<Job>>();
        perm.permute(jobList, 0, jobList.size()-1, permuations);

        List<Job> winner = null;
        int minCost = Integer.MAX_VALUE;
        for(List<Job> l: permuations) {
            int timeCounter =0;
            int lossCounter =0;
            for (Job j :l) {
                timeCounter += j.Time;
                lossCounter += (timeCounter-j.Time)*j.Loss;
            }
            if(lossCounter<minCost) {
                winner = l;
                minCost = lossCounter;
            }
        }
        System.out.println(winner + "\t " + minCost);
    }

    class Job implements Comparable {
        private int id;
        int Loss;
        int Time;

        public Job(int id, int loss, int time) {
            this.id = id;
            Loss = loss;
            Time = time;
        }

        @Override
        public String toString() {
            return Integer.toString(id);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Job job = (Job) o;
            return id == job.id &&
                    Loss == job.Loss &&
                    Time == job.Time;
        }

        public int compareTo(Object o) {
            if(this.equals(o)) return 0;
            return this.Loss*((Job)o).Time < ((Job)o).Loss+this.Time ? 1 : -1;
        }
    }
}
