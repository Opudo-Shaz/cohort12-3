package app.utility.db;

import app.utility.bootstrap.InitBootstrap;
import jakarta.enterprise.context.ApplicationScoped;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@InitBootstrap
@ApplicationScoped
public class DataSourceHelper {

    private static volatile DataSource dataSource;

    public DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (DataSourceHelper.class) {
                if (dataSource == null) {
                    try {
                        //initializing jndi context
                        Context initCtx = new InitialContext();

                        //look specific datasource
                        dataSource = (DataSource) initCtx.lookup("java:jboss/datasources/Cohort12DS");

                    } catch (Exception ex){
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
        return dataSource;
    }

    public Connection getConnection() throws SQLException {
        return this.getDataSource().getConnection();
    }
}