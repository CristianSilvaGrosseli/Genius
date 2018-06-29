package cristian.genius;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SequenceManager {

    private List<GeniusEnums> listOfSteps;
    private Random randomGenerator;
    private int currentIndex;

    public SequenceManager() {

        currentIndex = 0;
        listOfSteps = new ArrayList<GeniusEnums>();
        randomGenerator = new Random();
    }

    //public methods
    public GeniusEnums getCurrentStep()
    {

        return listOfSteps.get(currentIndex);
    }

    public GeniusEnums getStep(int index)
    {
        return listOfSteps.get(index);
    }

    public List getSequence()
    {
       return listOfSteps;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int getSequenceSize() {
        return listOfSteps.size();
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
        if(listOfSteps.isEmpty() || isLastStep())
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

        int randomNum = randomGenerator.nextInt(4);
        GeniusEnums color = GeniusEnums.BUTTON_YELLOW;
        switch(randomNum)
        {
            case 0:
                color = GeniusEnums.BUTTON_BLUE;
                break;
            case 1:
                color = GeniusEnums.BUTTON_RED;
                break;
            case 2:
                color = GeniusEnums.BUTTON_GREEN;
                break;
            case 3:
                color = GeniusEnums.BUTTON_YELLOW;
                break;
        }
        listOfSteps.add(color);
    }

    private void incIndex() {
        currentIndex++;
    }

    private void resetIndex() {
        currentIndex = 0;
    }
}