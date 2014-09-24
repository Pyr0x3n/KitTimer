KitTimer
========

KitTimer add-on for Lib's Hungergames

What does this plugin?

- It records when a kit is in-game purchased by a player, either with the /buykit command or trough the gui.

- It removes all player’s kits older than a specified date and stored in Libshungergames kits’ database.


What it is used for?

- It is very useful if you have a shop (like buycraft or other) and sell kits packages for lets say 30 days. With this plugin, kits are removed from players after 30 days (or more… or less… see config.yml).


How does this plugin work?

- It creates an extra field in Libshungergames kits’ database to store kits purchase date The database is also automatically updated when a kit is purchased by a player inside the game.

- On start up it remove all owned kits from the database before today minus 30 days. (can be configured in config.yml) so it require game not greater than 24 hour. (usually a typical hungergame is less than 30 minutes).


Does this plugin require Libshungergames installed? Is there a soft dependency?

-There is no soft dependency; neither the code in Libshungergames is altered or referred.

-It is an add-on, so it is made for Libshungergames, but you could use it with other Hungergames plugin using a database for kit storage.

-The config.yml can be setup for other plugins compatibility.


Does it require a database?

- Yes I require mysSQL installed and configured for Libshungergames.
