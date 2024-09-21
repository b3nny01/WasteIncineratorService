%====================================================================================
% monitoring_device description   
%====================================================================================
dispatch( sonarstart, sonarstart(X) ).
dispatch( sonarstop, sonarstop(X) ).
event( sonardata, distance(D) ).
event( obstacle, obstacle(D) ).
dispatch( actor_state, actor_state(P,V) ).
dispatch( doread, doread(X) ).
dispatch( ledOn, ledOn(X) ).
dispatch( ledOff, ledOff(X) ).
dispatch( ledBlink, ledBlink(X) ).
%====================================================================================
context(ctx_monitoring_device, "localhost",  "TCP", "8012").
context(ctx_wis, "192.168.1.67",  "TCP", "8022").
 qactor( wis, ctx_wis, "external").
  qactor( sonar24, ctx_monitoring_device, "it.unibo.sonar24.Sonar24").
 static(sonar24).
  qactor( distancefilter, ctx_monitoring_device, "it.unibo.distancefilter.Distancefilter").
 static(distancefilter).
  qactor( datacleaner, ctx_monitoring_device, "it.unibo.datacleaner.Datacleaner").
 static(datacleaner).
  qactor( sonardevice, ctx_monitoring_device, "it.unibo.sonardevice.Sonardevice").
 static(sonardevice).
  qactor( led, ctx_monitoring_device, "it.unibo.led.Led").
 static(led).
