package guiPractice;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HandleEvent implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent e) {
        System.out.println("You clicked me!");
    }
    
}
