package cn.java.design.command;

/**
 * Created by botter
 * on 17-5-20.
 */
public class Invoker {

    private Command command;

    public Invoker (Command command) {
        this.command = command;
    }

    public void action() {
        command.execute();
    }
}
