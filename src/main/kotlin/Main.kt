import Discord.Message
import commands.*
import commands.interfaces.ICommand

fun main(args: Array<String>) {
    val config = require("../config.json")

    registerCommands()

    println("Hello JavaScript!")

    val client = Discord.Client()

    client.on("ready") {
        println("Logged in as ${client.user.tag}")

        client.user.setPresence(JSON.parse("""
            {
                "game": {
                    "name": "Kotlin bot"
                }
            }
        """.trimIndent()))

        /*val embed = RichEmbed()

        embed.setDescription("Hello from kotlin")

        val channel = client.guilds.get("416512197590777857").channels.get("513113435727331329") as TextChannel

        channel.send(
                content = "YEET",
                embed = embed
        ).then {
            println("Embed send")
        }*/

        println("Loaded ${commandsObj.size} commands")
    }

    client.on("message") { handleMessage(it) }

    client.login(config.token)
}

fun handleMessage(message: Message) {
    val content = message.content.toLowerCase()

    if (!content.startsWith(prefix) || message.author.bot) {
        return
    }

    val split = content.split("\\s+".toRegex())
    var command = split[0].substring(prefix.length)
    val args = split.drop(1)

    if (aliasesObj.containsKey(command)) {
        command = aliasesObj[command]!!
    }

    if (commandsObj.containsKey(command)) {
        commandsObj[command]!!.execute(command, args, message)
    }
}

fun registerCommands() {
    addCommand(CodeCommand())
    addCommand(ShutdownCommand())
    addCommand(HelpCommand())
    addCommand(PingCommand())
    addCommand(AboutCommand())
}

fun addCommand(command: ICommand) {
    commandsObj[command.getName()] = command

    command.getAliasses().forEach {
        aliasesObj[it] = command.getName()
    }
}