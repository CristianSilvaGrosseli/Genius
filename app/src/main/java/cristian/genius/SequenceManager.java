package cristian.genius;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SequenceManager {

    private List<Integer> listOfSteps;
    private Random randomGenerator;
    private int currentIndex;

    public SequenceManager() {

        currentIndex = 0;
        listOfSteps = new ArrayList<Integer>();
        randomGenerator = new Random();
    }

    //public methods
    public int getCurrentStep()
    {
        return (int)listOfSteps.get(currentIndex);
    }

    public List getSequence()
    {
       return listOfSteps;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public boolean isLastStep() {
        boolean onLastStep = false;

        int currentPosition = currentIndex + 1;
        int numberOfSteps = listOfSteps.size();
        if(currentPosition == numberOfSteps)
        {
            onLastStep = true;
        }

        return onLastStep;
    }

    public void nextStep()
    {
        if(isLastStep())
        {
            generateRandomStep();
            resetIndex();
        }
        else
        {
            incIndex();
        }
    }

    //private methods
    private void generateRandomStep() {

        listOfSteps.add(randomGenerator.nextInt(4));
    }

    private void incIndex() {
        currentIndex++;
    }

    private void resetIndex() {
        currentIndex = 0;
    }
}