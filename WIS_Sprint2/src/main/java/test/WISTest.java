package main.java.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.CommUtils;
import unibo.basicomm23.utils.ConnectionFactory;

public class WISTest{
	private static Interaction connSupport;

	@BeforeClass
	public static void activateSystem() {
		CommUtils.outmagenta("activate");
		it.unibo.ctx_wis.MainCtx_wisKt.main();
		CommUtils.delay(1000);
		connSupport = ConnectionFactory.createClientSupport(ProtocolType.tcp, "localhost", "8022");

	}

	@After
	public void down() {
		CommUtils.outmagenta("end of test =======================================================");
	}

	@Test
	public void testIncinineratorActivation() {
		CommUtils.outmagenta("testIncinineratorActivation =======================================");
		IApplMessage req = CommUtils.buildRequest("tester", "system_state_req", "system_state_req", "wis");
		try {
			IApplMessage reply = connSupport.request(req);
			CommUtils.outblue("reply=" + reply);
			String answer = reply.msgContent();

			boolean burning = answer.split(",")[1].strip().equalsIgnoreCase("true");
			assertTrue(burning);

		} catch (Exception e) {
			fail("testIncinineratorActivation " + e.getMessage());
		}
	}
	
	@Test
	public void testOk5RP() {
		CommUtils.outmagenta("testOk5RP ========================================================= ");
		IApplMessage req = CommUtils.buildRequest("test", "test_req", "test_req", "test_observer");
		try {
			IApplMessage reply = connSupport.request(req);
			CommUtils.outblue("reply=" + reply);
			String payload = reply.msgContent();
			String[] payloadArgs= payload.split("\\(")[1].split("\\)")[0].split(",");
			int rp = Integer.parseInt(payloadArgs[0]);
			double l=Double.parseDouble(payloadArgs[3]);
			
			assertEquals(rp, 1);
			assertEquals(l, 1.0,0.001);
			
		} catch (Exception e) {
			fail("testOk5RP " + e.getMessage());
		}
	}
}