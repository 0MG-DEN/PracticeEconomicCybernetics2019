package bsu.fpmi.educational_practice2019;

import java.util.EventObject;

public class AcceptEvent extends EventObject {

    public final boolean radioButton1Selected;
    public final boolean radioButton2Selected;

    public AcceptEvent(final CustomBean source) {
        super(source);
        radioButton1Selected = source.isRadioButton1Selected();
        radioButton2Selected = source.isRadioButton2Selected();
    }
}
