package model;

import java.util.Iterator;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private AtomicInteger waitingPeriod;
    private BlockingQueue<Task> tasks;
    private int qID;
    private static int queueNo = 0;
    public AtomicBoolean canBeClosed;

    public Server(){
        queueNo++;
        this.qID = queueNo;
        this.tasks = new LinkedBlockingQueue<>();
        this.waitingPeriod = new AtomicInteger(0);
        this.canBeClosed = new AtomicBoolean(false);
    }

    public void addTask(Task newTask){
        this.waitingPeriod.addAndGet(newTask.getServiceTime());
        this.tasks.add(newTask);
    }

    public int getQueueWaitingTime(){
        int waitingTime = 0;
        if(this.tasks.size() > 1){
            Iterator<Task> it = this.tasks.iterator();
            int index = 0;
            while(it.hasNext() && index < this.tasks.size() - 1){
                waitingTime += it.next().getServiceTime();
                index++;
            }
        }
        return waitingTime;
    }

    @Override
    public void run() {
        while(canBeClosed.get() != true){
            while(this.tasks.size() >0) {
                Task head = this.tasks.peek();
                int time = head.getServiceTime();
                while (head.getServiceTime() != 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.printf("Interrupted thread while trying to put it to sleep");
                        return;
                    }
                    this.waitingPeriod.decrementAndGet();
                    head.setServiceTime(--time);
                    if(head.getServiceTime() == 0){
                        this.tasks.remove(head);
                    }
                }
            }
        }
    }

    public BlockingQueue<Task> getTasks(){
        return this.tasks;
    }

    public int getWaitingPeriod(){
        return this.waitingPeriod.get();
    }

    public void setWaitingPeriod(int value){
        this.waitingPeriod.set(value);
    }

    public int getqID(){
        return this.qID;
    }

    }

