package bounen057.multihardcore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DeathPlayer implements Listener {
    MultiHardCore pl;
    public DeathPlayer(MultiHardCore pl){
        this.pl = pl;
    }

    @EventHandler
    public void death(PlayerDeathEvent e) {
        Player p = e.getEntity();

        if(!(pl.config.getConfig().getString("config.deathmessage") == null)) {
            e.setDeathMessage(pl.config.getConfig().getString("config.deathmessage").replace("%player%",p.getName()) );
        }

        // 時間を取得
        Calendar ctime = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

        pl.player.getConfig().set("Player.ban.date."+p.getUniqueId(), sdf.format(ctime.getTime()) );
        pl.player.saveConfig();

        String message = pl.config.getConfig().getString("config.banmessage");
        if(message == null) message = "§4§lハードコアモードなのであなたはこのサーバーからBANされました。一定期間後またログインできます";

        p.kickPlayer(message);
    }
}
