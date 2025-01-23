%====================================================================================
% wis_system description   
%====================================================================================
dispatch( actor_state, actor_state(P,V) ).
dispatch( activationCommand, activationCommand(A) ).
event( endOfBurning, endOfBurning(E) ).
event( ashLevel, ashLevel(L) ).
%====================================================================================
context(ctx_wis, "localhost",  "TCP", "8020").
context(ctx_monitoringdev, "localhost",  "TCP", "8022").
 qactor( wis, ctx_wis, "it.unibo.wis.Wis").
 static(wis).
  qactor( incinerator, ctx_wis, "it.unibo.incinerator.Incinerator").
 static(incinerator).
  qactor( monitoring_device, ctx_monitoringdev, "it.unibo.monitoring_device.Monitoring_device").
 static(monitoring_device).
