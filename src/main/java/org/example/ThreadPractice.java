package org.example;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ThreadPractice {
    public static void main(String[] args) throws IOException {
//        multiThreads();
//        throw new CheckedException("error");
//        throw new UncheckedException("runtime error");
//        readExcel();
//        wrightExcel();
        readText();
    }



    public static void readExcel() throws IOException {
        FileInputStream fis =new FileInputStream(new File("C:\\Users\\dell\\Downloads\\Questionnaire - QA.XLSX"));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet=wb.getSheet("Sheet1");
        int rows=sheet.getLastRowNum();
        for (int i = 0; i < rows; i++) {
            XSSFRow row=sheet.getRow(i);
            int cols=0;
            try{
                 cols=row.getLastCellNum();
            }catch (Exception e){

            }

            for (int j = 0; j <cols ; j++) {
                String cell=new DataFormatter().formatCellValue(row.getCell(j));
                System.out.print(cell+" $");
            }
            System.out.println();
        }


    }

    public static void wrightExcel() throws IOException {
        List<String> countries = new ArrayList<>(List.of(
                "India", "United States", "Canada", "United Kingdom", "Australia",
                "Germany", "France", "Italy", "Spain", "Brazil",
                "Mexico", "China", "Japan", "South Korea", "Russia",
                "South Africa", "New Zealand", "Singapore", "UAE", "Netherlands"
        ));
        List<String> commonThings = new ArrayList<>();

        commonThings.add("National Flag");
        commonThings.add("Capital City");
        commonThings.add("National Anthem");
        commonThings.add("Official Language");
        commonThings.add("Currency");
        commonThings.add("Government");
        commonThings.add("Population");
        commonThings.add("Geographical Borders");
        commonThings.add("Time Zone");
        commonThings.add("Culture and Traditions");
        commonThings.add("Food and Cuisine");
        commonThings.add("Education System");
        commonThings.add("Healthcare System");
        commonThings.add("Economy");
        commonThings.add("Tourist Places");
        commonThings.add("Climate");
        commonThings.add("Religion");
        commonThings.add("Transportation System");
        commonThings.add("National Holidays");
        commonThings.add("Defense Forces");

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet=wb.createSheet("Sheet1");
        for (int i = 0; i <countries.size() ; i++) {
            XSSFRow row=sheet.createRow(i);
            for (int j = 0; j < commonThings.size(); j++) {
                XSSFCell cell=row.createCell(j);
                if(j==0){
                    cell.setCellValue(countries.get(i));

                }else {
                    cell.setCellValue(commonThings.get(j));
                }

            }
        }
        FileOutputStream fos=new FileOutputStream("C:\\Users\\dell\\Downloads\\Countries.XLSX");
        wb.write(fos);
        wb.close();
        fos.close();


    }
    public static  void multiThreads(){
        int recordsPerThread = 5000; // reduce if you required results in less time
        // if you use 5000  then you will get results in 100 minutes for 1M records
        List<String> records = new ArrayList<>();   // you will get list of record from file downloaded from GCP
        int threads = records.size() / recordsPerThread;   // creating 10 threads
        List<Thread> list = new ArrayList<>();

        for (int i = 0; i < threads; i++) {
            final int count = i;

            Runnable r = () -> {
                for (int j = count * recordsPerThread; j < count + recordsPerThread; j++) {

                    /*
                     Add your code to execute API  and verify response
                     try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                            System.out.println("child thread " + count + " " + j);*/
                }
            };

            Thread t = new Thread(r);
            list.add(t);
        }


        for (Thread t : list) {
            t.start();
        }
    }

    public static void readText() throws IOException {

        BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\dell\\Downloads\\TextFile.txt"));
       br.lines().forEach((line->{
           System.out.println(line);
       }));
    }
}
class CheckedException extends Exception{
   public CheckedException(String message){
       super(message);
   }
}
class UncheckedException extends RuntimeException{
    public UncheckedException(String message){
        super(message);
    }
}

