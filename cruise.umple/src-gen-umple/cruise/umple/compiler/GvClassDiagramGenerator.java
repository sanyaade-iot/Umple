/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.17.0.2716 modeling language!*/

package cruise.umple.compiler;
import java.io.*;
import java.util.*;
import cruise.umple.util.*;
import cruise.umple.compiler.exceptions.*;

/**
 * @umplesource Generator.ump 112
 * @umplesource Generator_CodeGvClassDiagram.ump 18
 */
// line 112 "../../../../src/Generator.ump"
// line 18 "../../../../src/Generator_CodeGvClassDiagram.ump"
public class GvClassDiagramGenerator implements CodeGenerator
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int line();String file();int javaline();int length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GvClassDiagramGenerator Attributes
  private UmpleModel model;
  private String output;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GvClassDiagramGenerator()
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
            "  " + "model" + "=" + getModel() != null ? !getModel() .equals(this)  ? getModel().toString().replaceAll("  ","    ") : "this" : "null"
     + outputString;
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  // line 21 ../../../../src/Generator_CodeGvClassDiagram.ump
  @umplesourcefile(line=21,file="Generator_CodeGvClassDiagram.ump",javaline=91,length=150)
  public void generate()
  {
    StringBuilder code = new StringBuilder();
    StringBuilder associations = new StringBuilder();
    String className;

    // Output basic gv file header
    code.append("// Code generated by Umple\n\n");
    code.append("digraph \""+model.getUmpleFile().getSimpleFileName()+"\" {\n");
    code.append("  size=\"80,10\"\n");
    code.append("  rankdir=\"BT\"\n");
    code.append("  node [shape=record; href=\"javascript:Action.selectClass(\\\"\\N\\\");\"];\n");

    // Set of classes we are visiting
    // We always visit from the top of the hierarchy first
    // This set ensures we track what we have visited
    HashSet visitedClasses = new HashSet();

    // Iterate through each class. 
    for (UmpleClass uClass : model.getUmpleClasses())
    {
      visitClass(uClass, visitedClasses, code, associations);
    } // End iteration through classes
    
    terminateCode(code, associations);
  }

  // Actually output the class contents  
  @umplesourcefile(line=49,file="Generator_CodeGvClassDiagram.ump",javaline=120,length=37)
  private void visitClass(UmpleClass uClass, Set visitedClasses, StringBuilder code, StringBuilder associations)
  {
    // Ensure we only visit once
    if(visitedClasses.contains(uClass)) {
      return;
    }
    
    // Ensure  output parents in the hierarchy first
    UmpleClass parent = uClass.getExtendsClass();
    if(parent != null) {
      visitClass(parent, visitedClasses, code, associations);
    }
    visitedClasses.add(uClass);
          
    String className = uClass.getName();
    String classColor = uClass.getDisplayColor();
    if (!classColor.equals("")) classColor=" style=filled, fillcolor="+classColor+" ";
    code.append("\n  // Class: "+className+"\n");

    code.append("  "+className+" ["+classColor+"shape=record, label=\"{"+className);

    if(uClass.getIsAbstract()) {  // add abstract tag
      code.append("\n&laquo;abstract&raquo;");
    }
      
    // Iterate through attributes of the class
    boolean isFirst = true;
    for (Attribute uAttribute : uClass.getAttributes()) {
      if(isFirst) code.append("|"); // attribute block starter
      else code.append("\\\n"); // separator between attributes
      String type = uAttribute.getType().replace("<","&lt;").replace(">","&gt;");
      code.append(""+uAttribute.getName()+"\\ :\\ "+type+"\\l");
      isFirst = false;
    }

    // Terminate outputting the class attributes
    code.append("}\"");
    
    // Output the tooltip : Class name followed by any comment
    code.append(",\n   tooltip=\"class "+className+"\n");
    for (Comment uComment : uClass.getComments()) {
      String text= uComment.getText().replace("\"","\\\"");
      if(text.startsWith(" *")) {
        code.append(text.substring(2)+"\n");
      }
      else
      {
        code.append(text+"\n");
      }
    }
    code.append("\"");

    // Terminate the class as a whole
    code.append("];\n");


    // Add any generalization now to parents
    // We know that parents have been output first
    UmpleClass parentClass = uClass.getExtendsClass();
    if(parentClass!= null) {
      code.append("  "+className+" -> "+parentClass.getName());
      code.append(" [arrowhead=\"empty\"; samehead=\"gen\"];\n\n");
    }

    // Add any interface implementations so they are output at the end
    for(UmpleInterface uInterface : uClass.getParentInterface()) {
      String intColor = uInterface.getDisplayColor();
      // TO DO needs fixing - interface colour does not appear
      if (!intColor.equals("")) {
        code.append("  "+uInterface.getName()
          +" [style=filled, fillcolor="+intColor+"];\n\n ");
      } 
      code.append("  "+className+" -> "+uInterface.getName());
      code.append(" [  arrowhead=\"empty\"; samehead=\"gen\"; style=dashed];\n\n");
    }

    // Add any associations so they are output at the end
    Set processedAssociations = new HashSet(); // needed to prevent reflexive double count
    for(Association uAssoc : uClass.getAssociations()) {
    
      // Prevent double-counting of reflexive associations
      if(processedAssociations.contains(uAssoc)) continue;
      processedAssociations.add(uAssoc);
      
      AssociationEnd leftEnd = uAssoc.getEnd(0);
      AssociationEnd rightEnd = uAssoc.getEnd(1);
      String arrows;

      // Only output them when visiting one class
      if(leftEnd.getClassName().equals(className)) {
        if(uAssoc.getIsLeftNavigable()) {
          if(uAssoc.getIsRightNavigable()) arrows = "dir=\"none\"";
          else arrows = "dir=\"back\", arrowtail=\"open\"";
        }
        else if(uAssoc.getIsRightNavigable()) {
          arrows = "dir=\"forward\", arrowhead=\"open\"";
        }
        else {
          arrows = "dir=\"none\"";
        }

        associations.append("  "+leftEnd.getClassName()+" -> "+
          rightEnd.getClassName()+" ["+arrows+
          ", taillabel=\""+leftEnd.toSimpleString()+
          " "+leftEnd.getDisplayRoleName()+"\""+
          ", headlabel=\""+rightEnd.toSimpleString()+
          " "+rightEnd.getDisplayRoleName()+"\""+
          "];\n");
      }
    }
  }
  
  @umplesourcefile(line=161,file="Generator_CodeGvClassDiagram.ump",javaline=233,length=6)
  private void terminateCode(StringBuilder code, StringBuilder associations) {

    code.append("\n  // All associations\n");
    code.append(associations);

    code.append("}\n");

    model.setCode(code.toString());
    writeModel();
  } 

  // Used to indent code
  @umplesourcefile(line=173,file="Generator_CodeGvClassDiagram.ump",javaline=246,length=5)
  private void appendSpaces(StringBuilder code, int numSpaces) {
    for(int i=0; i<numSpaces; i++) {
      code.append(" ");
    }
  }

  // Output the graphviz file to a file with the .gv suffix
  @umplesourcefile(line=180,file="Generator_CodeGvClassDiagram.ump",javaline=254,length=18)
  private void writeModel()
  {
    try
    {
      String path = model.getUmpleFile().getPath();
      File file = new File(path);
      file.mkdirs();
      String modelFilename = path + File.separator + model.getUmpleFile().getSimpleFileName() + "cd.gv";
      BufferedWriter bw = new BufferedWriter(new FileWriter(modelFilename));
      bw.write(model.getCode());
      bw.flush();
      bw.close();
    }
    catch (Exception e)
    {
      throw new UmpleCompilerException("There was a problem with generating GraphViz Class Diagram code." + e, e);
    }
  }

}