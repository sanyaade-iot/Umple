package cruise.queued.statemachine.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Test;

public class MultipleQueuedStateMachinesTest
{ 
	
  @Test 
  public void numberOfMessagesInMessageType()
  {
	  // compare the number of messages in MessageType is equal to the number of events in State Machine except timed events and auto-transition
	  Assert.assertEquals(3, MultipleQueuedSMs.MessageType.values().length);
	  Assert.assertEquals(true, MultipleQueuedSMs.MessageType.valueOf("e1_M").equals(MultipleQueuedSMs.MessageType.e1_M));
	  Assert.assertEquals(true, MultipleQueuedSMs.MessageType.valueOf("e2_M").equals(MultipleQueuedSMs.MessageType.e2_M));
	  Assert.assertEquals(true, MultipleQueuedSMs.MessageType.valueOf("e4_M").equals(MultipleQueuedSMs.MessageType.e4_M));
  }
  
  @Test 
  public void processEvents() throws InterruptedException
  {
	  MultipleQueuedSMs qsm = new MultipleQueuedSMs();
	  int numChecks;
	    
	  // check initial states are s1 - s3
	  Assert.assertEquals(MultipleQueuedSMs.Sm.s1, qsm.getSm());
	  Assert.assertEquals(MultipleQueuedSMs.Sm1.s3, qsm.getSm1());
	  
	  //event e1 is called
	  qsm.e1();//event e1 is added to the queue
	  // event e1 is taken off the queue and is processed
	  // state machine sm: transition to: s2 
	  // state machine sm1: transition to: s3
	  numChecks=200; // we will check for a second
	  while(!qsm.getSm().equals(MultipleQueuedSMs.Sm.s2) && numChecks>0) {
		Thread.sleep(5);
	    numChecks--;
	  }
	  assertThat(numChecks, not(equalTo(0)));
	  Assert.assertEquals(MultipleQueuedSMs.Sm.s2, qsm.getSm());
	  Assert.assertEquals(MultipleQueuedSMs.Sm1.s3, qsm.getSm1());
	  // queue is empty
	  Assert.assertEquals(0, qsm.queue.messages.size());
	  
	  //event e2 is called
	  qsm.e2();//event e2 is added to the queue
	  // event e2 is taken off the queue and is processed
	  // state machine sm: transition to: s1 
	  // state machine sm1: transition to: s4
	  numChecks=200; // we will check for a second
	  while((!qsm.getSm().equals(MultipleQueuedSMs.Sm.s1) && !qsm.getSm1().equals(MultipleQueuedSMs.Sm1.s4)) && numChecks>0) {
		Thread.sleep(5);
	    numChecks--;
	  }
	  assertThat(numChecks, not(equalTo(0)));
	  Assert.assertEquals(MultipleQueuedSMs.Sm.s1, qsm.getSm());
	  Assert.assertEquals(MultipleQueuedSMs.Sm1.s4, qsm.getSm1());
	  // queue is empty
	  Assert.assertEquals(0, qsm.queue.messages.size());
	  
	  //event e2 is called
	  qsm.e2();//event e2 is added to the queue
	  //event e2 is unspecified, it is ignored, current states are not changed
	  numChecks=200; // we will check for a second
	  while(numChecks>0 && (qsm.getSm().equals(MultipleQueuedSMs.Sm.s1) && qsm.getSm1().equals(MultipleQueuedSMs.Sm1.s4))) {
		if(!qsm.queue.messages.isEmpty()){
		  Thread.sleep(5);
	      numChecks--;
		}
		else
		{
		  Assert.assertEquals(MultipleQueuedSMs.Sm.s1, qsm.getSm());
		  Assert.assertEquals(MultipleQueuedSMs.Sm1.s4, qsm.getSm1());
		  Assert.assertEquals(true, qsm.queue.messages.isEmpty());
		  break;
	    }
	  }
	  assertThat(numChecks, not(equalTo(0)));
	  Assert.assertEquals(MultipleQueuedSMs.Sm.s1, qsm.getSm());
	  Assert.assertEquals(MultipleQueuedSMs.Sm1.s4, qsm.getSm1());
	  // queue is empty
	  Assert.assertEquals(0, qsm.queue.messages.size());
	  
	  //event e4 is called
	  qsm.e4();//event e4 is added to the queue
	  // event e4 is taken off the queue and is processed
	  // state machine sm: transition to: s1 
	  // state machine sm1: transition to: s3
	  numChecks=200; // we will check for a second
	  while(!qsm.getSm1().equals(MultipleQueuedSMs.Sm1.s3) && numChecks>0) {
		Thread.sleep(5);
	    numChecks--;
	  }
	  assertThat(numChecks, not(equalTo(0)));
	  Assert.assertEquals(MultipleQueuedSMs.Sm.s1, qsm.getSm());
	  Assert.assertEquals(MultipleQueuedSMs.Sm1.s3, qsm.getSm1());
	  // queue is empty
	  Assert.assertEquals(0, qsm.queue.messages.size());

	  //event e1 is called
	  qsm.e1();//event e1 is added to the queue
	  // event e1 is taken off the queue and is processed
	  // state machine sm: transition to: s2 
	  // state machine sm1: transition to: s3
	  numChecks=200; // we will check for a second
	  while(!qsm.getSm().equals(MultipleQueuedSMs.Sm.s2) && numChecks>0) {
		Thread.sleep(5);
	    numChecks--;
	  }
	  assertThat(numChecks, not(equalTo(0)));
	  Assert.assertEquals(MultipleQueuedSMs.Sm.s2, qsm.getSm());
	  Assert.assertEquals(MultipleQueuedSMs.Sm1.s3, qsm.getSm1());
	  // queue is empty
	  Assert.assertEquals(0, qsm.queue.messages.size());

	  //event e1 is called
	  qsm.e1();//event e1 is added to the queue
	  //event e1 is unspecified, it is ignored, current states are not changed
	  numChecks=200; // we will check for a second
	  while(numChecks>0 && (qsm.getSm().equals(MultipleQueuedSMs.Sm.s2) && qsm.getSm1().equals(MultipleQueuedSMs.Sm1.s3))) {
		if(!qsm.queue.messages.isEmpty()){
		  Thread.sleep(5);
	      numChecks--;
		}
		else
		{
		  Assert.assertEquals(MultipleQueuedSMs.Sm.s2, qsm.getSm());
		  Assert.assertEquals(MultipleQueuedSMs.Sm1.s3, qsm.getSm1());
		  Assert.assertEquals(true, qsm.queue.messages.isEmpty());
		  break;
	    }
	  }
	  assertThat(numChecks, not(equalTo(0)));
	  Assert.assertEquals(MultipleQueuedSMs.Sm.s2, qsm.getSm());
	  Assert.assertEquals(MultipleQueuedSMs.Sm1.s3, qsm.getSm1());
	  // queue is empty
	  Assert.assertEquals(0, qsm.queue.messages.size());
	  
	  //event e4 is called
	  qsm.e4();//event e4 is added to the queue
	  //event e4 is unspecified, it is ignored, current states are not changed
	  numChecks=200; // we will check for a second
	  while(numChecks>0 && (qsm.getSm().equals(MultipleQueuedSMs.Sm.s2) && qsm.getSm1().equals(MultipleQueuedSMs.Sm1.s3))) {
		if(!qsm.queue.messages.isEmpty()){
		  Thread.sleep(5);
	      numChecks--;
		}
		else
		{
		  Assert.assertEquals(MultipleQueuedSMs.Sm.s2, qsm.getSm());
		  Assert.assertEquals(MultipleQueuedSMs.Sm1.s3, qsm.getSm1());
		  Assert.assertEquals(true, qsm.queue.messages.isEmpty());
		  break;
	    }
	  }
	  assertThat(numChecks, not(equalTo(0)));
	  Assert.assertEquals(MultipleQueuedSMs.Sm.s2, qsm.getSm());
	  Assert.assertEquals(MultipleQueuedSMs.Sm1.s3, qsm.getSm1());
	  // queue is empty
	  Assert.assertEquals(0, qsm.queue.messages.size());

	  //event e4 is called
	  qsm.e4();//event e4 is added to the queue
	  //event e4 is unspecified, it is ignored, current states are not changed
	  numChecks=200; // we will check for a second
	  while(numChecks>0 && (qsm.getSm().equals(MultipleQueuedSMs.Sm.s2) && qsm.getSm1().equals(MultipleQueuedSMs.Sm1.s3))) {
		if(!qsm.queue.messages.isEmpty()){
		  Thread.sleep(5);
	      numChecks--;
		}
		else
		{
		  Assert.assertEquals(MultipleQueuedSMs.Sm.s2, qsm.getSm());
		  Assert.assertEquals(MultipleQueuedSMs.Sm1.s3, qsm.getSm1());
		  Assert.assertEquals(true, qsm.queue.messages.isEmpty());
		  break;
	    }
	  }
	  assertThat(numChecks, not(equalTo(0)));
	  Assert.assertEquals(MultipleQueuedSMs.Sm.s2, qsm.getSm());
	  Assert.assertEquals(MultipleQueuedSMs.Sm1.s3, qsm.getSm1());
	  // queue is empty
	  Assert.assertEquals(0, qsm.queue.messages.size());

      //event e2 is called
	  qsm.e2();//event e2 is added to the queue
	  // event e2 is taken off the queue and is processed
	  // state machine sm: transition to: s1 
	  // state machine sm1: transition to: s4
	  numChecks=200; // we will check for a second
	  while(!qsm.getSm1().equals(MultipleQueuedSMs.Sm1.s4) && numChecks>0) {
		Thread.sleep(5);
	    numChecks--;
	  }
	  assertThat(numChecks, not(equalTo(0)));
	  Assert.assertEquals(MultipleQueuedSMs.Sm.s1, qsm.getSm());
	  Assert.assertEquals(MultipleQueuedSMs.Sm1.s4, qsm.getSm1());
	  // queue is empty
	  Assert.assertEquals(0, qsm.queue.messages.size());	  
	  	  
	  
	  //check that there is no event left in the queue
	  Assert.assertEquals(0, qsm.queue.messages.size());	  
  }
}