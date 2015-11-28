package com.vales.general.Query;

import java.util.Scanner;

/**
 * Created by Serg on 28.11.2015.
 */
public enum Query {

    CUSTOM_QUERY {
        QueryType type;
        String query;
        Boolean isCheked = false;

        public void requestQuery() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter your query ");
            query = scanner.nextLine();
            checkType();
            isCheked = true;
        }


        @Override
        public String getQuery() {
            if (!isCheked)
                requestQuery();
            return query;
        }

        private void checkType() {
            String newQueryType = checkQueryType(query);
            for (QueryType queryType : QueryType.values())
                if (queryType.toString().equalsIgnoreCase(newQueryType)) {
                    type = queryType;
                    return;
                }
            //Todo must write to log
            System.out.println("Unsupported type" + newQueryType + ". Only:insert, select, update and delete");
        }

        private String checkQueryType(String query) {
            if (query != null && !query.isEmpty()) {
                String[] result = query.trim().replaceAll("^\\s*", "").split(" ", 2);
                result[0] = result[0].toLowerCase();
                return result[0];
            }
            return null;
        }

        @Override
        public QueryType getType() {
            if (!isCheked)
                requestQuery();
            return type;
        }
    },
    SELECT_ATTRTYPE {
        @Override
        public String getQuery() {
            return "select * from attrtype";
        }

        @Override
        public QueryType getType() {
            return QueryType.SELECT;
        }
    },
    SELECT_OBJTYPE {
        @Override
        public String getQuery() {
            return "select * from objtype";
        }

        @Override
        public QueryType getType() {
            return QueryType.SELECT;
        }
    },
    SELECT_ATTRIBUTES {
        @Override
        public String getQuery() {
            return "select * from attributes";
        }

        @Override
        public QueryType getType() {
            return QueryType.SELECT;
        }
    },
    SELECT_OBJREFERENCE {
        @Override
        public String getQuery() {
            return "select * from objreference";
        }

        @Override
        public QueryType getType() {
            return QueryType.SELECT;
        }
    },
    SELECT_OBJECTS {
        @Override
        public String getQuery() {
            return "select * from objects";
        }

        @Override
        public QueryType getType() {
            return QueryType.SELECT;
        }
    };

    public abstract String getQuery();

    public abstract QueryType getType();
}

