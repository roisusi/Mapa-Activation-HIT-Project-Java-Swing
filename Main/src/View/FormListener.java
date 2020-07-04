package View;

import java.util.EventListener;
//Interface to use Froms events
public interface FormListener extends EventListener {
    public void formEventOccurred(FormEvent e);
    public void formEventOccurredNumber(FormEvent e);
}

