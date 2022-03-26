package controller;

import model.Server;
import model.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer, SelectionPolicy selectionPolicy){
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        this.changeStrategy(selectionPolicy);
        this.servers = new ArrayList<Server>();
        for(int i = 0; i < maxNoServers; i++){
            Server newServer = new Server();
            servers.add(newServer);
            Thread serverThread = new Thread(newServer);
            serverThread.start();
        }
    }

    public void changeStrategy(SelectionPolicy policy){
        if(policy == SelectionPolicy.SHORTEST_QUEUE){
            strategy = new ConcreteStrategyQueue();
        }

        if(policy == SelectionPolicy.SHORTEST_TIME){
            strategy = new ConcreteStrategyTime();
        }
    }

    public void dispatchTask(Task t){
        ConcreteStrategyTime strategy = new ConcreteStrategyTime();
        strategy.addTask(this.servers, t);
    }

    public float getCurrAvgWaiting(){
        float wt = 0;
        int queuesWithWait = 0;
        for(Server s : this.servers){
            wt += s.getQueueWaitingTime();
            if(s.getTasks().size() > 1){
                queuesWithWait++;
            }
        }
        if(queuesWithWait > 0)
            wt /= queuesWithWait;
        else
            wt = 0;
        return wt;
    }

    public int getLineLengths(){
        int length = 0;
        for(Server s : this.servers){
            length += s.getTasks().size();
        }
        return length;
    }

    public List<Server> getServers(){
        return this.servers;
    }

    public String afisare(){
        String finalRes = "";
        for(Server s : servers){
            String res = "Queue " + s.getqID() + ": ";
            if(s.getTasks().size() == 0){
                res += "closed";
            }else{
                Iterator<Task> it = s.getTasks().iterator();
                while(it.hasNext()){
                    res += it.next().toString();
                    res += "; ";
                }
            }
            finalRes += res;
            finalRes += "\n";
        }
        return finalRes;
        }
    }
