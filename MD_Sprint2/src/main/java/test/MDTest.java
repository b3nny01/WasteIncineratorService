package main.java.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.CommUtils;
import unibo.basicomm23.utils.ConnectionFactory;

public class MDTest{
	private static Interaction connSupport;

	@BeforeClass
	public static void activateSystem() {
		CommUtils.outmagenta("activate");
		it.unibo.ctx_monitoring_device.MainCtx_monitoring_deviceKt.main();
		CommUtils.delay(1000);
		connSupport = ConnectionFactory.createClientSupport(ProtocolType.tcp, "localhost", "8012");

	}

	@After
	public void down() {
		CommUtils.outmagenta("end of test =======================================================");
	}

	@Test
	public void testLedBurning() {
		CommUtils.outmagenta("testLedBurning ====================================================");
		IApplMessage req = CommUtils.buildRequest("md_test", "test_led_burning", "test_req", "md_test_observer");
		try {
			IApplMessage reply = connSupport.request(req);
			CommUtils.outblue("reply=" + reply);
			String payload = reply.msgContent();
			String[] payloadArgs= payload.split("\\(")[1].split("\\)")[0].split(",");
			boolean ok = Boolean.parseBoolean(payloadArgs[0]);

			assertTrue(ok);
			

		} catch (Exception e) {
			fail("testLedBurning " + e.getMessage());
		}
	}
	
	@Test
	public void testLedEmptyWasteStorage() {
		CommUtils.outmagenta("testLedEmptyWasteStorage ==========================================");
		IApplMessage req = CommUtils.buildRequest("md_test", "test_led_empty_ws", "test_req", "md_test_observer");
		try {
			IApplMessage reply = connSupport.request(req);
			CommUtils.outblue("reply=" + reply);
			String payload = reply.msgContent();
			String[] payloadArgs= payload.split("\\(")[1].split("\\)")[0].split(",");
			boolean ok = Boolean.parseBoolean(payloadArgs[0]);

			assertTrue(ok);
			

		} catch (Exception e) {
			fail("testLedEmptyWasteStorage " + e.getMessage());
		}
	}
	
	@Test
	public void testLedFullAshStorage() {
		CommUtils.outmagenta("testLedFullAshStorage =============================================");
		IApplMessage req = CommUtils.buildRequest("md_test", "test_led_full_as", "test_req", "md_test_observer");
		try {
			IApplMessage reply = connSupport.request(req);
			CommUtils.outblue("reply=" + reply);
			String payload = reply.msgContent();
			String[] payloadArgs= payload.split("\\(")[1].split("\\)")[0].split(",");
			boolean ok = Boolean.parseBoolean(payloadArgs[0]);

			assertTrue(ok);
			

		} catch (Exception e) {
			fail("testLedFullAshStorage " + e.getMessage());
		}
	}
}