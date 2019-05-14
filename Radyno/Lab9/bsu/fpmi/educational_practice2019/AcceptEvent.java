package bsu.fpmi.educational_practice2019;

import java.util.EventObject;

public class AcceptEvent extends EventObject {

    public final boolean RB1Selected;
    public final boolean RB2Selected;

    public AcceptEvent(final CustomBean source) {
        super(source);
        RB1Selected = source.isRadioButton1Selected();
        RB2Selected = source.isRadioButton2Selected();
    }
}
