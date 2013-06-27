/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.17.0.2716 modeling language!*/

package cruise.vml;
import cruise.umple.compiler.*;
import java.util.*;

/**
 * Specialist parser for parsing VML
 * @umplesource Vml.ump 95
 * @umplesource Vml_Code.ump 174
 * @umplesource Vml_Code.ump 401
 */
// line 95 "../../../src/Vml.ump"
// line 174 "../../../src/Vml_Code.ump"
// line 401 "../../../src/Vml_Code.ump"
public class VmlParser extends Parser
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //VmlParser Attributes
  private Map<VariationPoint,List<String>> variationPointToRequiresListMap;
  private List<VariationPoint> invokedVariations;

  //VmlParser Associations
  private VmlSystem system;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  @umplesourcefile(line={178},file={"Vml_Code.ump"},javaline={44},length={1})
  public VmlParser(String aName)
  {
    super(aName);
    variationPointToRequiresListMap = null;
    invokedVariations = new ArrayList<VariationPoint>();
    // line 178 "../../../src/Vml_Code.ump"
    init();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public VmlSystem getSystem()
  {
    return system;
  }

  public boolean setSystem(VmlSystem aNewSystem)
  {
    boolean wasSet = false;
    system = aNewSystem;
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    system = null;
    super.delete();
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  //  @umplesourcefile(line={179},file={"Vml_Code.ump"},javaline={74},length={186})
  @umplesourcefile(line={180},file={"Vml_Code.ump"},javaline={75},length={23})
  private void init()
  {
    addCouple(new Couple("\"","\""));
    addCouple(new Couple("{","}"));
    
    addRule("program- : ( [[comment]] | [[concern]] | [[invoke]] | [[codeSnippet]] )*");
    addRule("concern : Concern [name] { ( [[comment]] | [[variationPoint]] )* }");
    addRule("variationPoint : ( [[variantVariationPoint]] | [[regularVariationPoint]] )");
    addRule("regularVariationPoint- : VariationPoint [name] ( requires [[requiresList]]  )? { [[kind]]? [[codeSnippet]]?  }");
    addRule("variantVariationPoint- : VariationPoint [name] ( requires [[requiresList]]  )? { [[kind]]? [[variant]]*  }");
    addRule("requiresList : [name] ( , [name] )*");
    addRule("kind- : Kind : [=kind:Alternative|Optional] ;");
    addRule("variant : Variant [name] { [[codeSnippet]]? }");
    addRule("invoke- : [[invokeAlternative]] | [[invokeOptional]]");
    addRule("invokeOptional : invoke -( [concern] , [variationPoint] -) ;");
    addRule("invokeAlternative : invoke -( [concern] , [variationPoint] , [variant] -) ;");
    addRule("codeSnippet- : [**codeSnippet]");
    addRule("comment- : [[inlineComment]] | [[multilineComment]]");
    addRule("inlineComment- : // [*inlineComment]");
    addRule("multilineComment- : /* [**multilineComment] */");
    
    system = new VmlSystem();
  }  
  
  @umplesourcefile(line={204},file={"Vml_Code.ump"},javaline={100},length={19})
  public ParseResult analyze()
  {
    for(Token t : getRootToken().getSubTokens())
    {
      if (t.is("codeSnippet"))
      {
        system.addCodeSnippet(new CodeSnippet(t.getValue()));
      }
      else if (t.is("concern"))
      {
        handleConcern(t);
      }
      else if (t.is("invokeOptional") || t.is("invokeAlternative"))
      {
        handleInvoke(t);
      }
    }
    return getParseResult();
  }
  
  @umplesourcefile(line={224},file={"Vml_Code.ump"},javaline={121},length={26})
  private void handleConcern(Token concernToken)
  {
    variationPointToRequiresListMap = new HashMap<VariationPoint,List<String>>();

    Concern c = new Concern(concernToken.getValue("name"));
    for(Token t : concernToken.getSubTokens())
    {
      if (t.is("variationPoint"))
      {
        handleVariationPoint(t,c);
      }
    }
   
    for (VariationPoint vp : variationPointToRequiresListMap.keySet())
    {
      for (String vpName : variationPointToRequiresListMap.get(vp))
      {
        VariationPoint requires = c.getVariationPoint(vpName);
        if (requires != null)
        {
          vp.addRequire(requires);  
        }
      }
    }
    system.addConcern(c);
  }
  
  @umplesourcefile(line={251},file={"Vml_Code.ump"},javaline={149},length={59})
  private void handleInvoke(Token invokeToken)
  {
    String concernName = invokeToken.getValue("concern");
    String variationPointName = invokeToken.getValue("variationPoint");
    
    Concern c = system.getConcern(concernName);
    if (c == null)
    { 
      return;
    }
    
    VariationPoint vp = c.getVariationPoint(variationPointName);
    if (vp == null)
    {
      return;
    }
    
    invokedVariations.add(vp);
    
    for (VariationPoint require : vp.getRequires())
    {
      if (!invokedVariations.contains(require))
      {
        invokedVariations.add(require);
        
        CodeSnippet snippet = null;
        if (VariationPoint.Kind.Optional.equals(require.getKind()))
        {
          snippet = require.getCodeSnippet();
        }
        else if (VariationPoint.Kind.Alternative.equals(require.getKind()) && require.numberOfVariants() > 0)
        {
          snippet = require.getVariant(0).getCodeSnippet();
        }
        
        if (snippet != null)
        {
          system.addCodeSnippet(snippet);
        }
      }
    }    
    
    if (invokeToken.is("invokeAlternative"))
    {
      String variantName = invokeToken.getValue("variant");
      Variant var = vp.getVariant(variantName);
      
      if (var == null)
      {
        return;
      }
      
      system.addCodeSnippet(var.getCodeSnippet());
    }
    else
    {
      system.addCodeSnippet(vp.getCodeSnippet()); 
    }
  }
  
  @umplesourcefile(line={311},file={"Vml_Code.ump"},javaline={210},length={44})
  private void handleVariationPoint(Token variationToken, Concern c)
  {
    VariationPoint vp = new VariationPoint(variationToken.getValue("name"));
    
    Token requiresToken = variationToken.getSubToken("requiresList");
    ArrayList<String> allRequires = new ArrayList<String>();
    if (requiresToken != null)
    {
      for (Token t : requiresToken.getSubTokens())
      {
        if (t.is("name"))
        {
          allRequires.add(t.getValue());
        }
      }
    }
    
    if (variationToken.getValue("kind") != null)
    {
      VariationPoint.Kind kind = VariationPoint.Kind.valueOf(variationToken.getValue("kind")); 
      vp.setKind(kind);
    }
    
    if (vp.getKind() == VariationPoint.Kind.Optional)
    {
      
      if (variationToken.getValue("codeSnippet") != null)
      {
        vp.setCodeSnippet(new CodeSnippet(variationToken.getValue("codeSnippet")));
      }
    }
    else if (vp.getKind() == VariationPoint.Kind.Alternative)
    {
      for(Token t : variationToken.getSubTokens())
      {
        if (t.is("variant"))
        {
          handleVariant(vp,t);
        }
      }
    }
    c.addVariationPoint(vp);
    variationPointToRequiresListMap.put(vp,allRequires);
  }
  
  @umplesourcefile(line={356},file={"Vml_Code.ump"},javaline={256},length={9})
  private void handleVariant(VariationPoint vp, Token variantToken)
  {
    Variant v = new Variant(variantToken.getValue("name"));
    if (variantToken.getValue("codeSnippet") != null)
    {
      v.setCodeSnippet(new CodeSnippet(variantToken.getValue("codeSnippet")));
    }
    vp.addVariant(v);
  }

}