package bsu.fpmi.educational_practice2019;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.lang.reflect.Method;
import java.util.*;
import javax.swing.*;

public class CustomBean extends JComponent implements BeanInfo {

    private final JLabel label = new JLabel("staticText");
    private final JRadioButton radioButton1 = new JRadioButton("radioButton1");
    private final JRadioButton radioButton2 = new JRadioButton("radioButton2");
    private final JButton button = new JButton("button");
    
    public CustomBean() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(label);
        add(radioButton1);
        add(radioButton2);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomBean.this.fireAcceptEvent(new AcceptEvent(CustomBean.this));
            };
        });
        add(button);
        setVisible(true);
    }

    public String getStaticText() {
        return label.getText();
    }

    public void setStaticText(String staticText) {
        label.setText(staticText);
    }

    public String getRadioButton1Text() {
        return radioButton1.getText();
    }

    public void setRadioButton1Text(String radioButton1Text) {
        radioButton1.setText(radioButton1Text);
    }

    public String getRadioButton2Text() {
        return radioButton2.getText();
    }

    public void setRadioButton2Text(String radioButton2Text) {
        radioButton2.setText(radioButton2Text);
    }

    public boolean isRadioButton1Selected() {
        return radioButton1.isSelected();
    }

    public void setRadioButton1Selected(final boolean radioButton1Selected) {
        radioButton1.setSelected(radioButton1Selected);
    }

    public boolean isRadioButton2Selected() {
        return radioButton2.isSelected();
    }

    public void setRadioButton2Selected(final boolean radioButton2Selected) {
        radioButton2.setSelected(radioButton2Selected);
    }

    public String getButtonText() {
        return button.getText();
    }

    public void setButtonText(String buttonText) {
        button.setText(buttonText);
    }

    @Override
    public BeanDescriptor getBeanDescriptor() {
        return new BeanDescriptor(this.getClass());
    }

    @Override
    public EventSetDescriptor[] getEventSetDescriptors() {
        final EventSetDescriptor[] SET = new EventSetDescriptor[1];
        try {
            SET[0] = new EventSetDescriptor(this.getClass(), "AcceptEvent", AcceptEventListener.class, "acceptEvent");
        } catch (IntrospectionException ie) {
            //ignored
        }
        return SET;
    }

    @Override
    public int getDefaultEventIndex() {
        return 0;
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        final PropertyDescriptor[] SET = new PropertyDescriptor[4];
        try {
            SET[0] = new PropertyDescriptor("staticText", CustomBean.class);
            SET[1] = new PropertyDescriptor("radioButton1Text", CustomBean.class);
            SET[2] = new PropertyDescriptor("radioButton2Text", CustomBean.class);
            SET[3] = new PropertyDescriptor("buttonText", CustomBean.class);
        } catch (IntrospectionException ie) {
            //ignored
        }
        return SET;
    }

    @Override
    public int getDefaultPropertyIndex() {
        return 0;
    }

    @Override
    public MethodDescriptor[] getMethodDescriptors() {
        final int SIZE = CustomBean.class.getMethods().length;
        final MethodDescriptor[] SET = new MethodDescriptor[SIZE];
        for(int i = 0; i < SIZE; ++i) {
            final Method method = CustomBean.class.getMethods()[i];
            SET[i] = new MethodDescriptor(method);
        }
        return SET;
    }

    @Override
    public BeanInfo[] getAdditionalBeanInfo() {
        final BeanInfo SET[] = new BeanInfo[0];
        return SET;
    }

    @Override
    public Image getIcon(final int iconKind) {
        return null;
    }
    
    private final ArrayList<AcceptEventListener> acceptEventListeners = new ArrayList<>();
    
    public void addAcceptEventListener(final AcceptEventListener listener) {
        this.acceptEventListeners.add(listener);
    }
    
    public void removeAcceptEventListener(final AcceptEventListener listener) {
        this.acceptEventListeners.remove(listener);
    }
    
    public void fireAcceptEvent(final AcceptEvent event) {
        for(AcceptEventListener listener : acceptEventListeners) {
            listener.acceptEvent(event);
        }
    }
}
