package bounen057.multihardcore.command;

import bounen057.multihardcore.MultiHardCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.MultipleCommandAlias;
import org.bukkit.entity.Player;

public class GetCommand implements CommandExecutor {
    MultiHardCore pl;
    public GetCommand(MultiHardCore pl){
        this.pl = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player)sender;

        // permission の確認
        //if(!p.hasPermission("MultiHardCore.admin")) return false;


        // help
        if(args.length == 0 || args[0].equals("help")){
            help(p);
            return true;
        }

        //
        // ここから args 2以上あるコマンド
        //

        if(args[1] == null){
            p.sendMessage(MultiHardCore.logo + "§4使い方が間違っていませんか?");
            return true;
        }

        if(args[0].equals("bantime")){
            new SetOption(pl).SetBanTime(p,Integer.parseInt(args[1]));
            return true;
        }

        String args_message = "";
        for(int i = 1;i < args.length;i++){
            if(args[i] == null) break;
            args_message = args_message + " " + args[i];
        }

        if(args[0].equals("deathmessage")){
            new SetOption(pl).SetDeathMessage(p,args_message);
            return true;
        }

        if(args[0].equals("banmessage")){
            new SetOption(pl).SetBanMessage(p,args_message);
        }



        return false;
    }


    /*

     HELP コマンド

     */
    private void help(Player p){
        p.sendMessage(pl.logo+" §6§l HELP");
        p.sendMessage("§b-/hardcore help  §7- helpの表示");
        p.sendMessage("§b-/hardcore deathmessage <msg>  §7- 死亡ログ %player%でID");
        p.sendMessage("§b-/hardcore bantime <時間(hour)>  §7- BANの期間の設定");
        p.sendMessage("§b-/hardcore banmessage <msg>  §7- BANメッセージの設定");
    }

}
