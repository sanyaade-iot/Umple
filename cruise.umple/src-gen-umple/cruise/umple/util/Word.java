/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.16.0.2388 modeling language!*/

package cruise.umple.util;

// line 841 "../../../../src/Util_Code.ump"
public class Word
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Word Attributes
  private String singular;
  private String plural;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Word(String aSingular, String aPlural)
  {
    singular = aSingular;
    plural = aPlural;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSingular(String aSingular)
  {
    boolean wasSet = false;
    singular = aSingular;
    wasSet = true;
    return wasSet;
  }

  public boolean setPlural(String aPlural)
  {
    boolean wasSet = false;
    plural = aPlural;
    wasSet = true;
    return wasSet;
  }

  public String getSingular()
  {
    return singular;
  }

  public String getPlural()
  {
    return plural;
  }

  public void delete()
  {}

}