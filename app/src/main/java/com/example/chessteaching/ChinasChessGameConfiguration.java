package com.example.chessteaching;

public class ChinasChessGameConfiguration {
    private int OriginX, OriginY, Unit;
    private final int[][] formalChessPosition = {{4, 9}, {3, 9}, {5, 9}, {2, 9}, {6, 9}, {0, 9}, {8, 9}, {1, 9},
            {7, 9}, {1, 7}, {7, 7}, {0, 6}, {2, 6}, {4, 6}, {6, 6}, {8, 6}};//棋盤座標(左上為原點)
    private final int[][] leisureChessPosition = {{0, 0}, {3, 9}, {5, 9}, {2, 9}, {6, 9}, {0, 9}, {8, 9}, {1, 9},
            {7, 9}, {1, 7}, {7, 7}, {0, 6}, {2, 6}, {4, 6}, {6, 6}, {8, 6}};//棋盤座標(左上為原點)


    public ChinasChessGameConfiguration(double originX, double originY, double unit) {
        OriginX = (int)originX;
        OriginY = (int)originY;
        Unit = (int)unit;
    }

    public int getChessPositionWidth(int NumberOfChess) {
        int Width = 0;
        int position = 0;
        if (NumberOfChess > 15) {
            NumberOfChess -= 16;
        }
        position = formalChessPosition[NumberOfChess][0];
        Width = OriginX+Unit*position;

        return Width;
    }

    public int getChessPositionHeight(int NumberOfChess) {
        int Height = 0;
        int position = 0;
        int Reverse=0;
        if (NumberOfChess > 15) {
            NumberOfChess -= 16;
            Reverse=-9;
            Unit= -Unit;
        }
        position = formalChessPosition[NumberOfChess][1];
        Height = OriginY+Unit*(position+Reverse);

        return Height;
    }
}
