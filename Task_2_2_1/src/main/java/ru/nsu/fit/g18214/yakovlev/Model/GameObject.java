package ru.nsu.fit.g18214.yakovlev.Model;

class GameObject{
  private ObjectType type;
  private TypeForTextures typeForTextures;

  GameObject() {
    this.type = ObjectType.EMPTY;
    this.typeForTextures = TypeForTextures.FIELD;
  }

  void setType(ObjectType type, TypeForTextures typeForTextures) {
    this.type = type;
    this.typeForTextures = typeForTextures;
  }

  void setEmptyType() {
    this.type = ObjectType.EMPTY;
    this.typeForTextures = TypeForTextures.FIELD;
  }

  ObjectType getType() {
    return type;
  }

  TypeForTextures getTypeForTextures() {
    return typeForTextures;
  }
}

