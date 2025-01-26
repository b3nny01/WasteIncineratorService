%====================================================================================
% wis_system description   
%====================================================================================
dispatch( actor_state, actor_state(PROPERTY,VALUE) ).
dispatch( incinerator_activation, incinerator_activation(ACTIVE) ).
request( system_state_req, system_state_req(N) ).
reply( system_state_repl, system_state_repl(RP,ACTIVE,BURNING,ASH_LEVEL,OP_ROBOT_STATE,LED_STATE) ).  %%for system_state_req
dispatch( system_state, system_state(RP,ACTIVE,BURNING,ASH_LEVEL,OP_ROBOT_STATE,LED_STATE) ).
request( burn_req, burn_req(N) ).
reply( burn_repl, burn_repl(N) ).  %%for burn_req
request( engage, engage(OWNER,STEPTIME) ).
reply( engagedone, engagedone(ARG) ).  %%for engage
reply( engagerefused, engagerefused(ARG) ).  %%for engage
dispatch( disengage, disengage(ARG) ).
request( moverobot, moverobot(TARGETX,TARGETY) ).
reply( moverobotdone, moverobotok(ARG) ).  %%for moverobot
reply( moverobotfailed, moverobotfailed(PLANDONE,PLANTODO) ).  %%for moverobot
request( getrobotstate, getrobotstate(ARG) ).
reply( robotstate, robotstate(POS,DIR) ).  %%for getrobotstate
dispatch( ledState, ledState(X) ).
request( test_req, test_req(N) ).
reply( test_repl, test_repl(N) ).  %%for test_req
%====================================================================================
context(ctx_wis, "localhost",  "TCP", "8022").
context(ctx_basic_robot, "basicRobot",  "TCP", "8020").
 qactor( basicrobot, ctx_basic_robot, "external").
  qactor( incinerator, ctx_wis, "it.unibo.incinerator.Incinerator").
 static(incinerator).
  qactor( scale, ctx_wis, "it.unibo.scale.Scale").
 static(scale).
  qactor( op_robot, ctx_wis, "it.unibo.op_robot.Op_robot").
 static(op_robot).
  qactor( wis, ctx_wis, "it.unibo.wis.Wis").
 static(wis).
  qactor( test_observer, ctx_wis, "it.unibo.test_observer.Test_observer").
 static(test_observer).
  qactor( sonar, ctx_wis, "it.unibo.sonar.Sonar").
 static(sonar).
  qactor( led, ctx_wis, "it.unibo.led.Led").
 static(led).
