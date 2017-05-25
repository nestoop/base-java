package cn.java.design.command;

/**
 * Created by botter
 * on 17-5-25.
 */
public class ConcreteCommand extends Command {

    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    void execute() {
        receiver.doSomething();
    }
}
