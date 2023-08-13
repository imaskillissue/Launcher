package me.skillissue.launcher

import java.lang.Process

class SubProcess(private val process: Process)
{
	fun alive(): Boolean
	{
		return process.isAlive
	}

	fun stop()
	{
		process.destroy()
	}
}