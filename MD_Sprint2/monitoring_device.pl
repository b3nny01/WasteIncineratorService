%====================================================================================
% monitoring_device description   
%====================================================================================
dispatch( sonarstart, sonarstart(X) ).
dispatch( sonarstop, sonarstop(X) ).
event( sonardata, distance(D) ).
dispatch( actor_state, actor_state(P,V) ).
dispatch( system_state, system_state(RP,A,B,L) ).
dispatch( doread, doread(X) ).
dispatch( update_led_mode, update_led_mode(M) ).
%====================================================================================
context(ctx_monitoring_device, "localhost",  "TCP", "8012").
context(ctx_wis, "10.0.0.1",  "TCP", "8022").
 qactor( wis, ctx_wis, "external").
  qactor( sonar24, ctx_monitoring_device, "it.unibo.sonar24.Sonar24").
 static(sonar24).
  qactor( datacleaner, ctx_monitoring_device, "it.unibo.datacleaner.Datacleaner").
 static(datacleaner).
  qactor( sonar_device, ctx_monitoring_device, "it.unibo.sonar_device.Sonar_device").
 static(sonar_device).
  qactor( led, ctx_monitoring_device, "it.unibo.led.Led").
 static(led).
  qactor( led_device, ctx_monitoring_device, "it.unibo.led_device.Led_device").
 static(led_device).
