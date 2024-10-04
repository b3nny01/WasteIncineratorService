%====================================================================================
% monitoring_device description   
%====================================================================================
dispatch( sonarstart, sonarstart(X) ).
dispatch( sonarstop, sonarstop(X) ).
event( sonardata, distance(D) ).
dispatch( actor_state, actor_state(P,V) ).
dispatch( doread, doread(X) ).
dispatch( ledOn, ledOn(X) ).
dispatch( ledOff, ledOff(X) ).
dispatch( ledBlink, ledBlink(X) ).
event( unload_ash, unload_ash(N) ).
%====================================================================================
context(ctx_monitoring_device, "localhost",  "TCP", "8012").
context(ctx_wis, "10.0.0.1",  "TCP", "8022").
 qactor( wis, ctx_wis, "external").
  qactor( monitoring_device, ctx_monitoring_device, "it.unibo.monitoring_device.Monitoring_device").
 static(monitoring_device).
