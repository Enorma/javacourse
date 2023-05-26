package com.javacourse.interfaces.uiwidget;

public class UIWidgetWindowSingle implements CanUIWidget {

    @Override
    public void drag() {
        System.out.println("dragging...");
    }
    //drag

    @Override
    public void render() {
        System.out.println("rendering...");
    }
    //render

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
//UIWidgetWindowSingle

//eof
