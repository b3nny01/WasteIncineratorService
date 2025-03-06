%====================================================================================
% wis_tester description   
%====================================================================================
event( system_state, system_state(RP,ACTIVE,BURNING,ASH_LEVEL,OP_ROBOT_STATE,OP_ROBOT_POS,LED_STATE) ).
dispatch( check, system_state(RP,ACTIVE,BURNING,ASH_LEVEL,OP_ROBOT_STATE,OP_ROBOT_POS,LED_STATE) ).
%====================================================================================
context(ctx_wis_tester, "localhost",  "TCP", "8022").
 qactor( wis_observer, ctx_wis_tester, "it.unibo.wis_observer.Wis_observer").
 static(wis_observer).
  qactor( wis_tester, ctx_wis_tester, "it.unibo.wis_tester.Wis_tester").
 static(wis_tester).
