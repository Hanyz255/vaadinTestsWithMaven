package com.mycompany.vaadintestswithmaven;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.Runo;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.mycompany.vaadintestswithmaven.MyAppWidgetset")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true); // Enables margins around whole component (layout)
        layout.setSpacing(true); // Enables spacing between components within the layout
        //layout.setSizeFull(); // Use entire window
        MenuBar barmenu = new MenuBar();
        layout.addComponent(barmenu);

        ProgressBar bar = new ProgressBar();
        bar.setIndeterminate(true);
        layout.addComponent(bar);

        setContent(layout);
        Label lb1 = new Label("Hello!");
        lb1.addStyleName("stylishlabel");
        layout.addComponent(lb1);
        layout.addComponent(lb1);

        final HorizontalLayout hor = new HorizontalLayout();
        hor.setSizeFull(); // Use entire window

        Button button = new Button("Click Me");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                hor.addComponent(new Label("Thank you for clicking"));
            }
        });
        button.setDescription("Testovaci popisek");
        Button button2 = new Button("Notification");
        button2.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Notification.show("This is the caption",
                        "This is the description",
                        Notification.Type.WARNING_MESSAGE);
            }
        });

        layout.addComponent(button);
        layout.addComponent(button2);
        layout.addComponent(hor);
        layout.setExpandRatio(hor, 1); // Expand to fill

        button.setIcon(new ThemeResource("icons/user2.png"));

        Page.getCurrent().setTitle("Test Vaadinu");

        //menu bar 
        MenuItem drinks = barmenu.addItem("Beverages", null, null);

        // Submenu item with a sub-submenu
        MenuItem hots = drinks.addItem("Hot", null, null);

        hots.addItem("Tea",
                new ThemeResource("icons/tea-16px.png"), null);
        hots.addItem("Coffee",
                new ThemeResource("icons/coffee-16px.png"), null);
        // Another submenu item with a sub-submenu
        MenuItem colds = drinks.addItem("Cold", null, null);
        colds.addItem("Milk", null, null);
        colds.addItem("Weissbier", null, null);
        // Another top-level item
        MenuItem snacks = barmenu.addItem("Snacks", null, null);
        snacks.addItem("Weisswurst", null, null);
        snacks.addItem("Bratwurst", null, null);
        snacks.addItem("Currywurst", null, null);
        // Yet another top-level item
        MenuItem servs = barmenu.addItem("Services", null, null);
        servs.addItem("Car Service", null, null);

        //popup view
        // Content for the PopupView
        VerticalLayout popupContent = new VerticalLayout();
        popupContent.addComponent(new TextField("Textfield"));
        popupContent.addComponent(new Button("Button"));
        // The component itself
        PopupView popup = new PopupView("Pop it up", popupContent);
        layout.addComponent(popup);

        BrowserFrame browser = new BrowserFrame("Browser",
                new ExternalResource("http://demo.vaadin.com/sampler"));
        browser.setWidth(100, Unit.PERCENTAGE);
        browser.setHeight("400px");
        //layout.addComponent(browser); 

        TextField tf1 = new TextField("Name");
        tf1.setIcon(FontAwesome.USER);
        tf1.setRequired(true);
        tf1.addValidator(new NullValidator("Must be given", false));
        layout.addComponent(tf1);

        //panel
        Panel panel = new Panel("Astronomer Panel");
        panel.setSizeUndefined(); // Shrink to fit content
        layout.addComponent(panel);
        // Create the content
        FormLayout content = new FormLayout();
        content.addStyleName("mypanelcontent");
        content.addComponent(new TextField("Participant"));
        content.addComponent(new TextField("Organization"));
        content.setSizeUndefined(); // Shrink to fit
        content.setMargin(true);
        panel.setContent(content);

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
