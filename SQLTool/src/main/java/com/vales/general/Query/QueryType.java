package com.vales.general.Query;

public enum QueryType {
    INSERT {
        @Override
        public String toString() {
            return "insert";
        }
    },
    UPDATE {
        @Override
        public String toString() {
            return "update";
        }
    },
    DELETE {
        @Override
        public String toString() {
            return "delete";
        }
    },
   SELECT {
        @Override
        public String toString() {
            return "select";
        }
    };

    @Override
    public abstract String toString();
}
