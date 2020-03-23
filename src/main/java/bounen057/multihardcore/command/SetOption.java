package bounen057.multihardcore.command;

import bounen057.multihardcore.MultiHardCore;
import org.bukkit.entity.Player;

public class SetOption {
    MultiHardCore pl;
    public SetOption(MultiHardCore pl){
        this.pl = pl;
    }

    public void SetDeathMessage(Player p,String message){

        // 死亡時のメッセージを変更
        pl.config.getConfig().set("config.deathmessage",
                message.replace("&","§")
        );

        p.sendMessage(pl.logo+"§a死亡時のメッセージを変更しました!");

        pl.config.saveConfig();
    }

    public void SetBanTime(Player p,int t){
        pl.config.getConfig().set("config.bantime",t * 60 * 60);

        p.sendMessage(pl.logo+"§aBANされる期間を変更しました!");

        pl.config.saveConfig();
    }

    public void SetBanMessage(Player p,String message){
        pl.config.getConfig().set("config.banmessage",message.replace("&","§"));

        p.sendMessage(pl.logo+"§aBANメッセージを変更しました!");

        pl.config.saveConfig();
    }
}
