%====================================================================================
% wis_system description   
%====================================================================================
dispatch( actor_state, actor_state(P,V) ).
dispatch( incinerator_activation, incinerator_activation(A) ).
request( conditions_verified_req, conditions_verified_req(N) ).
reply( conditions_verified_repl, conditions_verified_repl(R) ).  %%for conditions_verified_req
event( load_rp, load_rp(N) ).
event( unload_ash, unload_rp(N) ).
request( burn_req, burn_req(N) ).
reply( burn_repl, burn_repl(N) ).  %%for burn_req
request( engage, engage(OWNER,STEPTIME) ).
reply( engagedone, engagedone(ARG) ).  %%for engage
reply( engagerefused, engagerefused(ARG) ).  %%for engage
dispatch( disengage, disengage(ARG) ).
request( moverobot, moverobot(TARGETX,TARGETY) ).
reply( moverobotdone, moverobotok(ARG) ).  %%for moverobot
reply( moverobotfailed, moverobotfailed(PLANDONE,PLANTODO) ).  %%for moverobot
%====================================================================================
context(ctx_wis, "localhost",  "TCP", "8022").
context(ctx_basic_robot, "127.0.0.1",  "TCP", "8020").
 qactor( robotpos, ctx_basic_robot, "external").
  qactor( engager, ctx_basic_robot, "external").
  qactor( incinerator, ctx_wis, "it.unibo.incinerator.Incinerator").
 static(incinerator).
  qactor( scale, ctx_wis, "it.unibo.scale.Scale").
 static(scale).
  qactor( monitoring_device, ctx_wis, "it.unibo.monitoring_device.Monitoring_device").
 static(monitoring_device).
  qactor( wis, ctx_wis, "it.unibo.wis.Wis").
 static(wis).
  qactor( wis_state_observer, ctx_wis, "it.unibo.wis_state_observer.Wis_state_observer").
 static(wis_state_observer).
