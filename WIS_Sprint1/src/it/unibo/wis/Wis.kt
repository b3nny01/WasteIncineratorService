/* Generated by AN DISI Unibo */ 
package it.unibo.wis

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

class Wis ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		
				var POSITION="home"
				var OK=false;
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outgreen("$name starts")
						delay(500) 
						request("engage", "engage(wis,300)" ,"engager" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="activate_incinerator", cond=doswitch() )
				}	 
				state("activate_incinerator") { //this:State
					action { //it:State
						
									val AR=true	
						CommUtils.outyellow("$name: requesting incinerator activation")
						forward("incinerator_activation", "incinerator_activation($AR)" ,"incinerator" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="check_start_conditions", cond=doswitch() )
				}	 
				state("check_start_conditions") { //this:State
					action { //it:State
						delay(500) 
						CommUtils.outgreen("$name: checking conditions")
						request("conditions_verified_req", "conditions_verified_req" ,"wis_state_observer" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t04",targetState="handle_start_conditions_verified_repl",cond=whenReply("conditions_verified_repl"))
				}	 
				state("handle_start_conditions_verified_repl") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("conditions_verified_repl(R)"), Term.createTerm("conditions_verified_repl(R)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								
												OK=payloadArg(0).toBoolean()
						}
						CommUtils.outgreen("$name: conditions verified: $OK")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="move_to_waste_storage", cond=doswitchGuarded({ OK  
					}) )
					transition( edgeName="goto",targetState="check_start_conditions", cond=doswitchGuarded({! ( OK  
					) }) )
				}	 
				state("move_to_waste_storage") { //this:State
					action { //it:State
						CommUtils.outgreen("$name: moving to waste storage...")
						request("moverobot", "moverobot(1,3)" ,"robotpos" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="load_rp", cond=doswitch() )
				}	 
				state("load_rp") { //this:State
					action { //it:State
						CommUtils.outgreen("$name: loading an rp")
						emit("load_rp", "load_rp" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="move_to_incinerator", cond=doswitch() )
				}	 
				state("move_to_incinerator") { //this:State
					action { //it:State
						CommUtils.outgreen("$name: moving to incinerator...")
						delay(2000) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="request_to_burn", cond=doswitch() )
				}	 
				state("request_to_burn") { //this:State
					action { //it:State
						CommUtils.outgreen("$name: requesting to burn an rp")
						request("burn_req", "burn_req" ,"incinerator" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t05",targetState="handle_burn_repl",cond=whenReply("burn_repl"))
				}	 
				state("handle_burn_repl") { //this:State
					action { //it:State
						
									OK=payloadArg(0).toBoolean()	
						CommUtils.outgreen("$name: burn request result: $OK")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="move_to_ash_storage", cond=doswitch() )
				}	 
				state("move_to_ash_storage") { //this:State
					action { //it:State
						CommUtils.outgreen("$name: moving to ash storage...")
						delay(2000) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="unload_ash", cond=doswitch() )
				}	 
				state("unload_ash") { //this:State
					action { //it:State
						CommUtils.outgreen("$name: unloading ash...")
						emit("unload_ash", "unload_ash" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="check_continue_conditions", cond=doswitch() )
				}	 
				state("check_continue_conditions") { //this:State
					action { //it:State
						CommUtils.outgreen("$name: checking conditions")
						request("conditions_verified_req", "conditions_verified_req" ,"wis_state_observer" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t06",targetState="handle_continue_conditions_verified_repl",cond=whenReply("conditions_verified_repl"))
				}	 
				state("handle_continue_conditions_verified_repl") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("conditions_verified_repl(R)"), Term.createTerm("conditions_verified_repl(R)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								
												OK=payloadArg(0).toBoolean()
						}
						CommUtils.outgreen("$name: conditions verified: $OK")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="move_to_waste_storage", cond=doswitchGuarded({OK 
					}) )
					transition( edgeName="goto",targetState="move_to_home", cond=doswitchGuarded({! (OK 
					) }) )
				}	 
				state("move_to_home") { //this:State
					action { //it:State
						CommUtils.outgreen("moving to home...")
						delay(2000) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="check_start_conditions", cond=doswitch() )
				}	 
			}
		}
} 