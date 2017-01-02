public class GetPlayers {

    void magic() {
        for (int pagenumber = 0; pagenumber < 100; pagenumber++) {
            ParseBoardPage parser = new ParseBoardPage(pagenumber, GameMode.Arcade);
            Thread thread = new Thread(parser);
        }
    }
}
