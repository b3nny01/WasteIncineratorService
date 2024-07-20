%====================================================================================
% wis_system description   
%====================================================================================
dispatch( actor_state, actor_state(P,V) ).
request( conditions_verified_req, conditions_verified_req(N) ).
reply( conditions_verified_repl, conditions_verified_repl(R) ).  %%for conditions_verified_req
request( rp_request, rp_request(N) ).
reply( rp_reply, rp_reply(N) ).  %%for rp_request
event( end_of_burning, end_of_burning(N) ).
request( cmd_move, cmd_move(D) ).
request( cmd_add_rp, cmd_add_rp(N) ).
request( cmd_remove_rp, cmd_remove_rp(N) ).
request( cmd_add_ash, cmd_add_ash(N) ).
request( cmd_remove_ash, cmd_remove_ash(N) ).
request( cmd_burn, cmd_burn(N) ).
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
