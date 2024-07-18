%====================================================================================
% wis_system description   
%====================================================================================
request( waste_storage_state_request, waste_storage_state_request(N) ). %
reply( waste_storage_state_reply, waste_storage_state_reply(N) ). %%for waste_storage_state_request | 
request( ash_storage_state_request, ash_storage_state_request(N) ). %
reply( ash_storage_state_reply, ash_storage_state_reply(N) ). %%for ash_storage_state_request | 
request( incinerator_state_request, incinerator_state_request(N) ). %
reply( incinerator_state_reply, incinerator_state_reply(B,BOF) ). %%for incinerator_state_request | 
request( rp_request, rp_request(N) ). %
reply( rp_reply, rp_reply(N) ). %%for rp_request | 
request( move_request, move_request(D) ). %
reply( move_reply, move_reply(R) ). %%for move_request | 
dispatch( load_rp, load_rp(N) ). %
dispatch( unload_rp, unload_rp(N) ). %
dispatch( load_ash, load_ash(N) ). %
dispatch( unload_ash, unload_ash(N) ). %
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
