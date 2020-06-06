package ru.nsu.fit.g18214.yakovlev.Model;

import ru.nsu.fit.g18214.yakovlev.Tile;

class GameObject{
  private ObjectType type;
  private Tile tile;

  GameObject() {
    this.type = ObjectType.EMPTY;
    this.tile = Tile.FIELD;
  }

  void setType(ObjectType type, Tile tile) {
    this.type = type;
    this.tile = tile;
  }

  void setEmptyType() {
    this.type = ObjectType.EMPTY;
    this.tile = Tile.FIELD;
  }

  ObjectType getType() {
    return type;
  }

  Tile getTile() {
    return tile;
  }
}

