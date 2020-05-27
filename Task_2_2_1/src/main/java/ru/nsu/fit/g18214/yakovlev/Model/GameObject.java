package ru.nsu.fit.g18214.yakovlev.Model;

import ru.nsu.fit.g18214.yakovlev.TextureType;

class GameObject{
  private ObjectType type;
  private TextureType textureType;

  GameObject() {
    this.type = ObjectType.EMPTY;
    this.textureType = TextureType.FIELD;
  }

  void setType(ObjectType type, TextureType textureType) {
    this.type = type;
    this.textureType = textureType;
  }

  void setEmptyType() {
    this.type = ObjectType.EMPTY;
    this.textureType = TextureType.FIELD;
  }

  ObjectType getType() {
    return type;
  }

  TextureType getTextureType() {
    return textureType;
  }
}

