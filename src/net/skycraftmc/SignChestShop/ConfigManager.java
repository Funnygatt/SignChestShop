package net.skycraftmc.SignChestShop;

import java.io.IOException;
import java.util.logging.Level;

public class ConfigManager
{
	SignChestShopPlugin plugin;
	public ConfigManager(SignChestShopPlugin plugin)
	{
		this.plugin = plugin;
	}
	StringConfig config;
	public boolean writeConfig()
	{
		try
		{
			config.start();
			config.insertComment("SignChestShop config generated by version " + 
					plugin.getDescription().getVersion());
			config.writeLine();
			config.insertComment("==== Shop Options ====#");
			config.writeLine();
			config.insertComment("Shop option variables:");
			config.insertComment(" <owner> - the owner of the shop");
			config.insertComment(" <title> - the title of the shop");
			config.insertComment(" <curname> - the currency name, singular or plural, depending on the actual price");
			config.insertComment(" <curnameplur> - the plural form of the currency name");
			config.insertComment(" <curnamesing> - the singular form of the currency name");
			config.writeLine();
			config.insertComment("Text used to display the price of an item (use <price> for the price)");
			config.writeKey("shop.price.text", Options.DEFAULT_PRICE_TEXT);
			config.insertComment("<price> when the item(s) is/are free");
			config.writeKey("shop.price.free", Options.DEFAULT_PRICE_FREE);
			config.insertComment("<price> when the item(s) is/are on display");
			config.writeKey("shop.price.display", Options.DEFAULT_PRICE_DISPLAY);
			config.insertComment("<price> when there is only one item, but it is worth some amount (use <rawprice> for actual price)");
			config.writeKey("shop.price.cost", Options.DEFAULT_PRICE_COST);
			config.insertComment("<price> when there is more than one item, but they are worth some amount");
			config.insertComment(" Use <rawprice> for the cost of one item and <totalprice> for the total cost");
			config.writeKey("shop.price.costmulti", Options.DEFAULT_PRICE_COSTMULTI);
			config.writeLine();
			config.insertComment("Enable this to force signs to be empty on creation; ignores shop variables");
			config.writeKey("shop.forceempty", Options.DEFAULT_SHOP_FORCEEMPTY);
			config.insertComment("Minimum decimal places to display for prices");
			config.writeKey("shop.mindecplaces", Options.DEFAULT_SHOP_MINDECPLACES);
			config.writeLine();
			config.insertComment("==== Buying Options ====#");
			config.writeLine();
			config.insertComment("Buying modes:");
			config.insertComment(" single  - Items are bought as single items");
			config.insertComment(" stack  - Items are bought as stacks");
			config.insertComment(" amount  - Items are bought with the same amount as the displayed item");
			config.writeKey("buy.mode", Options.DEFAULT_BUY_MODE);
			config.insertComment("Enable this to make shift clicks buy a stack");
			config.write("buy.shiftclick", "" + config.getBoolean("buy.shiftclick", Options.DEFAULT_BUY_SHIFTCLICK));
			config.insertComment("Enable this to require players to have \"scs.buy\" in order to open a buy shop");
			config.writeKey("buy.perms", "" + Options.DEFAULT_BUY_PERMS);
			config.insertComment("Enable this to require players to have \"scs.buy.<id>\" in order to let them buy items with the id");
			config.writeKey("buy.permsid", "" + Options.DEFAULT_BUY_PERMSID);
			config.writeLine();
			config.insertComment("==== Selling Options ====#");
			config.writeLine();
			config.insertComment("Enable this to require players to have \"scs.sell\" in order to open a sell shop");
			config.writeKey("sell.perms", "" + Options.DEFAULT_SELL_PERMS);
			config.insertComment("Enable this to require players to have \"scs.sell.<id>\" in order to let them sell items with the id");
			config.writeKey("sell.permsid", "" + Options.DEFAULT_SELL_PERMSID);
			config.writeLine();
			config.insertComment("==== Messages ====#");
			config.writeLine();
			config.insertComment("Global message variables:");
			config.insertComment(" <player>  - The player doing an action");
			config.writeLine();
			config.insertComment("---- Command Messages ----#");
			config.writeLine();
			config.insertComment("Message for creating a shop");
			config.write("message.create.success", config.getString("message.create.success", Messages.DEFAULT_CREATE_SUCCESS));
			config.insertComment("Message for cancelling shop creation");
			config.write("message.create.cancel", config.getString("message.create.cancel", Messages.DEFAULT_CREATE_CANCEL));
			config.writeLine();
			config.insertComment("Message for cancelling the pricing of an item");
			config.write("message.price.cancel", config.getString("message.price.cancel", Messages.DEFAULT_PRICE_CANCEL));
			config.insertComment("Message for setting the price of an item");
			config.write("message.price.set", config.getString("message.price.set", Messages.DEFAULT_PRICE_SET));
			config.writeLine();
			config.insertComment("Message for editing a shop");
			config.write("message.edit", config.getString("message.edit", Messages.DEFAULT_EDIT));
			config.writeLine();
			config.insertComment("Message for an attempted breaking of a shop with the perm \"scs.create\"");
			config.write("message.break.perm", config.getString("message.break.perm", Messages.DEFAULT_BREAK_PERM));
			config.insertComment("Message for an attempted breaking of a shop without the perm \"scs.create\"");
			config.write("message.break.noperm", config.getString("message.beak.noperm", Messages.DEFAULT_BREAK_NOPERM));
			config.writeLine();
			config.insertComment("Message for setting the title of a shop.  <title> is the title of the shop.");
			config.writeKey("message.settitle.success", Messages.DEFAULT_SETTITLE_SUCCESS);
			config.insertComment("Message for removing the title of a shop");
			config.writeKey("message.settitle.remove", Messages.DEFAULT_SETTITLE_REMOVE);
			config.insertComment("Message for exceeding the 32 characters (Minecraft limitation; anything longer will cause errors) in a shop title");
			config.writeKey("message.settitle.fail", Messages.DEFAULT_SETTITLE_FAIL);
			config.writeLine();
			config.insertComment("Message for not targeting a SignChestShop");
			config.write("message.cmd.notarget", config.getString("message.cmd.notarget", Messages.DEFAULT_CMD_NOTARGET));
			config.insertComment("Message for not having the permissions");
			config.write("message.cmd.noperm", config.getString("message.cmd.noperm", Messages.DEFAULT_CMD_NOPERM));
			config.insertComment("Message for attempting to use owner commands on a shop not owned by the player");
			config.write("message.cmd.notowned", config.getString("message.cmd.notowned", Messages.DEFAULT_CMD_NOTOWNED));
			config.writeLine();
			config.insertComment("---- Transaction Messages ----#");
			config.writeLine();
			config.insertComment("Transaction message variables:");
			config.insertComment(" <amount>   - Amount of items bought");
			config.insertComment(" <price>   - Price of items");
			config.insertComment(" <rawprice>  - Price of items without the currency name");
			config.insertComment(" <itemcorrectl>  - \"item\" with a \"s\" if plural");
			config.insertComment(" <itemcorrectu>  - \"Item\" with a \"s\" if plural");
			config.insertComment("Shop option variables also apply here.");
			config.writeLine();
			config.insertComment("Message for buying an item from an unnamed and unowned shop successfully");
			config.writeKey("message.buy.success", Messages.DEFAULT_BUY_SUCCESS);
			config.insertComment("Message for buying an item from an unnamed and owned shop successfully");
			config.writeKey("message.buy.success.owner", Messages.DEFAULT_BUY_SUCCESS_OWNED);
			config.insertComment("Message for buying an item from a named and unowned shop successfully");
			config.writeKey("message.buy.success.titled", Messages.DEFAULT_BUY_SUCCESS_TITLED);
			config.insertComment("Message for buying an item from a named and owned shop successfully");
			config.writeKey("message.buy.success.titledowner", Messages.DEFAULT_BUY_SUCCESS_TITLED_OWNED);
			config.insertComment("Message for not having enough money while buying an item");
			config.write("message.buy.fail", config.getString("message.buy.fail", Messages.DEFAULT_BUY_FAIL));
			config.insertComment("Message for buying an item for free");
			config.write("message.buy.free", config.getString("message.buy.free", Messages.DEFAULT_BUY_FREE));
			config.insertComment("Message for doing an invalid action while shopping, ignores buy " +
					"variables");
			config.write("message.buy.invalid", config.getString("message.buy.invalid", Messages.DEFAULT_BUY_INVALID));
			config.writeLine();
			config.insertComment("Message for selling an item to an unnamed and unowned shop successfully");
			config.writeKey("message.sell.success", Messages.DEFAULT_SELL_SUCCESS);
			config.insertComment("Message for selling an item to an unnamed and owned shop successfully");
			config.writeKey("message.sell.success.owned", Messages.DEFAULT_SELL_SUCCESS_OWNED);
			config.insertComment("Message for selling an item to an named and unowned shop successfully");
			config.writeKey("message.sell.success.titled", Messages.DEFAULT_SELL_SUCCESS_TITLED);
			config.insertComment("Message for selling an item to an named and owned shop successfully");
			config.writeKey("message.sell.success.titledowned", Messages.DEFAULT_SELL_SUCCESS_TITLED_OWNED);
			config.insertComment("Message for the owner of the shop not having enough money to buy a sold item");
			config.writeKey("message.sell.fail", Messages.DEFAULT_SELL_FAIL);
			config.insertComment("Message for a limited shop's storage not having enough space to buy an item from a player");
			config.writeKey("message.sell.nospace", Messages.DEFAULT_SELL_NOSPACE);
			config.insertComment("Message for doing an invalid action while shopping, ignores sell " +
					"variables");
			config.write("message.sell.invalid", config.getString("message.sell.invalid", Messages.DEFAULT_SELL_INVALID));
			config.writeLine();
			config.insertComment("==== Logging Options ====#");
			config.writeLine();
			config.insertComment("Enable this to log shop creation to the console");
			config.write("log.create", "" + config.getBoolean("log.create", Options.DEFAULT_LOG_SHOP_CREATION));
			config.writeLine();
			config.insertComment("==== Update Checker Options ====#");
			config.writeLine();
			config.insertComment("Enable this to automatically check for updates on enable");
			config.write("updater.check", "" + config.getBoolean("updater.check", Options.DEFAULT_UPDATER_CHECK));
			config.close();
		}
		catch(IOException ioe)
		{
			plugin.getLogger().log(Level.SEVERE, "Could not create config", ioe);
			return false;
		}
		return true;
	}
	
	public void loadConfig()
	{
		try 
		{
			config.load();
		} 
		catch (IOException ioe) 
		{
			plugin.getLogger().log(Level.SEVERE, "Could not load config, reverting to defaults", ioe);
		}
	}
}
