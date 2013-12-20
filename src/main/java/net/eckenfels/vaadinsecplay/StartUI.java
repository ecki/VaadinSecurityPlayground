package net.eckenfels.vaadinsecplay;

import com.vaadin.annotations.Title;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


@Title("Vaadin Security Playground")
public class StartUI extends UI
{
    private static final long serialVersionUID = 1L;

	/*
	 * After UI class is created, init() is executed. You should build and wire
	 * up your user interface here.
	 */
	protected void init(VaadinRequest request) {
		initLayout();
	}

	/*
	 * In this example layouts are programmed in Java. You may choose use a
	 * visual editor, CSS or HTML templates for layout instead.
	 */
	private void initLayout()
	{
		/* Root of the user interface component tree is set */
		HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
		setContent(splitPanel);

	    VerticalLayout leftLayout = new VerticalLayout();
        leftLayout.setSpacing(true);
        splitPanel.addComponent(leftLayout);

        VerticalLayout rightLayout = new VerticalLayout();
        rightLayout.setSpacing(true);
        splitPanel.addComponent(rightLayout);


        final TextArea leftArea = new TextArea("Enter HTML");
        //leftArea.setTextChangeEventMode(TextChangeEventMode.);t
        //leftArea.setBuffered(false);
        leftArea.setImmediate(true);

        final RichTextArea leftText = new RichTextArea("Enter RichText");
        //leftText.setBuffered(false);
        leftText.setImmediate(true);

        final Label rightLabel1 = new Label("", ContentMode.PREFORMATTED);
        rightLabel1.setImmediate(true);

        final Label rightLabel2 = new Label("", ContentMode.HTML);
        rightLabel2.setImmediate(true);

        final Label rightLabel3 = new Label("", ContentMode.HTML);
        rightLabel3.setImmediate(true);

        leftArea.setValue("<h1>test</h1><p>testtest</p>");
        leftText.setValue("<h1>test</h1><p>testtest</p>");

        leftArea.addValueChangeListener(new ValueChangeListener()
        {
            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(ValueChangeEvent event)
            {
                String v = leftArea.getValue();
                System.out.println("leftArea " + event + "::" + v);
                rightLabel1.setValue(v);
                rightLabel2.setValue(v);
                rightLabel3.setValue(v);
                leftText.setValue(v);
            }
        });

        leftText.addValueChangeListener(new ValueChangeListener()
        {
            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(ValueChangeEvent event)
            {
                String v = leftText.getValue();
                System.out.println("leftText " + event + "::" + v);
                rightLabel1.setValue(v);
                rightLabel2.setValue(v);
                rightLabel3.setValue(v);
                leftArea.setValue(v);
            }
        });

        leftLayout.addComponent(new Label("<b>Input</b><hr>", ContentMode.HTML));
        leftLayout.addComponent(leftArea);
        leftLayout.addComponent(leftText);

        rightLayout.addComponent(new Label("<b>Output</b><hr><p>Escaped:</p>", ContentMode.HTML));
        rightLayout.addComponent(rightLabel1);
        rightLayout.addComponent(new Label("<hr><p>Unfiltered:</p>", ContentMode.HTML));
        rightLayout.addComponent(rightLabel2);
        rightLayout.addComponent(new Label("<hr><p>Filtered:</p>", ContentMode.HTML));
        rightLayout.addComponent(rightLabel3);
        rightLayout.addComponent(new Label("<hr>", ContentMode.HTML));
	}
}
