package View;

import java.util.EventListener;

public interface GetDataFromSipListener extends EventListener {
    public void addActivation(FormEvent e);
    public void updateActivation(FormEvent e);
    public void UpdateNumberRange();
}
