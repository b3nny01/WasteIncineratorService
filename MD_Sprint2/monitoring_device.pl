%====================================================================================
% monitoring_device description   
%====================================================================================
dispatch( sonar_start, sonarstart(X) ).
dispatch( sonar_stop, sonarstop(X) ).
event( sonar_data, distance(D) ).
event( sonar_clean_data, clean_data(D,MIND,MAXD) ).
dispatch( actor_state, actor_state(P,V) ).
dispatch( system_state, system_state(RP,ACTIVE,BURNING,ASH_LEVEL,OP_ROBOT_STATE,LED_STATE) ).
dispatch( do_read, do_read(X) ).
dispatch( update_led_mode, update_led_mode(M) ).
request( test_led_burning, test_req(N) ).
request( test_led_empty_ws, test_req(N) ).
request( test_led_full_as, test_req(N) ).
dispatch( set_system_state, system_state(RP,ACTIVE,BURNING,ASH_LEVEL,OP_ROBOT_STATE,LED_STATE) ).
request( led_device_state_req, led_device_state_req(N) ).
reply( led_device_state_repl, led_device_state_repl(S) ).  %%for led_device_state_req
%====================================================================================
context(ctx_monitoring_device, "localhost",  "TCP", "8012").
 qactor( sonar, ctx_monitoring_device, "it.unibo.sonar.Sonar").
 static(sonar).
  qactor( data_cleaner, ctx_monitoring_device, "it.unibo.data_cleaner.Data_cleaner").
 static(data_cleaner).
  qactor( led, ctx_monitoring_device, "it.unibo.led.Led").
 static(led).
  qactor( md_test_observer, ctx_monitoring_device, "it.unibo.md_test_observer.Md_test_observer").
 static(md_test_observer).
  qactor( sonar_device, ctx_monitoring_device, "it.unibo.sonar_device.Sonar_device").
 static(sonar_device).
  qactor( led_device, ctx_monitoring_device, "it.unibo.led_device.Led_device").
 static(led_device).
  qactor( wis, ctx_monitoring_device, "it.unibo.wis.Wis").
 static(wis).
