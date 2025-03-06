package main.resources.utils
data class Position(val x:Int,val y:Int){
	companion object{
		fun parse(str:String):Position{
			val xyStr=str.split("(")[1].split(")")[0].split(",")
			val x=xyStr[0].toInt()
			val y=xyStr[1].toInt()
			return Position(x,y)
		}
	}
	override fun toString():String{
		return "pos("+x.toString()+","+y.toString()+")"
	}
}