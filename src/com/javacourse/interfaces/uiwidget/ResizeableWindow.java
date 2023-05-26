package com.javacourse.interfaces.uiwidget;

public class ResizeableWindow implements Resizeable {

    @Override
    public void resize() {
        System.out.println("resizing by default...");
    }
    //resize

    @Override
    public void resize(int size) {
        System.out.println("resizing to size...");
    }
    //resize

    @Override
    public void resize(int x, int y) {
        System.out.println("resizing to coordinates...");
    }
    //resize

    @Override
    public void resizeTo(CanUIWidget window) {
        System.out.println("resizing to match window...");
    }
    //resizeTo
}
//ResizeableWindow

//eof
