package com.example.chessteaching;

import static com.example.chessteaching.MainActivity.LeisureMode;
import static com.example.chessteaching.MainActivity.TraditionMode;

public class ChinasChessGameConfiguration {
    private int OriginX, OriginY, Unit;
    private final int[][] formalChessPosition = {{4, 9}, {3, 9}, {5, 9}, {2, 9}, {6, 9}, {0, 9}, {8, 9}, {1, 9},
            {7, 9}, {1, 7}, {7, 7}, {0, 6}, {2, 6}, {4, 6}, {6, 6}, {8, 6}};//棋盤座標(左上為原點)
    private final int[][] leisureChessPosition = {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0},
            {0, 1}, {1, 1}, {2, 1}, {3, 1}, {4, 1}, {5, 1}, {6, 1}, {7, 1}};//棋盤座標(左上為原點)
    private int[][] standardChessPosition = new int[16][2];
    private int Mode = 0;

    public ChinasChessGameConfiguration(double originX, double originY, double unit, int mode) {
        OriginX = (int) originX;
        OriginY = (int) originY;
        Unit = (int) unit;
        Mode = mode;
        if (mode == TraditionMode) {
            standardChessPosition = formalChessPosition;
        } else if (mode == LeisureMode) {
            standardChessPosition = leisureChessPosition;
        }
    }

    public int getChessPositionWidth(int NumberOfChess) {
        int Width = 0;
        int position = 0;
        if (NumberOfChess > 15) {
            NumberOfChess -= 16;
        }
        position = standardChessPosition[NumberOfChess][0];
        Width = OriginX + Unit * position;

        return Width;
    }

    public int getChessPositionHeight(int NumberOfChess) {
        int Height = 0;
        int position = 0;
        int Reverse = 0;
        if (Mode == TraditionMode) {
            if (NumberOfChess > 15) {
                NumberOfChess -= 16;
                Reverse = -9;
                Unit = -Unit;
            }
        } else if (Mode == LeisureMode) {
            if (NumberOfChess > 15) {
                NumberOfChess -= 16;
                Reverse = 2;
            }
        }
        position = standardChessPosition[NumberOfChess][1];
        Height = OriginY + Unit * (position + Reverse);


        return Height;
    }
}
