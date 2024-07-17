%====================================================================================
% wis_system description   
%====================================================================================
request( waste_storage_state_request, waste_storage_state_request(N) ). %
request( ash_storage_state_request, ash_storage_state_request(N) ). %
request( incinerator_state_request, incinerator_state_request(N) ). %
request( rp_request, rp_request(N) ). %
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
