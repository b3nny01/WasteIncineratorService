/* Generated by AN DISI Unibo */ 
package it.unibo.data_cleaner

import it.unibo.kactor.*
import alice.tuprolog.*
import unibo.basicomm23.*
import unibo.basicomm23.interfaces.*
import unibo.basicomm23.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import it.unibo.kactor.sysUtil.createActor   //Sept2023

//User imports JAN2024

class Data_cleaner ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		 
				var CURRENT=0
				var PREVIOUS = 0
				val SENSITIVITY=1
				val START_D=3;
				val MIN_D=0;
				val MAX_D=10;
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outcyan("$name starts")
						delay(500) 
						subscribeToLocalActor("sonar_device") 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="idle", cond=doswitch() )
				}	 
				state("idle") { //this:State
					action { //it:State
						CommUtils.outcyan("$name: idle")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t01",targetState="filter",cond=whenEvent("sonar_data"))
				}	 
				state("filter") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("distance(D)"), Term.createTerm("distance(D)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								  
									      		CURRENT = payloadArg(0).toInt()-START_D
										
									      		if(CURRENT<=MIN_D)	CURRENT=MIN_D
									      		if(CURRENT>=MAX_D)	CURRENT=MAX_D
								CommUtils.outcyan("$name: clean data: $CURRENT")
								if(  CURRENT >= (PREVIOUS + SENSITIVITY) || CURRENT <= (PREVIOUS - SENSITIVITY)  
								 ){emitLocalStreamEvent("sonar_clean_data", "clean_data($CURRENT,$MIN_D,$MAX_D)" ) 
								 PREVIOUS = CURRENT  
								}
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="idle", cond=doswitch() )
				}	 
			}
		}
} 