package main.resources.configuration

import java.util.Properties
import java.io.FileReader

object SystemConfiguration{
	private var propertiesFile="./monitoring_device.properties"
	private val properties= Properties()

	init{
		properties.load(FileReader(propertiesFile))
	}

	fun load(propertiesFile:String){
		this.propertiesFile=propertiesFile
		properties.load(FileReader(propertiesFile));
	}

	fun getProperty(key:String):String{
		return this.properties.get(key).toString();
	}
	

}