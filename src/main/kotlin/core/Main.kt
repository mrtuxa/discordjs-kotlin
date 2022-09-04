package core

import util.Discord
import util.process
import util.require

fun main(args: Array<String>) {
    val client = Discord.Client(intents = 65535)
    var dotenv = require("dotenv").config()
    println("Bot is in ${client.guilds.size}")

    client.user.setPresence(JSON.parse("""
        {
            "game": {
                "name": "discord.js using Kotlin"
            }
        }
    """.trimIndent()))

    client.login(process.env.TOKEN.unsafeCast<String>())
}

fun handleMessage(message: Discord.Message) {

}