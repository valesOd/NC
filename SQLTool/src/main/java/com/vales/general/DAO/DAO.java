package com.vales.general.DAO;

import com.vales.general.Query.LauncherMethods;
import com.vales.general.Query.Query;
import com.vales.general.Query.QueryType;
import com.vales.general.Menu.Table;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.PrintStream;
import java.util.List;
import java.util.*;

public class DAO {
    private static final Logger GENERAL_LOGGER = LogManager.getLogger(DAO.class);
    private static final PrintStream TABLE_LOGGER = IoBuilder.forLogger(DAO.class).setAutoFlush(true).buildPrintStream();
    private JdbcTemplate jdbcTemplateObject;
    private Map<QueryType,LauncherMethods> command;

    public void initializationCommand() {
        command = new EnumMap<>(QueryType.class);
        LauncherMethods execute = new LauncherMethods() {
            @Override
            public boolean runMethod(Query query) {return updateQuery(query.getQuery(), query.getType().toString());
            }
        };
        LauncherMethods select = new LauncherMethods() {
            @Override
            public boolean runMethod(Query query) {
                return selectQuery(query.getQuery());
            }
        };
        command.put(QueryType.SELECT,select);
        command.put(QueryType.INSERT,execute);
        command.put(QueryType.UPDATE,execute);
        command.put(QueryType.DELETE,execute);
    }

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public boolean executeQuery(Query query){
        command.get(query.getType()).runMethod(query);
        return true;
    }
    private boolean updateQuery(String query, String typeQuery) {
        try {
            Integer value = jdbcTemplateObject.update(query);
            System.out.println(typeQuery + "(ed) " + value + " row(s)");
            GENERAL_LOGGER.info(typeQuery + "(ed) " + value + " row(s)");
            return true;
        } catch (DataAccessException e) {
            GENERAL_LOGGER.warn(e);
        }
        return false;
    }

    private boolean selectQuery(String query) {
        try {
            List resultList = jdbcTemplateObject.queryForList(query);
            if (!resultList.isEmpty()) {
                Table table = new Table(resultList);
                table.printTable();
                table.printTable(TABLE_LOGGER, 0);
                TABLE_LOGGER.flush();
                return true;
            } else {
                System.out.println("0 selected row");
                GENERAL_LOGGER.info("0 selected row");
            }
        } catch (DataAccessException e) {
            GENERAL_LOGGER.error(e);

        }
        return false;
    }
}