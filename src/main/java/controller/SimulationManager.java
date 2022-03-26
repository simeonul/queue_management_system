package controller;

import model.Server;
import model.Task;
import view.SimulationFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimulationManager implements Runnable{
    public int timeLimit;
    public int maxProceessingTime;
    public int minProcessingTime;
    public int maxArrivalTime;
    public int minArrivalTime;
    public int numberOfServers;
    public int numberOfClients;
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;
    private SimulationFrame view;
    public boolean correctInputs = false;

    private Scheduler scheduler;
    private SimulationFrame frame;
    private List<Task> generatedTasks;

    public SimulationManager(SimulationFrame view){
        this.view = view;
        this.view.getFrame().setVisible(true);
        this.generatedTasks = new ArrayList<Task>();
        this.view.newStartButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while(correctInputs == false){
                    timeLimit = view.getTimeValue();
                    maxProceessingTime = view.getMaxProcessingValue();
                    minProcessingTime = view.getMinProcessingValue();
                    maxArrivalTime = view.getMaxArrivalValue();
                    minArrivalTime = view.getMinArrivalValue();
                    numberOfServers = view.getQueuesValue();
                    numberOfClients = view.getClientsValue();

                    if(timeLimit >= 0 && maxProceessingTime >= 0 && minProcessingTime >= 0 && maxArrivalTime >= 0 &&
                            minArrivalTime >= 0 && numberOfClients >=0 && numberOfServers >=0){
                        correctInputs = true;
                    }

                    if(correctInputs == false){
                        view.showInputError("Please only use positive integers as simulation parameters!");
                        view.resetValues();
                    }


                }

                generateNRandomTasks();
                scheduler = new Scheduler(numberOfServers, numberOfClients, selectionPolicy);

                createSimulationThread();
            }
        });
    }

    public void createSimulationThread(){
        Thread t = new Thread(this);
        t.start();
    }


    private void generateNRandomTasks(){
        for(int i = 0; i < numberOfClients; i++){
            Random rand = new Random();
            int processingTime = rand.nextInt(maxProceessingTime - minProcessingTime + 1) + minProcessingTime;
            int arrivalTime = rand.nextInt(maxArrivalTime - minArrivalTime + 1) + minArrivalTime;
            Task newTask = new Task(arrivalTime, processingTime);
            this.generatedTasks.add(newTask);
            Collections.sort(generatedTasks);
        }

    }

    private float getAverageServiceTime(){
        float st = 0;
        for(Task t : this.generatedTasks){
            st += t.getServiceTime();
        }
        st = st / this.numberOfClients;
        return st;
    }


    @Override
    public void run() {

        float avgServiceTime = this.getAverageServiceTime();
        float totalAvgWaiting = 0;
        int maxHour = Integer.MIN_VALUE;
        int maxLineLength = Integer.MIN_VALUE;
        int queuesWithWait = 0;

        this.view.getTextArea().setText(this.view.getTextArea().getText() + "--------TASKS---------\n");
        for(int i = 0 ; i < this.generatedTasks.size(); i++){
            this.view.getTextArea().setText(this.view.getTextArea().getText() + this.generatedTasks.get(i).toString() + "\n");
        }
        this.view.getTextArea().setText(this.view.getTextArea().getText() + "-----------------------------\n");
        int currentTime = 0;

        while(currentTime < timeLimit){


            List<Task> toRemove = new ArrayList<Task>();
            for(Task t : this.generatedTasks) {
                if (t.getArrivalTime() == currentTime) {
                    this.scheduler.dispatchTask(t);
                    toRemove.add(t);
                }
            }

            //System.out.println("Curr avg waiting " + this.scheduler.getCurrAvgWaiting());

            float currAvgWaiting = this.scheduler.getCurrAvgWaiting();

            //System.out.println("Curr Avg Waiting : " + currAvgWaiting);


            if(currAvgWaiting > 0)
                queuesWithWait++;
            totalAvgWaiting += currAvgWaiting;

            int currLineLenghts = this.scheduler.getLineLengths();
            if(currLineLenghts > maxLineLength){
                maxLineLength = currLineLenghts;
                maxHour = currentTime;
                //maxWaiting = currAvgWaiting;
            }

            this.view.getTextArea().setText(this.view.getTextArea().getText() + "TIME " + currentTime + "\n");

            String waitingClient = "Waiting clients: ";
            for(Task t : this.generatedTasks){
                if(!toRemove.contains(t)){
                    waitingClient += t.toString();
                    waitingClient += "; ";
                }
            }
            this.view.getTextArea().setText(this.view.getTextArea().getText() + waitingClient + "\n");
            this.view.getTextArea().setText(this.view.getTextArea().getText() + this.scheduler.afisare() + "\n");

            this.generatedTasks.removeAll(toRemove);
            currentTime++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Eroare la rularea SimulationManager!");
                return;
            }
        }
        for (Server server : this.scheduler.getServers()) {
            server.canBeClosed.set(true);
        }
        if(queuesWithWait == 0){
            totalAvgWaiting = 0;
        }else{
            totalAvgWaiting /= queuesWithWait;
        }
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        //System.out.println("Queues with wait time : " + queuesWithWait);

        this.view.getTextArea().setText(this.view.getTextArea().getText() + "Average waiting time : " + df.format(totalAvgWaiting) + "\n");
        this.view.getTextArea().setText(this.view.getTextArea().getText() + "Average service time : " + df.format(avgServiceTime) + "\n");
        this.view.getTextArea().setText(this.view.getTextArea().getText() + "Peak hour for the simulation : " + maxHour + "\n");
    }
}
