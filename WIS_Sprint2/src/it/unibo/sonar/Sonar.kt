/* Generated by AN DISI Unibo */ 
package it.unibo.sonar

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

class Sonar ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		 val configurator = main.resources.configuration.SystemConfigurator
		 
				var O="init"
				var L=configurator.getProperty("mock_sonar.start_level").toDouble()
				var L_STEP=configurator.getProperty("mock_sonar.level_step").toDouble()
				val MAX_L=configurator.getProperty("mock_sonar.max_level").toDouble()
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outmagenta("$name starts")
						delay(500) 
						observeResource("localhost","8022","ctx_wis","wis","system_state")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="idle", cond=doswitch() )
				}	 
				state("idle") { //this:State
					action { //it:State
						CommUtils.outmagenta("$name: idle")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t024",targetState="handle_unload_ash",cond=whenDispatch("system_state"))
				}	 
				state("handle_unload_ash") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("system_state(RP,ACTIVE,BURNING,ASH_LEVEL,OP_ROBOT_STATE,LED_STATE)"), Term.createTerm("system_state(RP,ACTIVE,BURNING,ASH_LEVEL,OP_ROBOT_STATE,LED_STATE)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								
												val NEW_O=payloadArg(4);
												val TO_UPDATE=(NEW_O!=O && O=="ash_unloaded");
												O=NEW_O
								if(  TO_UPDATE  
								 ){  
											 		L+=2.5;
											 		val ASH_LEVEL=L/MAX_L 
								CommUtils.outmagenta("$name: ash level: $ASH_LEVEL")
								updateResourceRep( "actor_state(ash_storage_level,$ASH_LEVEL)"  
								)
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
