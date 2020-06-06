package ru.nsu.fit.g18214.yakovlev.Model;

import ru.nsu.fit.g18214.yakovlev.Tile;

class GameField {
  private GameObject[][] gameObjectsField;
  private int cellCnt;

  GameField(int cellCnt) {
    this.cellCnt = cellCnt;
    this.gameObjectsField = new GameObject[cellCnt][cellCnt];
  }

  void initializeField() {
    for (int i = 0; i < cellCnt; i++) {
      for (int j = 0; j < cellCnt; j++) {
        gameObjectsField[i][j] = new GameObject();
      }
    }
  }

  ObjectType getCellType(int x, int y) {
    return gameObjectsField[x][y].getType();
  }

  Tile getTypeForTextures(int x, int y) {
    return gameObjectsField[x][y].getTile();
  }

  void setCellType(int x, int y, ObjectType type, Tile texturesType) {
    gameObjectsField[x][y].setType(type, texturesType);
  }

  void makeCellEmpty(int x, int y) {
    gameObjectsField[x][y].setEmptyType();
  }
}
