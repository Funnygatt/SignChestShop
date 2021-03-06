package com.me.Funnygatt.signchestshop;

import java.io.IOException;
import java.util.logging.Level;
import java.util.regex.Matcher;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ConfigManager {

    SignChestShopPlugin plugin;
    StringConfig config;

    public static final String MSG_BUY_SUCCESS = "&e<itemcorrectu> bought for <price>!";
    public static final String MSG_BUY_SUCCESS_OWNED = "&e<itemcorrectu> bought from <owner> for <price>!";
    public static final String MSG_BUY_SUCCESS_TITLED = "&e<itemcorrectu> bought at <title> for <price>!";
    public static final String MSG_BUY_SUCCESS_TITLED_OWNED = "&e<itemcorrectu> bought at <title> from <owner> for <price>!";

    public static final String MSG_BUY_FAIL = "&eYou need at least <price> to buy this item!";
    public static final String MSG_BUY_FREE = "&e<itemcorrectu> bought for free!";
    public static final String MSG_BUY_INVALID = "&cYou can't do that!";
    public static final String MSG_BUY_NOPERM = "&cYou are not allowed to buy from shops.";
    public static final String MSG_BUY_NOPERMID = "&cYou are not allowed to buy this item!";

    public static final String MSG_BUY_NOTICE = "&e<player> bought <price> worth of items from one of your shops!";
    public static final String MSG_BUY_NOTICE_TITLED = "&e<player> bought <price> worth of items from your <title> shop!";

    public static final String MSG_SELL_SUCCESS = "&e<itemcorrectu> sold for <price>!";
    public static final String MSG_SELL_SUCCESS_OWNED = "&e<itemcorrectu> sold to <owner> <price>!";
    public static final String MSG_SELL_SUCCESS_TITLED = "&e<itemcorrectu> sold at <title> for <price>!";
    public static final String MSG_SELL_SUCCESS_TITLED_OWNED = "&e<itemcorrectu> sold at <title> to <owner> for <price>!";

    public static final String MSG_SELL_NOPERM = "&cYou are not allowed to sell to shops.";
    public static final String MSG_SELL_NOPERMID = "&cYou are not allowed to sell this item!";
    public static final String MSG_SELL_INVALID = "&cYou can't do that!";
    public static final String MSG_SELL_FAIL = "&cThe owner of this shop doesn't have enough money to buy this item!";
    public static final String MSG_SELL_NOSPACE = "&cThis shop doesn't have space for your item!";
    public static final String MSG_SELL_NOTICE = "&e<player> sold <price> worth of items to one of your shops!";
    public static final String MSG_SELL_NOTICE_TITLED = "&e<player> sold <price> worth of items to your <title> shop!";

    public static final String MSG_EDIT = "&eSignChestShop edited!";

    public static final String MSG_PRICE_CANCEL = "&eItem pricing cancelled.";
    public static final String MSG_PRICE_SET = "&ePrice set!";

    public static final String MSG_CREATE_CANCEL = "&eShop creation cancelled.";
    public static final String MSG_CREATE_SUCCESS = "&eShop created!";

    public static final String MSG_BREAK_NOPERM = "&cYou are not allowed to break shops!";
    public static final String MSG_BREAK_PERM = "&ePlease use &a/scs break &eto break this shop.";

    public static final String MSG_SETTITLE_SUCCESS = "&eShop title set to \"<title>\"";
    public static final String MSG_SETTITLE_REMOVE = "&eShop title removed!";
    public static final String MSG_SETTITLE_FAIL = "&cThe shop title can only have a maximum of 32 characters!";

    public static final String MSG_CMD_NOTARGET = "&cYou must target a SignChestShop!";
    public static final String MSG_CMD_NOPERM = "&cYou are not allowed to use this command!";
    public static final String MSG_CMD_NOTOWNED = "&cYou do not own this shop!";

    /* ****************** *
     * Configuration keys *
     * ****************** */
    public static final String CFG_BUY_MODE = "amount";

    public static final boolean CFG_BUY_SHIFTCLICK = true;
    public static final boolean CFG_BUY_PERMS = false;
    public static final boolean CFG_BUY_PERMSID = false;

    public static final String CFG_BUY_MODENAME = "Buy";
    public static final String CFG_BUY_MODEEXP = "from";

    public static final boolean CFG_SELL_PERMS = false;
    public static final boolean CFG_SELL_PERMSID = false;

    public static final String CFG_SELL_MODENAME = "Sell";
    public static final String CFG_SELL_MODEEXP = "to";

    public static final String CFG_PRICE_TEXT = "&bPrice: &6<price>";
    public static final String CFG_PRICE_FREE = "Free";
    public static final String CFG_PRICE_DISPLAY = "Display Only";
    public static final String CFG_PRICE_COST = "<rawprice> <curname>";
    public static final String CFG_PRICE_COSTMULTI = "<totalprice> <curname> total (<rawprice> <curname> each)";

    public static final boolean CFG_SHOP_NOTIFICATIONS = true;
    public static final boolean CFG_SHOP_AUTO_LIMIT = true;
    public static final boolean CFG_SHOP_AUTO_OWNER = true;
    public static final boolean CFG_SHOP_FORCEEMPTY = true;
    public static final int CFG_SHOP_MINDECPLACES = 2;

    public static final String CFG_SHOP_TITLE_DEFAULT = "<mode>";
    public static final String CFG_SHOP_TITLE_OWNED = "<mode> <modeexp> <owner>";
    public static final String CFG_SHOP_TITLE_TITLED = "<mode> <modeexp> <title>";
    public static final String CFG_SHOP_TITLE_OWNED_TITLED = "<mode>: <title>";

    public static final boolean CFG_LOG_SHOP_CREATION = false;

    public ConfigManager(final SignChestShopPlugin plugin) {
        this.plugin = plugin;
    }

    public void loadConfig() {
        try {
            config.load();
        } catch (final IOException ioe) {
            plugin.getLogger().log(Level.SEVERE, "Could not load config, reverting to defaults", ioe);
        }
    }

    public boolean writeConfig() {
        try {
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
            config.insertComment(" <mode> - the mode of the shop");
            config.insertComment(" <modeexp> - the phrase following the mode (example: \"from\" in \"Buy from\"");
            config.writeLine();
            config.insertComment("Text used to display the price of an item (use <price> for the price)");
            config.writeKey("shop.price.text", CFG_PRICE_TEXT);
            config.insertComment("<price> when the item(s) is/are free");
            config.writeKey("shop.price.free", CFG_PRICE_FREE);
            config.insertComment("<price> when the item(s) is/are on display");
            config.writeKey("shop.price.display", CFG_PRICE_DISPLAY);
            config.insertComment("<price> when there is only one item, but it is worth some amount (use <rawprice> for actual price)");
            config.writeKey("shop.price.cost", CFG_PRICE_COST);
            config.insertComment("<price> when there is more than one item, but they are worth some amount");
            config.insertComment(" Use <rawprice> for the cost of one item and <totalprice> for the total cost");
            config.writeKey("shop.price.costmulti", CFG_PRICE_COSTMULTI);
            config.writeLine();
            config.insertComment("Default title of an unowned and untitled shop");
            config.writeKey("shop.title.default", CFG_SHOP_TITLE_DEFAULT);
            config.insertComment("Default title of an owned shop");
            config.writeKey("shop.title.owned", CFG_SHOP_TITLE_OWNED);
            config.insertComment("Default title of an unowned and titled shop");
            config.writeKey("shop.title.titled", CFG_SHOP_TITLE_TITLED);
            config.insertComment("Default title of an owned and titled shop");
            config.writeKey("shop.title.titledowned", CFG_SHOP_TITLE_OWNED_TITLED);
            config.writeLine();
            config.insertComment("Enable this to force signs to be empty on creation; ignores shop variables");
            config.writeKey("shop.forceempty", CFG_SHOP_FORCEEMPTY);
            config.insertComment("Minimum decimal places to display for prices");
            config.writeKey("shop.mindecplaces", CFG_SHOP_MINDECPLACES);
            config.insertComment("This determines if owners should receive notifications");
            config.writeKey("shop.notifications", CFG_SHOP_NOTIFICATIONS);
            config.writeLine();
            config.insertComment("Enable this to make shops limited on creation");
            config.writeKey("shop.auto.limit", CFG_SHOP_AUTO_LIMIT);
            config.insertComment("Enable this to make each creator of a shop the owner of the shop on creation");
            config.writeKey("shop.auto.owner", CFG_SHOP_AUTO_OWNER);
            config.writeLine();
            config.insertComment("==== Buying Options ====#");
            config.writeLine();
            config.insertComment("Buying modes:");
            config.insertComment(" single  - Items are bought as single items");
            config.insertComment(" stack  - Items are bought as stacks");
            config.insertComment(" amount  - Items are bought with the same amount as the displayed item");
            config.writeKey("buy.mode", CFG_BUY_MODE);
            config.insertComment("Enable this to make shift clicks buy a stack");
            config.write("buy.shiftclick", "" + config.getBoolean("buy.shiftclick", CFG_BUY_SHIFTCLICK));
            config.insertComment("Enable this to require players to have \"scs.buy\" in order to open a buy shop");
            config.writeKey("buy.perms", "" + CFG_BUY_PERMS);
            config.insertComment("Enable this to require players to have \"scs.buy.<id>\" in order to let them buy items with the id");
            config.writeKey("buy.permsid", "" + CFG_BUY_PERMSID);
            config.insertComment("The name of this mode when used as a <mode> variable");
            config.writeKey("buy.modename", CFG_BUY_MODENAME);
            config.insertComment("The <modeexp> variable of the buy mode (see shop option variable section above)");
            config.writeKey("buy.modeexp", CFG_BUY_MODEEXP);
            config.insertComment("The notice that is sent to the owner when someone buys an item from an unnamed shop (uses shop option variables)");
            config.writeKey("buy.notice.default", MSG_BUY_NOTICE);
            config.insertComment("The notice that is sent to the owner when someone buys an item from a named shop (uses shop option variables)");
            config.writeKey("buy.notice.titled", MSG_BUY_NOTICE_TITLED);
            config.writeLine();
            config.insertComment("==== Selling Options ====#");
            config.writeLine();
            config.insertComment("Enable this to require players to have \"scs.sell\" in order to open a sell shop");
            config.writeKey("sell.perms", "" + CFG_SELL_PERMS);
            config.insertComment("Enable this to require players to have \"scs.sell.<id>\" in order to let them sell items with the id");
            config.writeKey("sell.permsid", "" + CFG_SELL_PERMSID);
            config.insertComment("The name of this mode when used as a <mode> variable");
            config.writeKey("sell.modename", CFG_SELL_MODENAME);
            config.insertComment("The <modeexp> variable of the sell mode (see shop option variable section above)");
            config.writeKey("sell.modeexp", CFG_SELL_MODEEXP);
            config.insertComment("The notice that is sent to the owner when someone sells an item to an unnamed shop (uses shop option variables)");
            config.writeKey("sell.notice.default", MSG_SELL_NOTICE);
            config.insertComment("The notice that is sent to the owner when someone sells an item to a named shop (uses shop option variables)");
            config.writeKey("sell.notice.titled", MSG_SELL_NOTICE_TITLED);
            config.writeLine();
            config.insertComment("==== Messages ====#");
            config.writeLine();
            config.insertComment("Global message variables:");
            config.insertComment(" <player>  - The player doing an action");
            config.writeLine();
            config.insertComment("---- Command Messages ----#");
            config.writeLine();
            config.insertComment("Message for creating a shop");
            config.write("message.create.success", config.getString("message.create.success", MSG_CREATE_SUCCESS));
            config.insertComment("Message for cancelling shop creation");
            config.write("message.create.cancel", config.getString("message.create.cancel", MSG_CREATE_CANCEL));
            config.writeLine();
            config.insertComment("Message for cancelling the pricing of an item");
            config.write("message.price.cancel", config.getString("message.price.cancel", MSG_PRICE_CANCEL));
            config.insertComment("Message for setting the price of an item");
            config.write("message.price.set", config.getString("message.price.set", MSG_PRICE_SET));
            config.writeLine();
            config.insertComment("Message for editing a shop");
            config.write("message.edit", config.getString("message.edit", MSG_EDIT));
            config.writeLine();
            config.insertComment("Message for an attempted breaking of a shop with the perm \"scs.create\"");
            config.write("message.break.perm", config.getString("message.break.perm", MSG_BREAK_PERM));
            config.insertComment("Message for an attempted breaking of a shop without the perm \"scs.create\"");
            config.write("message.break.noperm", config.getString("message.beak.noperm", MSG_BREAK_NOPERM));
            config.writeLine();
            config.insertComment("Message for setting the title of a shop.  <title> is the title of the shop.");
            config.writeKey("message.settitle.success", MSG_SETTITLE_SUCCESS);
            config.insertComment("Message for removing the title of a shop");
            config.writeKey("message.settitle.remove", MSG_SETTITLE_REMOVE);
            config.insertComment("Message for exceeding the 32 characters (Minecraft limitation; anything longer will cause errors) in a shop title");
            config.writeKey("message.settitle.fail", MSG_SETTITLE_FAIL);
            config.writeLine();
            config.insertComment("Message for not targeting a SignChestShop");
            config.write("message.cmd.notarget", config.getString("message.cmd.notarget", MSG_CMD_NOTARGET));
            config.insertComment("Message for not having the permissions");
            config.write("message.cmd.noperm", config.getString("message.cmd.noperm", MSG_CMD_NOPERM));
            config.insertComment("Message for attempting to use owner commands on a shop not owned by the player");
            config.write("message.cmd.notowned", config.getString("message.cmd.notowned", MSG_CMD_NOTOWNED));
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
            config.writeKey("message.buy.success", MSG_BUY_SUCCESS);
            config.insertComment("Message for buying an item from an unnamed and owned shop successfully");
            config.writeKey("message.buy.success.owner", MSG_BUY_SUCCESS_OWNED);
            config.insertComment("Message for buying an item from a named and unowned shop successfully");
            config.writeKey("message.buy.success.titled", MSG_BUY_SUCCESS_TITLED);
            config.insertComment("Message for buying an item from a named and owned shop successfully");
            config.writeKey("message.buy.success.titledowner", MSG_BUY_SUCCESS_TITLED_OWNED);
            config.insertComment("Message for not having enough money while buying an item");
            config.write("message.buy.fail", config.getString("message.buy.fail", MSG_BUY_FAIL));
            config.insertComment("Message for buying an item for free");
            config.write("message.buy.free", config.getString("message.buy.free", MSG_BUY_FREE));
            config.insertComment("Message for doing an invalid action while shopping, ignores buy " +
                    "variables");
            config.write("message.buy.invalid", config.getString("message.buy.invalid", MSG_BUY_INVALID));
            config.writeLine();
            config.insertComment("Message for selling an item to an unnamed and unowned shop successfully");
            config.writeKey("message.sell.success", MSG_SELL_SUCCESS);
            config.insertComment("Message for selling an item to an unnamed and owned shop successfully");
            config.writeKey("message.sell.success.owned", MSG_SELL_SUCCESS_OWNED);
            config.insertComment("Message for selling an item to an named and unowned shop successfully");
            config.writeKey("message.sell.success.titled", MSG_SELL_SUCCESS_TITLED);
            config.insertComment("Message for selling an item to an named and owned shop successfully");
            config.writeKey("message.sell.success.titledowned", MSG_SELL_SUCCESS_TITLED_OWNED);
            config.insertComment("Message for the owner of the shop not having enough money to buy a sold item");
            config.writeKey("message.sell.fail", MSG_SELL_FAIL);
            config.insertComment("Message for a limited shop's storage not having enough space to buy an item from a player");
            config.writeKey("message.sell.nospace", MSG_SELL_NOSPACE);
            config.insertComment("Message for doing an invalid action while shopping, ignores sell " +
                    "variables");
            config.write("message.sell.invalid", config.getString("message.sell.invalid", MSG_SELL_INVALID));
            config.writeLine();
            config.insertComment("==== Logging Options ====#");
            config.writeLine();
            config.insertComment("Enable this to log shop creation to the console");
            config.write("log.create", "" + config.getBoolean("log.create", CFG_LOG_SHOP_CREATION));
            config.close();
        } catch (final IOException ioe) {
            plugin.getLogger().log(Level.SEVERE, "Could not create config", ioe);
            return false;
        }
        return true;
    }

    String color(final String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    String doShopTitle(final Shop shop) {
        final String owner = shop.getOwnerName();
        final String title = shop.getTitle();
        SignChestShopPlugin.DKey<String, String> a;
        String fin;
        if (owner == null && title == null) {
            a = new SignChestShopPlugin.DKey<String, String>("shop.title.default",
                    CFG_SHOP_TITLE_DEFAULT);
        } else if (owner != null && title == null) {
            a = new SignChestShopPlugin.DKey<String, String>("shop.title.owned",
                    CFG_SHOP_TITLE_OWNED);
        } else if (title != null && owner == null) {
            a = new SignChestShopPlugin.DKey<String, String>("shop.title.titled",
                    CFG_SHOP_TITLE_TITLED);
        } else {
            a = new SignChestShopPlugin.DKey<String, String>("shop.title.titledowned",
                    CFG_SHOP_TITLE_OWNED_TITLED);
        }
        fin = config.getString(a.a, a.b);
        if (fin.startsWith("<mode>")) {
            final String mode = shop.getMode().name().toLowerCase();
            fin = fin.replaceFirst("<mode>", Character.toUpperCase(mode.charAt(0)) +
                    mode.substring(1));
        }
        return varShop0(fin, shop, owner, title);
    }

    String placePadding(final double price) {
        String pstring = Double.toString(price);
        final int places = pstring.length() - pstring.indexOf('.') - 1;
        final int min = config.getInt("shop.mindecplaces", CFG_SHOP_MINDECPLACES);
        if (places < min) {
            final StringBuffer sb = new StringBuffer().append(pstring);
            for (int j = places; j < min; j++) {
                sb.append(0);
            }
            pstring = sb.toString();
        }
        return pstring;
    }

    String var(final String s) {
        String a = color(Matcher.quoteReplacement(s));
        a = a.replaceAll("<curnameplur>", plugin.econ.currencyNamePlural());
        a = a.replaceAll("<curnamesing>", plugin.econ.currencyNameSingular());
        return a;
    }

    String varCur(final String s, final double amount) {
        return var(s).replaceAll("<curname>", amount == 1 ? plugin.econ.currencyNameSingular() : plugin.econ.currencyNamePlural());
    }

    String varNotice(final String s, final Shop shop, final Player player, final double amount) {
        String a = varPlayer(varShop(s, shop), player);
        a = a.replaceAll("<price>", "" + amount);
        return a;
    }

    String varPlayer(final String s, final Player player) {
        return var(s).replaceAll("<player>", player.getName());
    }

    String varShop(final String s, final Shop shop) {
        return varShop0(s, shop, shop.getOwnerName(), shop.getTitle());
    }

    String varTrans(final String s, final Player player, final Shop shop, final int amount, final String price, final double rawprice) {
        String a = varCur(varPlayer(s, player), rawprice);
        a = a.replaceAll("<amount>", "" + amount);
        a = a.replaceAll("<price>", "" + price);
        a = a.replaceAll("<rawprice>", "" + rawprice);
        a = a.replaceAll("<itemcorrectl>", (amount == 1 ? "item" : "items"));
        a = a.replaceAll("<itemcorrectu>", (amount == 1 ? "Item" : "Items"));
        return varShop(a, shop);
    }

    private String varShop0(final String s, final Shop shop, final String owner, final String title) {
        String a = var(s);
        a = a.replaceAll("<owner>", owner == null ? "" : owner);
        a = a.replaceAll("<title>", title == null ? "" : title);
        final Shop.ShopMode mode = shop.getMode();
        a = a.replaceAll("<mode>", mode.name().toLowerCase());
        if (mode == Shop.ShopMode.BUY) {
            a = a.replaceAll("<modeexp>",
                    config.getString("buy.modeexp", CFG_BUY_MODEEXP));
        } else if (mode == Shop.ShopMode.SELL) {
            a = a.replaceAll("<modeexp>",
                    config.getString("sell.modeexp", CFG_SELL_MODEEXP));
        }
        return a;
    }
}
