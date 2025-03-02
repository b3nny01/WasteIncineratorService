package main.resources.utils

enum class OpRobotState(val code:String) {
	INIT("init"),
	ENGAGING("engaging"),ENGAGED("engaged"),
	CHECKING("checking"),CHECKED("checked"),
	MOVING_WS("moving_ws"),MOVED_WS("moved_ws"),
	RP_LOADING("rp_loading"),RP_LOADED("rp_loaded"),
	MOVING_BI("moving_bi"),MOVED_BI("moved_bi"),
	RP_UNLOADING("rp_unloading"),RP_UNLOADED("rp_unloaded"),
	MOVING_H("moving_h"),MOVED_H("moved_h"),
	WAITING("waiting"),
	MOVING_BO("moving_bo"),MOVED_BO("moved_bo"),
	ASH_LOADING("ash_loading"),ASH_LOADED("ash_loaded"),
	MOVING_AS("moving_as"),MOVED_AS("moved_ad"),
	ASH_UNLOADING("ash_unloading"),ASH_UNLOADED("ash_unloaded");
	
	override fun toString():String{
		return this.code;
	}
}