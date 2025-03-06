package main.resources.utils

enum class LedState(val code:String) {
	ON("on"),OFF("off"),BLINKING("blinking");
	
	override fun toString():String{
		return this.code;
	}
	companion object {
		fun parseStr(str:String):LedState{
			return LedState.values().first({ s -> s.toString()==str})
		}
	}
}