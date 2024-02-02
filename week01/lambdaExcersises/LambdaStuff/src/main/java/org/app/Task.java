package org.app;

public class Task {
    void run(){
        try {
            Thread.sleep(1000);
            if(1+1!=2){
                throw new RuntimeException("error computation failed");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
