package net.eckenfels.vaadinsecplay;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.vaadin.annotations.Title;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


@Title("Vaadin Security Playground")
public class StartUI extends UI
{
    private static final long serialVersionUID = 1L;
    private volatile Collection<String> filters;
    private volatile HashMap<String, Whitelist> whitelists;

    private ObjectProperty<String> inputProperty;

    private Label rightLabel2;
    private Label rightLabel1;


    /*
     * After UI class is created, init() is executed. You should build and wire
     * up your user interface here.
     */
    protected void init(VaadinRequest request) {
        initData();
        initLayout();
    }

    private void initData()
    {
        whitelists = new HashMap<String, Whitelist>();

        whitelists.put("Simple Text", Whitelist.simpleText());
        whitelists.put("Basic", Whitelist.basic());
        whitelists.put("Basic with Images", Whitelist.basicWithImages());
        whitelists.put("Relaxed", Whitelist.relaxed());

        Whitelist custom = Whitelist.relaxed();
        custom.addTags("div", "font", "span")
        .addAttributes("div", "align")
        .addAttributes("font", "face", "size")
        .addAttributes("span", "style");
        whitelists.put("Custom Relaxed" ,  custom);
        whitelists.put("UNFILTERED", null);

        // unsorted: filters = whitelists.keySet();
        filters = Arrays.asList("Simple Text", "Basic", "Basic with Images", "Relaxed", "Custom Relaxed", "UNFILTERED");
    }

    /*
     * In this example layouts are programmed in Java. You may choose use a
     * visual editor, CSS or HTML templates for layout instead.
     */
    private void initLayout()
    {
        String START_TEXT = "<h1>test</h1>\n"+
                        "<p>normal <b>bold</b> <i>italic</i> <em>emphasis</em></p>\n"+
                        "<p><font color=\"red\" face=\"Courier New\">green</font> <span style=\"color: green\">red</span></p>";
        inputProperty = new ObjectProperty<String>("", String.class);

        // Left Side

        final TextArea leftArea = new TextArea("Enter HTML", inputProperty);
        leftArea.setWidth(500, Unit.PIXELS);

        final RichTextArea leftRichText = new RichTextArea("Enter RichText", inputProperty);
        leftRichText.setWidth(500, Unit.PIXELS);

        VerticalLayout leftLayout = new VerticalLayout();
        leftLayout.setSpacing(true);
        leftLayout.setMargin(true);
        leftLayout.addComponent(new Label("<b>Input</b><hr>", ContentMode.HTML));
        leftLayout.addComponent(leftArea);
        leftLayout.addComponent(leftRichText);


        // Right Side

        rightLabel1 = new Label("", ContentMode.PREFORMATTED);
        rightLabel1.setImmediate(true);
        final Panel preformattedPanel = new Panel("Preformatted", rightLabel1);

        final Label filterCaption = new Label("Select Rendering Filter: ");
        filterCaption.setWidth(30, Unit.PERCENTAGE);

        final NativeSelect filterSelect = new NativeSelect(null, filters);
        filterSelect.setNullSelectionAllowed(false);
        filterSelect.setImmediate(true);
        filterSelect.setValue("Basic");

        final Component filterComponent = new CssLayout(filterCaption, filterSelect);

        rightLabel2 = new Label("", ContentMode.HTML);
        rightLabel2.setImmediate(true);
        final Panel filteredPanel = new Panel("Rendered", rightLabel2);

        VerticalLayout rightLayout = new VerticalLayout();
        rightLayout.setSpacing(true);
        rightLayout.setMargin(true);
        rightLayout.addComponent(new Label("<b>Output</b><hr>", ContentMode.HTML));
        rightLayout.addComponent(filterComponent);
        rightLayout.addComponent(preformattedPanel);
        rightLayout.addComponent(filteredPanel);

        // Root of the user interface component tree is set

        HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
        splitPanel.addComponent(leftLayout);
        splitPanel.addComponent(rightLayout);
        setContent(splitPanel);

        // Events

        filterSelect.addValueChangeListener(new ValueChangeListener()
        {
            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(ValueChangeEvent event)
            {
                System.out.println("Changed Filter");
                String v = inputProperty.getValue();
                Object f = filterSelect.getValue();
                render(v,f);
            }
        });

        inputProperty.addValueChangeListener(new ValueChangeListener()
        {
            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(ValueChangeEvent event)
            {
                System.out.println("Changed Property");
                String v = inputProperty.getValue();
                Object f = filterSelect.getValue();
                render(v, f);
            }
        });

        // Kick off events

        leftArea.setImmediate(true);
        leftRichText.setImmediate(true);
        inputProperty.setValue(START_TEXT);
}

    protected void render(String v, Object f)
    {
        Whitelist whitelist = (f instanceof String)?whitelists.get((String)f):null;
        if (whitelist != null)
        {
            String filtered = Jsoup.clean(v, whitelist);
            rightLabel1.setValue(filtered);
            rightLabel2.setValue(filtered);
        } else {
            rightLabel1.setValue(v);
            rightLabel2.setValue(v);
        }
    }
}
