//formatter for P2 output
//Author: Patrick Fleming
//Student Number: c3253586
//date: 27/08/2023

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class ClusterManagementFormat extends Formatter {
    
        @Override
        public String format(LogRecord record){
            return record.getMessage() + "\n";
        }
    
}
