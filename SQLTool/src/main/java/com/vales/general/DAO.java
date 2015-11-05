package com.vales.general;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.io.PrintStream;
import java.util.List;

public class DAO {
    private static final Logger GENERAL_LOGGER = LogManager.getLogger(DAO.class);
    private static final PrintStream TABLE_LOGGER = IoBuilder.forLogger(DAO.class).setAutoFlush(true).buildPrintStream();
    private JdbcTemplate jdbcTemplateObject;


    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    public boolean executeQuery(String query) {

        GENERAL_LOGGER.info("execute sql (" + query + ")");
        String typeQuery = checkQueryType(query);
        return typeQuery != null && executeQuery(query, typeQuery);
    }
    private static String checkQueryType(String query) {
        if (query != null && !query.isEmpty()) {
            String[] result = query.split(" ", 2);
            result[0] = result[0].toLowerCase();
            return result[0];
        }
        return null;
    }
    private boolean executeQuery(String query, String typeQuery) {

        if ("select".equals(typeQuery)) {
            if (selectQuery(query)) return true;

        } else if ("update".equals(typeQuery)
                || "delete".equals(typeQuery)
                || "insert".equals(typeQuery)) {

            return updateQuery(query, typeQuery);
        } else {
            System.out.println("unsupported or incorrect query (" + query + ").Supported only next operations:select, insert, update, delete");
            GENERAL_LOGGER.warn("unsupported or incorrect query (" + query + ").Supported only next operations:select, insert, update, delete");
        }
        return false;
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