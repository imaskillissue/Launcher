package me.skillissue.launcher.cmd

import me.skillissue.launcher.names

class Command(
		val name: String,
		val description: String,
		val usage: String,
)
{
	private val maps = mutableMapOf<Int, List<String>>()

	fun complete(parameterNumber: Int, text: String): Array<String>
	{
		if (maps[parameterNumber] == null)
			return arrayOf(text)
		val list = maps[parameterNumber]!!.filter { it.startsWith(text) }
		if (list.isEmpty())
			return arrayOf(text)
		return list.toTypedArray()
	}

	fun addCompletion(parameterNumber: Int, vararg completions: String)
	{
		if (maps[parameterNumber] == null)
			maps[parameterNumber] = completions.toList()
		else
			maps[parameterNumber]!! + completions.toList()
	}

	fun serverCompletion(parameterNumber: Int)
	{
		addCompletion(parameterNumber, *names.toTypedArray())
	}

	fun removeCompletion(parameterNumber: Int)
	{
		maps.remove(parameterNumber)
	}


}