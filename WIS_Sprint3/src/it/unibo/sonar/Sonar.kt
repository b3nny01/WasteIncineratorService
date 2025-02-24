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
//Sept2024
import org.slf4j.Logger
import org.slf4j.LoggerFactory 
import org.json.simple.parser.JSONParser
import org.json.simple.JSONObject


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
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="init_mqtt", cond=doswitch() )
				}	 
				state("init_mqtt") { //this:State
					action { //it:State
						connectToMqttBroker( "${configurator.getProperty("mqtt_broker_uri")}" )
						subscribe(  "system_state" ) //mqtt.subscribe(this,topic)
						subscribe(  "update_storage" ) //mqtt.subscribe(this,topic)
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
					 transition(edgeName="t021",targetState="handle_unload_ash",cond=whenEvent("system_state"))
					transition(edgeName="t022",targetState="handle_update_storage",cond=whenEvent("update_storage"))
				}	 
				state("handle_unload_ash") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("system_state(RP,ACTIVE,BURNING,ASH_LEVEL,OP_ROBOT_STATE,OP_ROBOT_POS,LED_STATE)"), Term.createTerm("system_state(RP,ACTIVE,BURNING,ASH_LEVEL,OP_ROBOT_STATE,OP_ROBOT_POS,LED_STATE)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								
												val NEW_O=payloadArg(4);
												val TO_UPDATE=(NEW_O!=O && O=="ash_unloaded");
												O=NEW_O
								if(  TO_UPDATE  
								 ){  
											 		L+=L_STEP;
											 		val ASH_LEVEL=L/MAX_L 
								CommUtils.outmagenta("$name: ash level: $ASH_LEVEL")
								//val m = MsgUtil.buildEvent(name, "actor_state", "actor_state(ash_storage_level,$ASH_LEVEL)" ) 
								publish(MsgUtil.buildEvent(name,"actor_state","actor_state(ash_storage_level,$ASH_LEVEL)").toString(), "actor_state" )   
								}
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="idle", cond=doswitch() )
				}	 
				state("handle_update_storage") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("update_storage(STORAGE)"), Term.createTerm("update_storage(STORAGE)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								
												val STORAGE=payloadArg(0);
								if(  STORAGE.equals("ash")  
								 ){
													L=0.0
													val ASH_LEVEL=0.0 
								CommUtils.outmagenta("$name: mock ash update")
								//val m = MsgUtil.buildEvent(name, "actor_state", "actor_state(ash_storage_level,$ASH_LEVEL)" ) 
								publish(MsgUtil.buildEvent(name,"actor_state","actor_state(ash_storage_level,$ASH_LEVEL)").toString(), "actor_state" )   
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
