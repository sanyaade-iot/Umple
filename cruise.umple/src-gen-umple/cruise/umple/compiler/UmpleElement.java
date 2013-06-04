/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.17.0.2716 modeling language!*/

package cruise.umple.compiler;
import cruise.umple.compiler.Position;
import java.util.*;

/**
 * An UmpleElement is one of the top-level items found in an Umple model
 * Currently it has one subclass, UmpleClassifier
 * @umplesource Umple.ump 72
 * @umplesource Umple_Code.ump 622
 */
// line 72 "../../../../src/Umple.ump"
// line 622 "../../../../src/Umple_Code.ump"
public class UmpleElement
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //UmpleElement Attributes
  private String name;
  private String modifier;
  private List<String> namespaces;
  private String packageName;
  private String extraCode;
  private boolean isInternal;
  private Coordinate coordinates;
  private String displayColor;

  //UmpleElement Associations
  private List<Position> positions;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public UmpleElement(String aName)
  {
    name = aName;
    modifier = null;
    namespaces = new ArrayList<String>();
    packageName = "";
    extraCode = "";
    isInternal = false;
    coordinates = new Coordinate(-1,-1,-1,-1);
    displayColor = "";
    positions = new ArrayList<Position>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setModifier(String aModifier)
  {
    boolean wasSet = false;
    modifier = aModifier;
    wasSet = true;
    return wasSet;
  }

  public boolean addNamespace(String aNamespace)
  {
    boolean wasAdded = false;
    wasAdded = namespaces.add(aNamespace);
    return wasAdded;
  }

  public boolean removeNamespace(String aNamespace)
  {
    boolean wasRemoved = false;
    wasRemoved = namespaces.remove(aNamespace);
    return wasRemoved;
  }

  @umplesourcefile(line={101},file={"Umple.ump"},javaline={94},length={1})
  public boolean setPackageName(String aPackageName)
  {
    boolean wasSet = false;
    // line 101 "../../../../src/Umple.ump"
    if (aPackageName == null) { return false; }
    packageName = aPackageName;
    wasSet = true;
    return wasSet;
  }

  public boolean setExtraCode(String aExtraCode)
  {
    boolean wasSet = false;
    extraCode = aExtraCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsInternal(boolean aIsInternal)
  {
    boolean wasSet = false;
    isInternal = aIsInternal;
    wasSet = true;
    return wasSet;
  }

  public boolean setCoordinates(Coordinate aCoordinates)
  {
    boolean wasSet = false;
    coordinates = aCoordinates;
    wasSet = true;
    return wasSet;
  }

  public boolean setDisplayColor(String aDisplayColor)
  {
    boolean wasSet = false;
    displayColor = aDisplayColor;
    wasSet = true;
    return wasSet;
  }

  /**
   * The name of the Umple element.
   */
  public String getName()
  {
    return name;
  }

  /**
   * The modifier associated with the Umple element.
   */
  public String getModifier()
  {
    return modifier;
  }

  public String getNamespace(int index)
  {
    String aNamespace = namespaces.get(index);
    return aNamespace;
  }

  public String[] getNamespaces()
  {
    String[] newNamespaces = namespaces.toArray(new String[namespaces.size()]);
    return newNamespaces;
  }

  public int numberOfNamespaces()
  {
    int number = namespaces.size();
    return number;
  }

  public boolean hasNamespaces()
  {
    boolean has = namespaces.size() > 0;
    return has;
  }

  public int indexOfNamespace(String aNamespace)
  {
    int index = namespaces.indexOf(aNamespace);
    return index;
  }

  public String getPackageName()
  {
    return packageName;
  }

  /**
   * The code associated with the Umple element.
   */
  public String getExtraCode()
  {
    return extraCode;
  }

  /**
   * Specifies whether or not the Umple element is internal.
   */
  public boolean getIsInternal()
  {
    return isInternal;
  }

  /**
   * Specifies the position of this Umple element (ex. The UmpleOnline diagram).
   */
  public Coordinate getCoordinates()
  {
    return coordinates;
  }

  /**
   * Specifies the display color of this element
   */
  public String getDisplayColor()
  {
    return displayColor;
  }

  public boolean isIsInternal()
  {
    return isInternal;
  }

  public Position getPosition(int index)
  {
    Position aPosition = positions.get(index);
    return aPosition;
  }

  /**
   * The position(s) of the element in the source code, used in debugging
   * may have multiple positions in the case of mixins
   */
  public List<Position> getPositions()
  {
    List<Position> newPositions = Collections.unmodifiableList(positions);
    return newPositions;
  }

  public int numberOfPositions()
  {
    int number = positions.size();
    return number;
  }

  public boolean hasPositions()
  {
    boolean has = positions.size() > 0;
    return has;
  }

  public int indexOfPosition(Position aPosition)
  {
    int index = positions.indexOf(aPosition);
    return index;
  }

  public static int minimumNumberOfPositions()
  {
    return 0;
  }

  public boolean addPosition(Position aPosition)
  {
    boolean wasAdded = false;
    if (positions.contains(aPosition)) { return false; }
    positions.add(aPosition);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePosition(Position aPosition)
  {
    boolean wasRemoved = false;
    if (positions.contains(aPosition))
    {
      positions.remove(aPosition);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addPositionAt(Position aPosition, int index)
  {  
    boolean wasAdded = false;
    if(addPosition(aPosition))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPositions()) { index = numberOfPositions() - 1; }
      positions.remove(aPosition);
      positions.add(index, aPosition);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePositionAt(Position aPosition, int index)
  {
    boolean wasAdded = false;
    if(positions.contains(aPosition))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPositions()) { index = numberOfPositions() - 1; }
      positions.remove(aPosition);
      positions.add(index, aPosition);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPositionAt(aPosition, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    positions.clear();
  }


  public String toString()
  {
	  String outputString = "";
	  
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "modifier" + ":" + getModifier()+ "," +
            "packageName" + ":" + getPackageName()+ "," +
            "extraCode" + ":" + getExtraCode()+ "," +
            "isInternal" + ":" + getIsInternal()+ "," +
            "displayColor" + ":" + getDisplayColor()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "coordinates" + "=" + (getCoordinates() != null ? !getCoordinates().equals(this)  ? getCoordinates().toString().replaceAll("  ","    ") : "this" : "null")
     + outputString;
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  // line 624 ../../../../src/Umple_Code.ump
  @umplesourcefile(line={624},file={"Umple_Code.ump"},javaline={336},length={4})
  public void appendExtraCode(String newCode)
  {
    appendExtraCode(newCode,true);
  }

  @umplesourcefile(line={629},file={"Umple_Code.ump"},javaline={342},length={12})
  public void appendExtraCode(String newCode, boolean addNewline)
  {
    if (newCode == null) 
    { 
      return;
    }
    if (!"".equals(extraCode) && addNewline)
    {
      extraCode += System.getProperty("line.separator");
    } 
    extraCode += newCode;
  }

}