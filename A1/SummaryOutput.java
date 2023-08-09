//Summarise results of scheduling algorythms
//Author: Patrick Fleming
//Student Number: c3253586
//date: 2023-08-09

public class SummaryOutput {

    private Output spnOutput;
    private Output ppOutput;
    private Output rrOutput;
    private Output fcfsOutput;

    public SummaryOutput(Output spnOutput, Output ppOutput, Output rrOutput, Output fcfsOutput){
        this.spnOutput = spnOutput;
        this.ppOutput = ppOutput;
        this.rrOutput = rrOutput;
        this.fcfsOutput = fcfsOutput;
    }

    public String toString(){
        String output = "";

        output += "Summary\n";
        output += "Algorithm\tAverage Turnaround Time\tAverage Waiting Time\n";
        output += spnOutput.sum("SPN");
        output += ppOutput.sum("PP");
        output += rrOutput.sum("RR");
        output += fcfsOutput.sum("FCFS");

        return output;
    }
    
    
}
