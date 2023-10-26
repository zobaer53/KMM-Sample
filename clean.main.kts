#!/usr/bin/env kotlin

import java.io.File

cmd("rm -rf build")
cmd("rm -rf .gradle")
cmd("rm -rf .idea")

cmd("rm -rf build", "./gradlePlugin/blueprint-configuration")
cmd("rm -rf .gradle", "./gradlePlugin/blueprint-configuration")

cmd("rm -rf build", "./gradlePlugin/blueprint-dependency")
cmd("rm -rf .gradle", "./gradlePlugin/blueprint-dependency")

cmd("rm -rf build", "./gradlePlugin/blueprint-tools")
cmd("rm -rf .gradle", "./gradlePlugin/blueprint-tools")


fun cmd(command: String, path: String = "./"): Boolean {
    val commands = command.split(" ")
    val arguments = commands.subList(1, commands.size)
    val result = ProcessBuilder()
        .directory(File(path))
        .command(commands[0], *arguments.toTypedArray())
        .redirectError(ProcessBuilder.Redirect.INHERIT)
        .redirectOutput(ProcessBuilder.Redirect.INHERIT)
        .start()
        .waitFor()
    return result == 0
}
