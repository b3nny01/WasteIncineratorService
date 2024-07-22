%====================================================================================
% wis_system description   
%====================================================================================
dispatch( actor_state, actor_state(P,V) ).
request( conditions_verified_req, conditions_verified_req(N) ).
reply( conditions_verified_repl, conditions_verified_repl(R) ).  %%for conditions_verified_req
request( rp_req, rp_req(N) ).
reply( rp_repl, rp_repl(N) ).  %%for rp_req
request( burn_req, burn_req(N) ).
reply( burn_repl, burn_repl(N) ).  %%for burn_req
request( ash_req, ash_req(N) ).
reply( ash_repl, ash_repl(N) ).  %%for ash_req
%====================================================================================
context(ctx_wis, "localhost",  "TCP", "8020").
 qactor( incinerator, ctx_wis, "it.unibo.incinerator.Incinerator").
 static(incinerator).
  qactor( waste_storage, ctx_wis, "it.unibo.waste_storage.Waste_storage").
 static(waste_storage).
  qactor( ash_storage, ctx_wis, "it.unibo.ash_storage.Ash_storage").
 static(ash_storage).
  qactor( op_robot, ctx_wis, "it.unibo.op_robot.Op_robot").
 static(op_robot).
  qactor( waste_incinerator_service, ctx_wis, "it.unibo.waste_incinerator_service.Waste_incinerator_service").
 static(waste_incinerator_service).
