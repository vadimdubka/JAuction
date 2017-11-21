<%@ page contentType="text/html;charset=UTF-8" %>
<div class="player-block">
    <p>Welcome, ${role} ${player.email}!</p>

    <form action="controller" method="get">
        <input type="hidden" name="command_type" value="logout">
        <input type="submit" value="Log out">
    </form>
    <form action="controller" method="get">
        <input type="hidden" name="command_type" value="show_all_players">
        <input type="submit" value="Show all players">
    </form>
</div>