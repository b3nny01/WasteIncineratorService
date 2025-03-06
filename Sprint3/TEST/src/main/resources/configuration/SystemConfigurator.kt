package main.resources.configuration

import java.util.Properties
import java.io.FileReader

object SystemConfigurator{
	private var propertiesFile="./wis_tester.properties"
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