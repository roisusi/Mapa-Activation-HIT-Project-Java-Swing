package Model;

import java.util.List;

public class ListOfActivation {

    private List<ActivationFormSip> activationFormSipList;

    public ListOfActivation(List<ActivationFormSip> activationFormSipList) {
        this.activationFormSipList = activationFormSipList;
    }

    public List<ActivationFormSip> getActivationFormSipList() {
        return activationFormSipList;
    }

    public int getSize(){
        return activationFormSipList.size();
    }
}
