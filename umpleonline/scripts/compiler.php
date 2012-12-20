<?php
// Copyright: All contributers to the Umple Project
// This file is made available subject to the open source license found at:
// http://umple.org/license
//
// Invoked on the UmpleOnline server to cause one of the
// two jars to be run with appropriate arguments (umple.jar or umplesync.jar)
//
// Involed as an Ajax call by umple_action.js in the following places:
// Action.ajax - near the end of that file - for most compilation tasks
// Action.loadFile using ajax.js - argument load  - load a user saved file
// Action.loadExample - argument exampleCode - one of the pre-canned examples
// Action.saveNewFile using ajax.js - argument save 
// Action.pngImage, Action.ymlImageCallback
// Action.uiguCallback - argument asUI
//
// This file also formats the output from the jars for presentation back on
// the UmpleOnline screen
//
// Most of the helper methods are in compiler_config.php, except
// getErrorHtml, which reads the generated error file, and jsonDecode
//

require_once("compiler_config.php");

if (isset($_REQUEST["save"]))
{
  $input = $_REQUEST["umpleCode"];
  $filename = saveFile($input);
  echo $filename;
}
else if (isset($_REQUEST["load"]))
{
  $filename = $_REQUEST["filename"];
  $outputUmple = readTemporaryFile($filename);
  echo $outputUmple;
}
else if (isset($_REQUEST["action"]))
{
  handleUmpleTextChange();
}
else if (isset($_REQUEST["umpleCode"]))
{
  $input = $_REQUEST["umpleCode"];
  $language = $_REQUEST["language"];
  $outputErr = isset($_REQUEST["error"])?$_REQUEST["error"]:false;
  $uigu = False;
  
  if ($language == "javadoc")
  {
     $language = "Java";
     $javadoc = True;
  }
  else if ($language == "uigu")
  {
     $language = "Uigu";
     $uigu = True;
  }
  else
  {
    $javadoc = False;
    $uigu = False;
  }
  
  if ($language == "Simulate")
  {
    $filename = saveFile("generate Php \"./\" --override-all;\n" . $input);
    executeCommand("java -jar umple.jar {$filename}");
    $filename = saveFile("generate Simulate \"./\" --override-all;\n" . $input);
    executeCommand("java -jar umple.jar {$filename}");
    // Restore file so it doesn't have the 'generate' command in front
    saveFile($input);
    $modelId = getFilenameId($filename);
    echo $modelId;
    return;
  }
  elseif ($uigu)
  {
  $filename = saveFile($input);
  showUserInterface($filename);
/*	chdir("JSFProvider");
	if (file_exists("JSFProvider/model.ump"))
	  unlink("JSFProvider/model.ump");
    copy("$filename", "JSFProvider/model.ump");
	executeCommand("java -cp GUIModel.jar;JSFProvider.jar;GUIGenerator.jar cruise.generator.UIGU UmpleProject.xml model.ump TempDir TempApp");*/
  }
  elseif (!in_array($language,array("Php","Java","Ruby","Cpp","Sql")))
  {  
    $filename = saveFile($input);
    $errorFilename = "{$filename}.erroroutput";
    
    if ($language == "Experimental-Cpp" || $language == "Experimental-Sql") {
      $sourceCode = executeCommand("echo \"{$language} is under development. Output is currently only available to developers of Umple\" 2> {$errorFilename}");
    }
    else {
      $sourceCode = executeCommand("java -jar umplesync.jar -generate {$language} {$filename} 2> {$errorFilename}");
    }
    $errors = getErrorHtml($errorFilename, 0);
    
    if ($language != "Json")
    {
      $sourceCode = htmlspecialchars($sourceCode);
    }
    
    if($outputErr == "true")
    	echo $errors ."<p>URL_SPLIT";
    	
    echo $sourceCode;
    return;
  }

  if (!$uigu)
  {
  // Generate the Java, PHP, Ruby, Cpp or Sql and put it into the right directory
  $filename = saveFile("generate {$language} \"./{$language}/\" --override-all;\n" . $input);
  
  $outputFilename = "{$filename}.output";
  $errorFilename = "{$filename}.erroroutput";
  
  // Clean up any pre-existing java. php, ruby or cpp files
  $thedir = dirname($outputFilename);
  $toRemove = False;
  $rmcommand = "rm -rf ";
  foreach (glob("$thedir/{$language}") as $afile) {
    $rmcommand = $rmcommand . " " . $afile;
    $toRemove = True;
  }    
  if($toRemove) { exec($rmcommand); }
  
  $command = "java -jar umplesync.jar -source {$filename} 1> {$outputFilename} 2> {$errorFilename}";    
  exec($command);
  
  // Restore file so it doesn't have the 'generate' command in front
  saveFile($input);
  
  $sourceCode = readTemporaryFile($outputFilename);
  
  $sourceCode = str_replace("<?php","",$sourceCode);
  $sourceCode = str_replace("?>","",$sourceCode);
  $sourceCode = htmlspecialchars($sourceCode);
  
  $errhtml = getErrorHtml($errorFilename);
  
  if ($sourceCode == "")
  {
    if($input == "//$?[End_of_model]$?") {
      $html = "
        Please create an Umple model textually (on the left side of the screen)
        or visually (on the right side of the screen),
        and then choose Generate Code again.";
    }
    else
    {
      $html = "
        An error occurred interpreting your Umple code, please review it and try again.
        If the problem persists, please email the Umple code to
        the umple-help google group: umple-help@googlegroups.com";
    }
    echo $errhtml ."<p>URL_SPLIT" . $html;
    
  }
  else
  {
    if ($javadoc)
    {
       $thedir = dirname($outputFilename);
       $theurldir = "ump/" . basename($thedir);
       exec("rm -rf " . $thedir . "/javadoc");
       
        $command = "/usr/bin/javadoc -d " . $thedir . "/javadoc -footer \"Generated by Umple\" " ;
              
      foreach (glob("$thedir/{$language}/*/*/*/*.java") as $afile) {
          $command = $command . " " . $afile;
       }
       
       foreach (glob("$thedir/{$language}/*/*/*.java") as $afile) {
          $command = $command . " " . $afile;
       }    
       
       foreach (glob("$thedir/{$language}/*/*.java") as $afile) {
          $command = $command . " " . $afile;
       }    
       
       foreach (glob("$thedir/{$language}/*.java") as $afile) {
          $command = $command . " " . $afile;
       }                  
       
       exec($command);
       exec("cd $thedir; rm javadocFromUmple.zip; /usr/bin/zip -r javadocFromUmple javadoc");
       
       $html = "<a href=\"umpleonline/$thedir/javadocFromUmple.zip\">Download the following as a zip file</a>&nbsp;{$errhtml}
      <iframe width=100% height=1000 src=\"" . $theurldir . "/javadoc/\">This browser does not
      support iframes, so the javadoc cannot be displayed</iframe> 
     ";
       echo $html;
    }
    else // This is where the Java, PHP and other output is placed on the screen
    {
	   exec("cd $thedir; rm {$language}FromUmple.zip; /usr/bin/zip -r {$language}FromUmple {$language}");
	   echo "<a href=\"umpleonline/$thedir/{$language}FromUmple.zip\">Download the following as a zip file</a>&nbsp;{$errhtml}<p>URL_SPLIT";
       echo $sourceCode;
    }
  }
  } // UIGU
  else
  {
	$thedir = dirname($filename);
	$theurldir = "ump/" . basename($thedir);
	$errorFilename = "{$filename}.erroroutput";
	$errhtml = getErrorHtml($errorFilename);
	$rnd=substr(basename($thedir), 3);
	$uiguDir=file_get_contents("uigudir.txt");
	$html = "<a href=\"$theurldir/umpleUIGU.war\">Download the generated application as web archive (WAR) file.</a>&nbsp;{$errhtml}
	  <iframe width=100% height=300 src='$theurldir/'></iframe>
	 ";
	 echo $html;
  }
}
else if (isset($_REQUEST["exampleCode"]))
{
  $filename = "../ump/" . $_REQUEST["exampleCode"];
  $outputUmple = readTemporaryFile($filename);
  echo $outputUmple;
}
else if (isset($_REQUEST["asImage"]))
{
  $asImage = $_REQUEST["asImage"];
  $json = json_decode($asImage);
  showUmlImage($json);
}
else if (isset($_REQUEST["asYuml"]))
{
  showYumlImage($_REQUEST["asYuml"]);
}
else if (isset($_REQUEST["asUI"]))
{ 
  showUserInterface($_REQUEST["asUI"]);
}
else
{
  echo "Invalid use of compiler";
}


function getErrorHtml($errorFilename, $offset = 1) 
{
	
  $errorMessage = readTemporaryFile($errorFilename);

  if($errorMessage != "") 
  {
     $errInfo = jsonDecode($errorMessage);
     $errhtml = "<a href='#' id='errorClick'>Show/Hide errors and warnings</a>";
     $errhtml .= "<div id='errorRow' colspan='3' >";  // style='display:none'
     
     if($errInfo == null)
     {
     	$errhtml .= "Couldn't read results from the Umple compiler!";
     }
     else
     {
     	$results = $errInfo["results"];
     	
     	
     	foreach($results as $result)
     	{
     		$url = $result["url"];
     		$line = intval($result["line"]) - $offset; 
     		$errorCode = $result["errorCode"];
     		$severityInt = intval($result["severity"]);
     		if($severityInt > 2) {
     		  $severity = "Warning";
     		  $textcolor = "<font color=\"black\">";
     		}
     		else
     		{
     		  $severity = "Error";
     		  $textcolor = "<font color=\"red\">";
     		}
     		$msg = htmlspecialchars($result["message"]);
     		     		
     		$errhtml .= $textcolor." {$severity} on <a href=\"javascript:Action.setCaretPosition({$line});Action.updateLineNumberDisplay();\">line {$line}</a> : {$msg}.</font> <i><a href=\"{$url}\" target=\"helppage\">More information ({$errorCode})</a></i></br>";
     	}
     }
 	
     $errhtml .= "</div>";
        
     $errhtml .= "<script type=\"text/javascript\">jQuery(\"#errorClick\").click(function(a){a.preventDefault();jQuery(\"#errorRow\").toggle();});</script>";
     return $errhtml;
  }
  return "";
}

// taken from http://php.net/manual/en/function.json-decode.php
function jsonDecode ($json) 
{ 
      $json = str_replace(array("\\\\", "\\\""), array("&#92;", "&#34;"), $json); 
      $parts = preg_split("@(\"[^\"]*\")|([\[\]\{\},:])|\s@is", $json, -1, PREG_SPLIT_NO_EMPTY | PREG_SPLIT_DELIM_CAPTURE); 
      foreach ($parts as $index => $part) 
      { 
          if (strlen($part) == 1) 
          { 
              switch ($part) 
              { 
                  case "[": 
                  case "{": 
                      $parts[$index] = "array("; 
                      break; 
                  case "]": 
                  case "}": 
                      $parts[$index] = ")"; 
                      break; 
                  case ":": 
                    $parts[$index] = "=>"; 
                    break;    
                  case ",": 
                    break; 
                  default: 
                      return null; 
              } 
          } 
          else 
          { 
              if ((substr($part, 0, 1) != "\"") || (substr($part, -1, 1) != "\"")) 
              { 
                  return null; 
              } 
          } 
      } 
      $json = str_replace(array("&#92;", "&#34;", "$"), array("\\\\", "\\\"", "\\$"), implode("", $parts)); 
      return eval("return $json;"); 
} 
?>
