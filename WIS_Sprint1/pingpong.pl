%====================================================================================
% pingpong description   
%====================================================================================
dispatch( ball, ball(N) ). %info exchanged by the players
dispatch( ballview, ball(N) ). %observed info | payload not mandatory
request( info, info(X) ). %sent by the testUnit to the observer
reply( obsinfo, obsinfo(X) ).  %%for info
dispatch( endgame, endgame(X) ). %sent as an automsg by the observer
%====================================================================================
context(ctxping, "localhost",  "TCP", "8014").
context(ctxpong, "pong",  "TCP", "8015").
 qactor( pong, ctxpong, "external").
  qactor( ping, ctxping, "it.unibo.ping.Ping").
 static(ping).
