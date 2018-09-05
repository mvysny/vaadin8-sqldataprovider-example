package org.test;

import com.github.vok.framework.VaadinOnKotlin;
import com.github.vokorm.VokOrm;

import org.flywaydb.core.Flyway;
import org.h2.Driver;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author mavi
 */
@WebListener
public class Bootstrap implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // this configures the database + HikariCP connection pool, in order for the SQLDataProvider to be able to access the database
        // here we simply configure an in-memory H2 database
        VokOrm.INSTANCE.getDataSourceConfig().setDriverClassName(Driver.class.getName());
        VokOrm.INSTANCE.getDataSourceConfig().setJdbcUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        VokOrm.INSTANCE.getDataSourceConfig().setUsername("sa");
        VokOrm.INSTANCE.getDataSourceConfig().setPassword("");
        VaadinOnKotlin.INSTANCE.init();

        // create the 'Person' table
        final Flyway flyway = new Flyway();
        flyway.setDataSource(VokOrm.INSTANCE.getDataSource());
        flyway.migrate();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        VaadinOnKotlin.INSTANCE.destroy();
    }
}
