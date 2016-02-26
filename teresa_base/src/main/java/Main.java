/**
 * @author Jari Van Melckebeke
 */
public class Main {
    /**
     * in deze methode vind de main runtime plaats.
     *
     * @param args default system variables
     */
    public static void main(String[] args) throws Exception {
        System.out.println("system started");
        Input input = new Input();
        String command = input.getCommand();
        if (command.contains("TERESA")) Output.speak(Action.doAction(command.substring(7)));
        else Output.speak(Action.doAction(command));
    }
}
