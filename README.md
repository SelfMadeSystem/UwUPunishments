# UwUPunishments
UwUPunishments for MatrixMC

Created with eclipse and converted to Maven. Using IntelliJ now.

Thanks to UntouchedOdin0 (https://www.spigotmc.org/members/devbukkit.28513/) for making the RADME.md 

A new, simple, and tiny punishment plugin for admins.

I coded this plugin with the 1.12.2 spigot api. I tested it with 1.8.8 and 1.12.2 spigot, so I don't know if it'll work on other versions. It's up to you if you want to find out.

This plugin is in use on my server.

This plugin is not a ban manager, it is simply an easy way to punish people.

__Current features:__
- Punish any player with one command.
- Forgive any player with one command.
- Highly customizable punishments.
- Customizable messages.
- SQL support.
- Super tiny plugin and shouldn't affect tps.

__Planned features:__ 
(in order that I think I will do them, from very likely, to not very likely)
- Better config.
- Make an API
- Make an API

__Why choose this one over other ones?__
Right now, not much reason aside from it's Async (won't affect TPS at all) and it's really tiny, and I plan to keep it that way. It does work, but it doesn't have all the features I would like it to have yet, but they will be implemented soon because I know the features I want and how to implement them (except SQL, I hate that bs)

__How to install:__
Just drag and drop this plugin into the /plugins directory in your server and reload/restart the server or load it with plugman. It's recommended to restart the server. 

__Permissions and commands:__

Punishes someone
/punish <player> <broadcast, true/yes false/no> <reason>
Permission: uwu.forgive.use
Aliases: pu

Forgives someone
/forgive <player> <reason>
Permission: uwu.forgive.use
Aliases: fo

Sets someone's violation level.
/setviolations <player> <reason> <do-action> <amount>
Permission: uwu.setvl.use
Aliases: svl, setvl

Checks someone's violation level
/checkviolations <player> [reason]
Permission: uwu.forgive.use
Aliases: cv, checkvl

Reloads the configuration (sometimes doesn't work, better to reload it via a plugin manager)
/weload
Permission: uwu.pu.reload.use

Prevents anyone from changing your punishment level.
Permission: uwu.pu.exempt

Other stuff:

My discord: https://discord.gg/46efA3P

Please do not dislike this plugin just because it's trash, please give a good reason as to why (spigot tries to tell you to do this, but most people don't do that).

If you find any issues, please open a ticket at https://github.com/True-cc/UwUPunishments/issues
