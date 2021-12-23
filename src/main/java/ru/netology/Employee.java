package ru.netology;

import static ru.netology.Main.myLog;

public class Employee implements Runnable{

    private ATS ats;

    public Employee(ATS ats) {
        this.ats = ats;
    }

    @Override
    public void run() {
        myLog("Сотрудник " + Thread.currentThread().getName() + " вышел на работу");
        String call;
        while (ats.isWorking()){
            //if(Thread.currentThread().isInterrupted()) break;
            //myLog(Thread.currentThread().getName() + " " + (Thread.currentThread().isAlive() ? true : false));
            if ((call = ats.getCall()) != null){
                myLog("Сотрудник " + Thread.currentThread().getName() + " взял звонок " + call + " в работу");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    myLog("InterruptedException Сотрудник " + Thread.currentThread().getName() + " при обработке звонка " + call + ": " + e.getMessage());
                }
                myLog("Сотрудник " + Thread.currentThread().getName() + " закончил обработку звонка " + call);
            }
        }
        myLog("Сотрудник " + Thread.currentThread().getName() + " закончил работу");
    }
}
