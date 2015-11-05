package com.vales.general;

import dnl.utils.text.table.TextTable;
import java.io.PrintStream;
import java.util.List;

 class Table {
   private String[][] data;
   private String[] columnNames;
   private TextTable textTable;

    public Table(List resultSet) {
        fillingColumn(resultSet.subList(0, 1));
        fillingData(resultSet);
        textTable = new TextTable(columnNames,data);

    }
    public void printTable(){
        textTable.printTable();
    }
    public void printTable(PrintStream ps, int indent){
        textTable.printTable(ps,indent);
    }
    private void fillingColumn(List resultSet) {

        String[] row = resultSet.get(0).toString().split(",");
        columnNames = new String[row.length];
        String[] temp;
        for (int indexColomn = 0; indexColomn < row.length; indexColomn++) {
            temp = row[indexColomn].split("=");
            columnNames[indexColomn] = temp[0].substring(1,temp[0].length());
        }

    }
    private void fillingData(List resultSet) {
        data = new String[resultSet.size()][columnNames.length];
        for (int indexRow = 0; indexRow < resultSet.size(); indexRow++) {
            String[] row = resultSet.get(indexRow).toString()
                    .substring(0, resultSet.get(indexRow).toString().length() - 1)
                    .split(",");
            String[] temp;
            for (int indexColomn = 0; indexColomn < row.length; indexColomn++) {
                temp = row[indexColomn].split("=");
                data[indexRow][indexColomn] = temp[1];
            }
        }
    }
}
