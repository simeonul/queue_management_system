package controller;

import model.Server;
import model.Task;

import java.util.List;

public class ConcreteStrategyTime implements Strategy{
    @Override
    public void addTask(List<Server> servers, Task t) {
        int min_total = Integer.MAX_VALUE;
        Server aux = new Server();
        aux.setWaitingPeriod(min_total);
        for(Server s : servers){
            if(s.getWaitingPeriod() < min_total){
                min_total = s.getWaitingPeriod();
                aux = s;
            }
        }
        aux.addTask(t);

    }
}
