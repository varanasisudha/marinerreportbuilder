import exception.FileTypeNotSupportedException;
import generator.ReportGenerator;

import java.util.concurrent.ExecutionException;

public class ReportBuilderDriver {

    public static void main(String args[]) {

        try {
            ReportGenerator reportGenerator = new ReportGenerator();
            if(args.length !=2) {
                System.out.print("please pass source and destination paths");
                System.out.print("usage: ");
                System.exit(1);
            }
            reportGenerator.trigger(args[0], args[1]);
        } catch (InterruptedException |ExecutionException e) {
            System.out.print(e.getMessage());
        } catch (FileTypeNotSupportedException ex) {
            System.out.print(ex.getMessage());
        }

    }

}

