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
    
    private final ArrayList<AcceptEventListener> aEventListeners = new ArrayList<>();
    
    public CustomBean() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(label);
        add(radioButton1);
        add(radioButton2);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                CustomBean.this.fireAcceptEvent(new AcceptEvent(CustomBean.this));
            };
        });
        add(button);
        setVisible(true);
    }

    public String getStaticText() {
        return label.getText();
    }

    public void setStaticText(final String staticText) {
        label.setText(staticText);
    }

    public String getRadioButton1Text() {
        return radioButton1.getText();
    }

    public void setRadioButton1Text(final String radioButton1Text) {
        radioButton1.setText(radioButton1Text);
    }

    public String getRadioButton2Text() {
        return radioButton2.getText();
    }

    public void setRadioButton2Text(final String radioButton2Text) {
        radioButton2.setText(radioButton2Text);
    }

    public boolean isRadioButton1Selected() {
        return radioButton1.isSelected();
    }

    public void setRadioButton1Selected(final boolean RB1Selected) {
        radioButton1.setSelected(RB1Selected);
    }

    public boolean isRadioButton2Selected() {
        return radioButton2.isSelected();
    }

    public void setRadioButton2Selected(final boolean RB2Selected) {
        radioButton2.setSelected(RB2Selected);
    }

    public String getButtonText() {
        return button.getText();
    }

    public void setButtonText(final String buttonText) {
        button.setText(buttonText);
    }

    @Override
    public BeanDescriptor getBeanDescriptor() {
        return new BeanDescriptor(this.getClass());
    }

    @Override
    public EventSetDescriptor[] getEventSetDescriptors() {
        final EventSetDescriptor[] set = new EventSetDescriptor[1];
        try {
            set[0] = new EventSetDescriptor(this.getClass(), "AcceptEvent", AcceptEventListener.class, "acceptEvent");
        } catch (IntrospectionException ie) {
            JOptionPane.showMessageDialog(null, ie.getMessage(), ie.getClass().getName(), JOptionPane.ERROR_MESSAGE);
        }
        return set;
    }

    @Override
    public int getDefaultEventIndex() {
        return 0;
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        final PropertyDescriptor[] set = new PropertyDescriptor[4];
        try {
            set[0] = new PropertyDescriptor("staticText", CustomBean.class);
            set[1] = new PropertyDescriptor("radioButton1Text", CustomBean.class);
            set[2] = new PropertyDescriptor("radioButton2Text", CustomBean.class);
            set[3] = new PropertyDescriptor("buttonText", CustomBean.class);
        } catch (IntrospectionException ie) {
            JOptionPane.showMessageDialog(null, ie.getMessage(), ie.getClass().getName(), JOptionPane.ERROR_MESSAGE);
        }
        return set;
    }

    @Override
    public int getDefaultPropertyIndex() {
        return 0;
    }

    @Override
    public MethodDescriptor[] getMethodDescriptors() {
        final int length = CustomBean.class.getMethods().length;
        final MethodDescriptor[] set = new MethodDescriptor[length];
        final Method[] methods = CustomBean.class.getMethods();
        for(int i = 0; i < length; ++i) {
            final Method method = methods[i];
            final MethodDescriptor descriptor = new MethodDescriptor(method);
            set[i] = descriptor;
        }
        return set;
    }

    @Override
    public BeanInfo[] getAdditionalBeanInfo() {
        return new BeanInfo[0];
    }

    @Override
    public Image getIcon(final int iconKind) {
        return null;
    }
    
    public void addAcceptEventListener(final AcceptEventListener listener) {
        this.aEventListeners.add(listener);
    }
    
    public void removeAcceptEventListener(final AcceptEventListener listener) {
        this.aEventListeners.remove(listener);
    }
    
    public void fireAcceptEvent(final AcceptEvent event) {
        for(final AcceptEventListener listener : aEventListeners) {
            listener.acceptEvent(event);
        }
    }
}
