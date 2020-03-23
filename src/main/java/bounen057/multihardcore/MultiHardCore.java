package bounen057.multihardcore;

import bounen057.multihardcore.command.GetCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/*
*
* @author Bounen057
* マルチサーバー用 ハードコア
*
 */

public final class MultiHardCore extends JavaPlugin {

    public static String logo = "§c§l[§4§lMultiHardCore§c§l]";

    // 変数
    public CustomConfig config,player;

    @Override
    public void onEnable() {


        // CustomConfig
        config = new CustomConfig(this);
        config.saveDefaultConfig();

        player = new CustomConfig(this,"player.yml");
        player.saveDefaultConfig();



        // リステナー
        Bukkit.getPluginManager().registerEvents(new DeathPlayer(this), this);
        Bukkit.getPluginManager().registerEvents(new JoinPlayer(this), this);

        Bukkit.getPluginCommand("hardcore").setExecutor(new GetCommand(this));
    }

    @Override
    public void onDisable() {

    }
}
