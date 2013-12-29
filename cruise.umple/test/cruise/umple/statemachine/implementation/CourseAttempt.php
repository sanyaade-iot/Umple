<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE ${last.version} modeling language!*/

class CourseAttempt
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CourseAttempt State Machines
  private static $CourseAttemptSMStudying = 1;
  private static $CourseAttemptSMFailed = 2;
  private static $CourseAttemptSMPassed = 3;
  private static $CourseAttemptSMLab1Null = 4;
  private static $CourseAttemptSMLab1Lab1 = 5;
  private static $CourseAttemptSMLab1Lab2 = 6;
  private static $CourseAttemptSMTermProjectNull = 7;
  private static $CourseAttemptSMTermProjectTermProject = 8;
  private static $CourseAttemptSMFinalExamNull = 9;
  private static $CourseAttemptSMFinalExamFinalExam = 10;
  private $courseAttemptSM;
  private $courseAttemptSMLab1;
  private $courseAttemptSMTermProject;
  private $courseAttemptSMFinalExam;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct()
  {
    $this->setCourseAttemptSMLab1(self::$CourseAttemptSMLab1Null);
    $this->setCourseAttemptSMTermProject(self::$CourseAttemptSMTermProjectNull);
    $this->setCourseAttemptSMFinalExam(self::$CourseAttemptSMFinalExamNull);
    $this->setCourseAttemptSM(self::$CourseAttemptSMStudying);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getCourseAttemptSMFullName()
  {
    $answer = $this->getCourseAttemptSM();
    if ($this->courseAttemptSMLab1 != self::$CourseAttemptSMLab1Null) { $answer .= "." . $this->getCourseAttemptSMLab1(); }
    if ($this->courseAttemptSMTermProject != self::$CourseAttemptSMTermProjectNull) { $answer .= "." . $this->getCourseAttemptSMTermProject(); }
    if ($this->courseAttemptSMFinalExam != self::$CourseAttemptSMFinalExamNull) { $answer .= "." . $this->getCourseAttemptSMFinalExam(); }
    return $answer;
  }

  public function getCourseAttemptSM()
  {
    if ($this->courseAttemptSM == self::$CourseAttemptSMStudying) { return "CourseAttemptSMStudying"; }
    elseif ($this->courseAttemptSM == self::$CourseAttemptSMFailed) { return "CourseAttemptSMFailed"; }
    elseif ($this->courseAttemptSM == self::$CourseAttemptSMPassed) { return "CourseAttemptSMPassed"; }
    return null;
  }

  public function getCourseAttemptSMLab1()
  {
    if ($this->courseAttemptSMLab1 == self::$CourseAttemptSMLab1Null) { return "CourseAttemptSMLab1Null"; }
    elseif ($this->courseAttemptSMLab1 == self::$CourseAttemptSMLab1Lab1) { return "CourseAttemptSMLab1Lab1"; }
    elseif ($this->courseAttemptSMLab1 == self::$CourseAttemptSMLab1Lab2) { return "CourseAttemptSMLab1Lab2"; }
    return null;
  }

  public function getCourseAttemptSMTermProject()
  {
    if ($this->courseAttemptSMTermProject == self::$CourseAttemptSMTermProjectNull) { return "CourseAttemptSMTermProjectNull"; }
    elseif ($this->courseAttemptSMTermProject == self::$CourseAttemptSMTermProjectTermProject) { return "CourseAttemptSMTermProjectTermProject"; }
    return null;
  }

  public function getCourseAttemptSMFinalExam()
  {
    if ($this->courseAttemptSMFinalExam == self::$CourseAttemptSMFinalExamNull) { return "CourseAttemptSMFinalExamNull"; }
    elseif ($this->courseAttemptSMFinalExam == self::$CourseAttemptSMFinalExamFinalExam) { return "CourseAttemptSMFinalExamFinalExam"; }
    return null;
  }

  private function __autotransition2__()
  {
    $wasEventProcessed = false;
    
    $aCourseAttemptSM = $this->courseAttemptSM;
    if ($aCourseAttemptSM == self::$CourseAttemptSMStudying)
    {
      $this->exitCourseAttemptSM();
      $this->setCourseAttemptSM(self::$CourseAttemptSMPassed);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  private function enterStudying()
  {
    $wasEventProcessed = false;
    
    $aCourseAttemptSMLab1 = $this->courseAttemptSMLab1;
    $aCourseAttemptSMTermProject = $this->courseAttemptSMTermProject;
    $aCourseAttemptSMFinalExam = $this->courseAttemptSMFinalExam;
    if ($aCourseAttemptSMLab1 == self::$CourseAttemptSMLab1Null)
    {
      $this->setCourseAttemptSMLab1(self::$CourseAttemptSMLab1Lab1);
      $wasEventProcessed = true;
    }

    
    if ($aCourseAttemptSMTermProject == self::$CourseAttemptSMTermProjectNull)
    {
      $this->setCourseAttemptSMTermProject(self::$CourseAttemptSMTermProjectTermProject);
      $wasEventProcessed = true;
    }

    
    if ($aCourseAttemptSMFinalExam == self::$CourseAttemptSMFinalExamNull)
    {
      $this->setCourseAttemptSMFinalExam(self::$CourseAttemptSMFinalExamFinalExam);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  private function exitStudying()
  {
    $wasEventProcessed = false;
    
    $aCourseAttemptSMLab1 = $this->courseAttemptSMLab1;
    $aCourseAttemptSMTermProject = $this->courseAttemptSMTermProject;
    $aCourseAttemptSMFinalExam = $this->courseAttemptSMFinalExam;
    if ($aCourseAttemptSMLab1 == self::$CourseAttemptSMLab1Lab1)
    {
      $this->setCourseAttemptSMLab1(self::$CourseAttemptSMLab1Null);
      $wasEventProcessed = true;
    }
    elseif ($aCourseAttemptSMLab1 == self::$CourseAttemptSMLab1Lab2)
    {
      $this->setCourseAttemptSMLab1(self::$CourseAttemptSMLab1Null);
      $wasEventProcessed = true;
    }

    
    if ($aCourseAttemptSMTermProject == self::$CourseAttemptSMTermProjectTermProject)
    {
      $this->setCourseAttemptSMTermProject(self::$CourseAttemptSMTermProjectNull);
      $wasEventProcessed = true;
    }

    
    if ($aCourseAttemptSMFinalExam == self::$CourseAttemptSMFinalExamFinalExam)
    {
      $this->setCourseAttemptSMFinalExam(self::$CourseAttemptSMFinalExamNull);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function lab1Done()
  {
    $wasEventProcessed = false;
    
    $aCourseAttemptSMLab1 = $this->courseAttemptSMLab1;
    if ($aCourseAttemptSMLab1 == self::$CourseAttemptSMLab1Lab1)
    {
      $this->setCourseAttemptSMLab1(self::$CourseAttemptSMLab1Lab2);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function fail()
  {
    $wasEventProcessed = false;
    
    $aCourseAttemptSMFinalExam = $this->courseAttemptSMFinalExam;
    if ($aCourseAttemptSMFinalExam == self::$CourseAttemptSMFinalExamFinalExam)
    {
      $this->exitCourseAttemptSMFinalExam();
      $this->setCourseAttemptSM(self::$CourseAttemptSMFailed);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  private function exitCourseAttemptSM()
  {
    if ($this->courseAttemptSM == self::$CourseAttemptSMStudying)
    {
      $this->exitStudying();
    }
  }

  private function setCourseAttemptSM($aCourseAttemptSM)
  {
    $this->courseAttemptSM = $aCourseAttemptSM;

    // entry actions and do activities
    if ($this->courseAttemptSM == self::$CourseAttemptSMStudying)
    {
      if ($this->courseAttemptSMLab1 == self::$CourseAttemptSMLab1Null) { $this->setCourseAttemptSMLab1(self::$CourseAttemptSMLab1Lab1); }
      if ($this->courseAttemptSMTermProject == self::$CourseAttemptSMTermProjectNull) { $this->setCourseAttemptSMTermProject(self::$CourseAttemptSMTermProjectTermProject); }
      if ($this->courseAttemptSMFinalExam == self::$CourseAttemptSMFinalExamNull) { $this->setCourseAttemptSMFinalExam(self::$CourseAttemptSMFinalExamFinalExam); }
      $this->__autotransition2__();
    }
  }

  private function exitCourseAttemptSMLab1()
  {
    if ($this->courseAttemptSMLab1 == self::$CourseAttemptSMLab1Lab2)
    {
      lab2Done();
    }
  }

  private function setCourseAttemptSMLab1($aCourseAttemptSMLab1)
  {
    $this->courseAttemptSMLab1 = $aCourseAttemptSMLab1;
    if ($this->courseAttemptSM != self::$CourseAttemptSMStudying && $aCourseAttemptSMLab1 != self::$CourseAttemptSMLab1Null) { $this->setCourseAttemptSM(self::$CourseAttemptSMStudying); }
  }

  private function exitCourseAttemptSMTermProject()
  {
    if ($this->courseAttemptSMTermProject == self::$CourseAttemptSMTermProjectTermProject)
    {
      projectDone();
    }
  }

  private function setCourseAttemptSMTermProject($aCourseAttemptSMTermProject)
  {
    $this->courseAttemptSMTermProject = $aCourseAttemptSMTermProject;
    if ($this->courseAttemptSM != self::$CourseAttemptSMStudying && $aCourseAttemptSMTermProject != self::$CourseAttemptSMTermProjectNull) { $this->setCourseAttemptSM(self::$CourseAttemptSMStudying); }
  }

  private function exitCourseAttemptSMFinalExam()
  {
    if ($this->courseAttemptSMFinalExam == self::$CourseAttemptSMFinalExamFinalExam)
    {
      pass();
    }
  }

  private function setCourseAttemptSMFinalExam($aCourseAttemptSMFinalExam)
  {
    $this->courseAttemptSMFinalExam = $aCourseAttemptSMFinalExam;
    if ($this->courseAttemptSM != self::$CourseAttemptSMStudying && $aCourseAttemptSMFinalExam != self::$CourseAttemptSMFinalExamNull) { $this->setCourseAttemptSM(self::$CourseAttemptSMStudying); }
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>