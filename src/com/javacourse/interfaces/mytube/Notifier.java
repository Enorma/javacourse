package com.javacourse.interfaces.mytube;

public class Notifier implements Notifiable {

    @Override
    public void notify(User user) {

        System.out.println("notifying " + user.getEmail());
    }
    //sendNotification
}
//Notifier

//eof
