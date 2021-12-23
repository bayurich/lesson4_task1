package ru.netology;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ATS {

    private boolean isWorking = true;
    private Queue<String> calls = new ConcurrentLinkedQueue<>();

    public boolean addCall(String name){
        return calls.add(name);
    }

    public String getCall(){
        return calls.poll();
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }
}
