/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.17.0.2716 modeling language!*/

package cruise.umple.compiler;
import java.io.*;
import java.util.*;
import cruise.umple.util.*;
import cruise.umple.compiler.exceptions.*;

/**
 * @umplesource Generator.ump 112
 * @umplesource Generator_CodeSimpleMetrics.ump 17
 */
// line 112 "../../../../src/Generator.ump"
// line 17 "../../../../src/Generator_CodeSimpleMetrics.ump"
public class SimpleMetricsGenerator implements CodeGenerator
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SimpleMetricsGenerator Attributes
  private UmpleModel model;
  private String output;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SimpleMetricsGenerator()
  {
    model = null;
    output = "";
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setModel(UmpleModel aModel)
  {
    boolean wasSet = false;
    model = aModel;
    wasSet = true;
    return wasSet;
  }

  public boolean setOutput(String aOutput)
  {
    boolean wasSet = false;
    output = aOutput;
    wasSet = true;
    return wasSet;
  }

  /**
   * Contains various aspects from an Umple file (.ump), such as classes, attributes, associations and methods.  Generated output is based
   * off of what's contained in here.
   */
  public UmpleModel getModel()
  {
    return model;
  }

  public String getOutput()
  {
    return output;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "output" + ":" + getOutput()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "model" + "=" + (getModel() != null ? !getModel().equals(this)  ? getModel().toString().replaceAll("  ","    ") : "this" : "null")
     + outputString;
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  //  @umplesourcefile(line={19},file={"Generator_CodeSimpleMetrics.ump"},javaline={89},length={104})
  @umplesourcefile(line={20},file={"Generator_CodeSimpleMetrics.ump"},javaline={90},length={61})
  public void generate()
  {
    StringBuilder code = new StringBuilder();
    StringBuilder transitions = new StringBuilder();
    String className;

    // Output basic gv file header
    code.append("Metrics generated by Umple from "+model.getUmpleFile().getSimpleFileName()+".ump\n\n");

    // Do basic counts
    int smCount = 0;
    int classCount = 0;
    Set<String> allUmpFiles = new HashSet<String>();

    for (UmpleClass uClass : model.getUmpleClasses()) {
      classCount++;
      for (Position p : uClass.getPositions()) {
        allUmpFiles.add(p.getFilename());
      }
      
      for (StateMachine sm : uClass.getStateMachines()) {
        smCount++;
      }
    }

    code.append("class count = "+classCount+"\n");
    code.append("state machine count = "+smCount+"\n");
    code.append("source file count = "+allUmpFiles.size()+"\n");
    code.append("\n");

     // Count lines of coe
    int totalLineCount = 0;    
    for(String fn : allUmpFiles) {

       code.append("  File "+fn+" ");

       int fileLineCount = 0;
       try {
         fileLineCount = countLinesInFile(fn);
         code.append("LOC = "+fileLineCount+"\n");         
       }
       catch (IOException e) {
         try {
           fileLineCount = 
             countLinesInFile(model.getUmpleFile().getPath()+File.separator+fn);
           code.append("LOC = "+fileLineCount+"\n");
         }
         catch (IOException e2) {  
           fileLineCount = 0;
           code.append("Unreadable\n");
          } 
       }
       totalLineCount += fileLineCount;     
    }
    code.append("\n  Total LOC = "+totalLineCount + "\n");

    model.setCode(code.toString());
    writeModel();

    return;
  }

  // Output the metrics to a file 
  @umplesourcefile(line={83},file={"Generator_CodeSimpleMetrics.ump"},javaline={154},length={18})
  private void writeModel()
  {
    try
    {
      String path = model.getUmpleFile().getPath();
      File file = new File(path);
      file.mkdirs();
      String modelFilename = path + File.separator + model.getUmpleFile().getSimpleFileName() + ".metrics";
      BufferedWriter bw = new BufferedWriter(new FileWriter(modelFilename));
      bw.write(model.getCode());
      bw.flush();
      bw.close();
    }
    catch (Exception e)
    {
      throw new UmpleCompilerException("There was a problem with generating simple metrics." + e, e);
    }
  } 
 
  // Code from http://stackoverflow.com/questions/453018/number-of-lines-in-a-file-in-java 
  @umplesourcefile(line={103},file={"Generator_CodeSimpleMetrics.ump"},javaline={175},length={20})
  public int countLinesInFile(String filename) throws IOException {
    InputStream is = new BufferedInputStream(new FileInputStream(filename));
    try {
        byte[] c = new byte[1024];
        int count = 0;
        int readChars = 0;
        boolean empty = true;
        while ((readChars = is.read(c)) != -1) {
            empty = false;
            for (int i = 0; i < readChars; ++i) {
                if (c[i] == '\n') {
                    ++count;
                }
            }
        }
        return (count == 0 && !empty) ? 1 : count;
    } finally {
        is.close();
    }
}

}