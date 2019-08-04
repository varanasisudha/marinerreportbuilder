import exception.FileTypeNotSupportedException;
import exception.InvalidInputException;
import exception.NoFilesToProcessException;
import generator.ReportGenerator;

import java.util.concurrent.ExecutionException;

public class ReportBuilderDriver {

    public static void main(String args[]) {

        try {
            ReportGenerator reportGenerator = new ReportGenerator();
            if(args.length !=2) {
                throw new InvalidInputException("please pass source and destination paths \n" +
                        "usage: java -jar mariner-report-builder-1.0-SNAPSHOT.jar reports results ");

            } else {
                reportGenerator.trigger(args[0], args[1]);
            }
        } catch (InterruptedException |ExecutionException e) {
            System.out.print(e.getMessage());

        } catch (FileTypeNotSupportedException| NoFilesToProcessException|InvalidInputException ex) {
            System.out.print(ex.getMessage());
        }

    }

}

