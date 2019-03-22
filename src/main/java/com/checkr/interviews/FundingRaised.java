package com.checkr.interviews;

import java.util.*;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;

public class FundingRaised {

    static String stringKeys[]= {"permalink","company_name","number_employees","category","city","state","funded_date","raised_amount","raised_currency","round"};

    public static List<Map<String, String>> where(Map<String, String> options) throws IOException {
        List<String[]> csvData = new ArrayList<String[]>();
        CSVReader reader = new CSVReader(new FileReader("startup_funding.csv"));
        String[] row = null;
        while((row = reader.readNext()) != null) {
            csvData.add(row);
        }

        reader.close();
        csvData.remove(0);

        if(options.containsKey(stringKeys[1])) {
            List<String[]> results = new ArrayList<String[]> ();

            csvData = asignCSV(options, csvData, results,1);
        }

        if (options.containsKey(stringKeys[4])) {
            List<String[]> results = new ArrayList<String[]>();

            csvData = asignCSV(options, csvData, results,4);;
        }

        if (options.containsKey(stringKeys[5])) {
            List<String[]> results = new ArrayList<String[]>();
            csvData = asignCSV(options, csvData, results,5);
        }

        if (options.containsKey(stringKeys[9])) {
            List<String[]> results = new ArrayList<String[]>();

            csvData = asignCSV(options, csvData, results,9);
        }

        List<Map<String, String>> output = new ArrayList<Map<String, String>>();
        for (int i = 0; i < csvData.size(); i++) {
            Map<String, String> mapped = new HashMap<String, String>();
            extractArrayData(csvData, mapped, i);
            output.add(mapped);
        }

        return output;
    }

    private static List<String[]> asignCSV(Map<String, String> options, List<String[]> csvData,
            List<String[]> results,int h) {
        for (int i = 0; i < csvData.size(); i++) {
            if (csvData.get(i)[h].equals(options.get(stringKeys[h]))) {
                results.add(csvData.get(i));
            }
        }
        csvData = results;
        return csvData;
    }

    public static Map<String, String> findBy(Map<String, String> options) throws IOException, NoSuchEntryException {
        List<String[]> csvData = new ArrayList<String[]>();
        CSVReader reader = new CSVReader(new FileReader("startup_funding.csv"));
        String[] row = null;

        while ((row = reader.readNext()) != null) {
            csvData.add(row);
        }

        reader.close();
        csvData.remove(0);
        Map<String, String> mapped = new HashMap<String, String>();

        for (int i = 0; i < csvData.size(); i++) {
            if (options.containsKey(stringKeys[1])) {
                if (isEqual(options.get(stringKeys[1]), csvData.get(i)[1])) {
                    extractArrayData(csvData, mapped, i);
                } else {
                    continue;
                }
            }

            if (options.containsKey(stringKeys[4])) {
                if (isEqual(options.get(stringKeys[4]), csvData.get(i)[4])) {
                    extractArrayData(csvData, mapped, i);
                } else {
                    continue;
                }
            }

            if (options.containsKey(stringKeys[5])) {
                if (isEqual(options.get(stringKeys[5]), csvData.get(i)[5])) {
                    extractArrayData(csvData, mapped, i);
                } else {
                    continue;
                }
            }

            if (options.containsKey(stringKeys[9])) {
                if (isEqual(options.get(stringKeys[9]), csvData.get(i)[9])) {
                    extractArrayData(csvData, mapped, i);
                } else {
                    continue;
                }
            }

            return mapped;
        }

        throw new NoSuchEntryException();
    }

    private static boolean isEqual(String optionsOne, String csvDataOne) {
        return csvDataOne.equals(optionsOne);
    }

    private static void extractArrayData(List<String[]> csvData, Map<String, String> mapped, int i) {
        for(int t=0 ; t<10 ; t++){
            mapped.put(stringKeys[t], csvData.get(i)[t]);
        }
    }

    public static void main(String[] args) {
        try {
            Map<String, String> options = new HashMap<String, String> ();
            options.put("company_name", "Facebook");
            options.put("round", "a");
            System.out.print(FundingRaised.where(options).size());
        } catch(IOException e) {
            System.out.print(e.getMessage());
            System.out.print("error");
        }
    }
}

class NoSuchEntryException extends Exception {}
