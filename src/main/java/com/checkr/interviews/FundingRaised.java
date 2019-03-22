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

            csvData = extracted2(options, csvData, results,1);
        }

        if (options.containsKey(stringKeys[4])) {
            List<String[]> results = new ArrayList<String[]>();

            csvData = extracted2(options, csvData, results,4);;
        }

        if (options.containsKey(stringKeys[5])) {
            List<String[]> results = new ArrayList<String[]>();
            csvData = extracted2(options, csvData, results,5);
        }

        if (options.containsKey(stringKeys[9])) {
            List<String[]> results = new ArrayList<String[]>();

            csvData = extracted2(options, csvData, results,9);
        }

        List<Map<String, String>> output = new ArrayList<Map<String, String>>();
        for (int i = 0; i < csvData.size(); i++) {
            Map<String, String> mapped = new HashMap<String, String>();
            extracted(csvData, mapped, i);
            output.add(mapped);
        }

        return output;
    }

    private static List<String[]> extracted2(Map<String, String> options, List<String[]> csvData,
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
                if (csvData.get(i)[1].equals(options.get(stringKeys[1]))) {
                    extracted(csvData, mapped, i);
                } else {
                    continue;
                }
            }

            if (options.containsKey(stringKeys[4])) {
                if (csvData.get(i)[4].equals(options.get(stringKeys[4]))) {
                    extracted(csvData, mapped, i);
                } else {
                    continue;
                }
            }

            if (options.containsKey(stringKeys[5])) {
                if (csvData.get(i)[5].equals(options.get(stringKeys[5]))) {
                    extracted(csvData, mapped, i);
                } else {
                    continue;
                }
            }

            if (options.containsKey(stringKeys[9])) {
                if (csvData.get(i)[9].equals(options.get(stringKeys[9]))) {
                    extracted(csvData, mapped, i);
                } else {
                    continue;
                }
            }

            return mapped;
        }

        throw new NoSuchEntryException();
    }

    private static void extracted(List<String[]> csvData, Map<String, String> mapped, int i) {
        mapped.put(stringKeys[0], csvData.get(i)[0]);
        mapped.put(stringKeys[1], csvData.get(i)[1]);
        mapped.put(stringKeys[2], csvData.get(i)[2]);
        mapped.put(stringKeys[3], csvData.get(i)[3]);
        mapped.put(stringKeys[4], csvData.get(i)[4]);
        mapped.put(stringKeys[5], csvData.get(i)[5]);
        mapped.put(stringKeys[6], csvData.get(i)[6]);
        mapped.put(stringKeys[7], csvData.get(i)[7]);
        mapped.put(stringKeys[8], csvData.get(i)[8]);
        mapped.put(stringKeys[9], csvData.get(i)[9]);
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
