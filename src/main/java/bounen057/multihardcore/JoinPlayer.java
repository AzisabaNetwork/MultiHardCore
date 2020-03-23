package bounen057.multihardcore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import sun.util.resources.cldr.aa.CalendarData_aa_ER;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JoinPlayer implements Listener {
    MultiHardCore pl;
    public JoinPlayer(MultiHardCore pl){
        this.pl = pl;
    }
    @EventHandler
    public void Join(PlayerJoinEvent e) throws ParseException {
        Player p = e.getPlayer();

        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Date time1 = sdFormat.parse(
                pl.player.getConfig().getString("Player.ban.date."+p.getUniqueId())
                );

        Calendar cTime1 = Calendar.getInstance();
        cTime1.setTime(time1);

        // banされた時間 と 今の時間を比較
        if(pl.player.getConfig().getString("Player.ban.date."+p.getUniqueId()) != null) {
            Calendar cTime2 = Calendar.getInstance();

            cTime2.add( Calendar.SECOND, -1 * pl.config.getConfig().getInt("config.bantime") );
            Date time2 = cTime2.getTime();

            // キックの処理
            if(!time1.before(time2)){

                int time_s = getDiffDays(cTime1,cTime2);
                p.kickPlayer("§4§lあなたはBANされています! 次ログインできるまで:"+time_s +"時間");

            }

        }

    }

    /**
     * 経過日数を取得する。
     */
    int getDiffDays(Calendar calendar1, Calendar calendar2) {
        //==== ミリ秒単位での差分算出 ====//
        long diffTime = calendar1.getTimeInMillis() - calendar2.getTimeInMillis();

        //==== 日単位に変換 ====//
        int MILLIS_OF_HOUR = 1000 * 60 * 60;
        int diffDays = (int)(diffTime / MILLIS_OF_HOUR);

        return diffDays;
    }
}
