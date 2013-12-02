<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3036 modeling language!*/

class NestedStatesWthSameEventNames
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //NestedStatesWthSameEventNames State Machines
  private static $SmNestedState1 = 1;
  private static $SmNestedState2 = 2;
  private static $SmState5 = 3;
  private static $SmNestedState1Null = 4;
  private static $SmNestedState1State1 = 5;
  private static $SmNestedState1State2 = 6;
  private static $SmNestedState2Null = 7;
  private static $SmNestedState2State3 = 8;
  private static $SmNestedState2State4 = 9;
  private $sm;
  private $smNestedState1;
  private $smNestedState2;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct()
  {
    $this->setSmNestedState1(self::$SmNestedState1Null);
    $this->setSmNestedState2(self::$SmNestedState2Null);
    $this->setSm(self::$SmNestedState1);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getSmFullName()
  {
    $answer = $this->getSm();
    if ($this->smNestedState1 != self::$SmNestedState1Null) { $answer .= "." . $this->getSmNestedState1(); }
    if ($this->smNestedState2 != self::$SmNestedState2Null) { $answer .= "." . $this->getSmNestedState2(); }
    return $answer;
  }

  public function getSm()
  {
    if ($this->sm == self::$SmNestedState1) { return "SmNestedState1"; }
    elseif ($this->sm == self::$SmNestedState2) { return "SmNestedState2"; }
    elseif ($this->sm == self::$SmState5) { return "SmState5"; }
    return null;
  }

  public function getSmNestedState1()
  {
    if ($this->smNestedState1 == self::$SmNestedState1Null) { return "SmNestedState1Null"; }
    elseif ($this->smNestedState1 == self::$SmNestedState1State1) { return "SmNestedState1State1"; }
    elseif ($this->smNestedState1 == self::$SmNestedState1State2) { return "SmNestedState1State2"; }
    return null;
  }

  public function getSmNestedState2()
  {
    if ($this->smNestedState2 == self::$SmNestedState2Null) { return "SmNestedState2Null"; }
    elseif ($this->smNestedState2 == self::$SmNestedState2State3) { return "SmNestedState2State3"; }
    elseif ($this->smNestedState2 == self::$SmNestedState2State4) { return "SmNestedState2State4"; }
    return null;
  }

  private function enterNestedState1()
  {
    $wasEventProcessed = false;
    
    $aSmNestedState1 = $this->smNestedState1;
    if ($aSmNestedState1 == self::$SmNestedState1Null)
    {
      $this->setSmNestedState1(self::$SmNestedState1State1);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  private function exitNestedState1()
  {
    $wasEventProcessed = false;
    
    $aSmNestedState1 = $this->smNestedState1;
    if ($aSmNestedState1 == self::$SmNestedState1State1)
    {
      $this->setSmNestedState1(self::$SmNestedState1Null);
      $wasEventProcessed = true;
    }
    elseif ($aSmNestedState1 == self::$SmNestedState1State2)
    {
      $this->setSmNestedState1(self::$SmNestedState1Null);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function moveTo()
  {
    $wasEventProcessed = false;
    
    $aSmNestedState1 = $this->smNestedState1;
    $aSmNestedState2 = $this->smNestedState2;
    if ($aSmNestedState1 == self::$SmNestedState1State1)
    {
      $this->setSmNestedState1(self::$SmNestedState1State2);
      $wasEventProcessed = true;
    }

    
    if ($aSmNestedState2 == self::$SmNestedState2State3)
    {
      $this->setSmNestedState2(self::$SmNestedState2State4);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function transit()
  {
    $wasEventProcessed = false;
    
    $aSmNestedState1 = $this->smNestedState1;
    $aSmNestedState2 = $this->smNestedState2;
    if ($aSmNestedState1 == self::$SmNestedState1State2)
    {
      $this->exitSm();
      $this->setSm(self::$SmState5);
      $wasEventProcessed = true;
    }

    
    if ($aSmNestedState2 == self::$SmNestedState2State4)
    {
      $this->exitSm();
      $this->setSm(self::$SmState5);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  private function enterNestedState2()
  {
    $wasEventProcessed = false;
    
    $aSmNestedState2 = $this->smNestedState2;
    if ($aSmNestedState2 == self::$SmNestedState2Null)
    {
      $this->setSmNestedState2(self::$SmNestedState2State3);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  private function exitNestedState2()
  {
    $wasEventProcessed = false;
    
    $aSmNestedState2 = $this->smNestedState2;
    if ($aSmNestedState2 == self::$SmNestedState2State3)
    {
      $this->setSmNestedState2(self::$SmNestedState2Null);
      $wasEventProcessed = true;
    }
    elseif ($aSmNestedState2 == self::$SmNestedState2State4)
    {
      $this->setSmNestedState2(self::$SmNestedState2Null);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  private function exitSm()
  {
    if ($this->sm == self::$SmNestedState1)
    {
      $this->exitNestedState1();
    }
    elseif ($this->sm == self::$SmNestedState2)
    {
      $this->exitNestedState2();
    }
  }

  private function setSm($aSm)
  {
    $this->sm = $aSm;

    // entry actions and do activities
    if ($this->sm == self::$SmNestedState1)
    {
      if ($this->smNestedState1 == self::$SmNestedState1Null) { $this->setSmNestedState1(self::$SmNestedState1State1); }
    }
    elseif ($this->sm == self::$SmNestedState2)
    {
      if ($this->smNestedState2 == self::$SmNestedState2Null) { $this->setSmNestedState2(self::$SmNestedState2State3); }
    }
  }

  private function setSmNestedState1($aSmNestedState1)
  {
    $this->smNestedState1 = $aSmNestedState1;
    if ($this->sm != self::$SmNestedState1 && $aSmNestedState1 != self::$SmNestedState1Null) { $this->setSm(self::$SmNestedState1); }
  }

  private function setSmNestedState2($aSmNestedState2)
  {
    $this->smNestedState2 = $aSmNestedState2;
    if ($this->sm != self::$SmNestedState2 && $aSmNestedState2 != self::$SmNestedState2Null) { $this->setSm(self::$SmNestedState2); }
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>