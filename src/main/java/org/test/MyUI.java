package org.test;

import com.github.vok.framework.DefaultFilterFieldFactory;
import com.github.vok.framework.FilterRow;
import com.github.vok.framework.sql2o.vaadin.SqlFilterFactory;
import com.github.vok.framework.sql2o.vaadin.VaadinFiltersKt;
import com.github.vokorm.Filter;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;

import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final Grid<Person> grid = new Grid<>(Person.class);
        grid.setSizeFull();
        grid.setDataProvider(Person.createDataProvider());

        final KClass<Person> kclass = (KClass<Person>) Reflection.getOrCreateKotlinClass(Person.class);
        final FilterRow<Person, Filter<Person>> filterRow = VaadinFiltersKt.generateFilterComponents(grid.appendHeaderRow(),
                grid,
                kclass,
                new DefaultFilterFieldFactory<>(new SqlFilterFactory<>()),
                ValueChangeMode.EAGER
        );
        setContent(grid);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
